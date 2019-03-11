package com.shoppingDemo.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.shoppingDemo.Model.Products;
import com.shoppingDemo.service.ProductService;
import com.shoppingDemo.service.impl.ProductServiceImpl;

@RestController
@RequestMapping(value="/Admin")
public class AdminController {
	@Autowired
	private ProductService productService;
	
	
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
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
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
	
	
}
