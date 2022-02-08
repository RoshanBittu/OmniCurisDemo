package com.shoppingDemo.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.shoppingDemo.DAO.ProductDAO;
import com.shoppingDemo.Model.Customer;
import com.shoppingDemo.Model.Items;
import com.shoppingDemo.Model.OrdersInfo;
import com.shoppingDemo.Model.PlaceOrder;
import com.shoppingDemo.Model.OrderDetails;
import com.shoppingDemo.Model.OrderDetailsInfo;
import com.shoppingDemo.Model.Orders;
import com.shoppingDemo.Model.Products;
import com.shoppingDemo.service.CustomerService;
import com.shoppingDemo.service.OrderDetailsService;
import com.shoppingDemo.service.OrderService;
import com.shoppingDemo.service.ProductService;
import com.shoppingDemo.service.impl.ProductServiceImpl;

@RestController
@RequestMapping(value="/Admin")
public class AdminController {
	@Autowired
	private ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderDetailsService orderDeatilsService;
	
	@Autowired
	CustomerService customerService;
	
	@RequestMapping(value = "/getAllProducts",method=RequestMethod.GET)
	public List getAllProducts(){
		List<Products> productStatus = productService.getAllProduct();
		List<Products> productActive= new ArrayList<Products>();
		for(Products p :productStatus ){
			if(p.getStatus().equals("ACTIVE")&&p.getProductQuantity()>0){
				productActive.add(p);
			}
		}
		return productActive;
	}
	
	@RequestMapping(value = "/getProductByID/{id}",method=RequestMethod.GET)
	public ResponseEntity<Products> getProductByID(@PathVariable("id") int id){
		String test;
		Products p = productService.getProductByID(id);
		if(p == null){
			return new ResponseEntity<Products>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Products>(p, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/addProduct",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<Void> addProduct(@RequestBody Products product, UriComponentsBuilder ucBuilder){
		
		Products p = productService.getProductByID(product.getProductId());
		if(p!=null){
			return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
		}
			productService.createProduct(product);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("getProductByID/{id}").buildAndExpand(product.getProductId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/updateProduct",method=RequestMethod.PUT,headers="Accept=application/json")
	public ResponseEntity<String> updateProduct(@RequestBody Products product){
		String message;
		Products p = productService.getProductByID(product.getProductId());
		if(p==null){
			message = "Product not exist";
			return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
			}
		
		product.setCreateDate();
		productService.editProduct(product);
		message="Updated Successfully";
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/deleteProduct/{id}",method=RequestMethod.GET)
	public ResponseEntity<String> deleteProduct(@PathVariable("id") int id){
		String message;
		Products p = productService.getProductByID(id);
		if(p==null){
			message = "Product Not Found";
			return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		}
		productService.deleteProduct(id);
		message = "Deleted Successfully";
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	//******************************************************************************************************************
	//ORDERS URI
	
	@RequestMapping(value = "/getAllOrders",method=RequestMethod.GET)
	public ResponseEntity<List<OrdersInfo>> getAllOrders(){
		List<OrdersInfo> orders= orderService.getAllOrders();
		return new ResponseEntity<List<OrdersInfo>>(orders,HttpStatus.OK) ;
	}
	
	
	@RequestMapping(value = "/getOrderByID/{id}",method=RequestMethod.GET)
	public ResponseEntity<OrdersInfo> getOrderByID(@PathVariable("id") int id){
		
		OrdersInfo orders = orderService.getOrdersByID(id);
		if(orders==null){
			return new ResponseEntity<OrdersInfo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<OrdersInfo>(orders,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/placeOrder",method=RequestMethod.POST,headers="Accept=application/json")
	public ResponseEntity<String> placeAnOrder(@RequestBody PlaceOrder placeOrder){
		
		String message;
		
		if(placeOrder==null||placeOrder.getCustEmai()==null||placeOrder.getItems()==null){
			message = "Not  valid Request,check your Order";
			return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
		}
		Customer customer = customerService.getCustomerByEmail(placeOrder.getCustEmai());
		if(customer == null){
			message = "cutomer not found,please register first"+placeOrder.getCustEmai();
			return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
		}
		
		List<Items> items = placeOrder.getItems();
		
		List<Products> orderProducts = new ArrayList<>();
		
		for(Items it : items){
			if (it.getItemQuantity()<0||it.getItemQuantity() == 0){
				message = "Not a valid Request,check your OrdersQuantity"+it.getItemID();
				return new ResponseEntity<String>(message,HttpStatus.BAD_REQUEST);
			}
			Products products = productService.getProductByID(it.getItemID());
			if(products == null){
				message = "Sorry quantity is not availble,item unavailable,check your Item"+it.getItemID();
				return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
			}
			if(products.getProductQuantity()< it.getItemQuantity()){
				message = "Sorry the Order Cannot be place,quantity not sufficient for "+it.getItemID();
				return new ResponseEntity<String>(message,HttpStatus.NOT_FOUND);
			}
			
		}
		Orders order = orderService.placeOrder(customer, items, orderProducts);
		if(order != null){
		message = "Your Order has been placed with orderId "+order.getOrderId();
		}
		else{
		message = "errorOccured";
		return new ResponseEntity<String>(message,HttpStatus.EXPECTATION_FAILED);
		}
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
}
