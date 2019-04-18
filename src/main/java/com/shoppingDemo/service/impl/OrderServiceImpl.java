package com.shoppingDemo.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingDemo.DAO.OrderDetailsDAO;
import com.shoppingDemo.DAO.OrdersDAO;
import com.shoppingDemo.Model.Customer;
import com.shoppingDemo.Model.Items;
import com.shoppingDemo.Model.OrderDetails;
import com.shoppingDemo.Model.OrderDetailsInfo;
import com.shoppingDemo.Model.Orders;
import com.shoppingDemo.Model.OrdersInfo;
import com.shoppingDemo.Model.Products;
import com.shoppingDemo.service.OrderService;
import com.shoppingDemo.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrderDetailsDAO orderDetailsDAO;
	
	@Autowired
	private ProductService productService;
	
	@Transactional
	public List<OrdersInfo> getAllOrders() {
		
		List<Orders> orders= ordersDAO.getAllOrders();
		
		List<OrdersInfo> orderInfo = new ArrayList<>();
		
		for(Orders o : orders){
			
			List<OrderDetailsInfo> orderDetailsInfo = new ArrayList<>();
			
			OrdersInfo oI = new OrdersInfo();
			
			int orderNum = o.getOrderId();
			Date orderDate = o.getOrderDate();
			Customer customer = o.getCustomerID();
			String cEmail = customer.getCustomerEmail();
			double grandTotal = o.getOrderAmount();
			
			Set<OrderDetails> ordersDetails = o.getOrderDetails();
			for(Iterator<OrderDetails> it = ordersDetails.iterator(); it.hasNext(); ){
				
				OrderDetailsInfo odi = new OrderDetailsInfo();
				
				OrderDetails productInfo = it.next();
				
				Products product = productInfo.getProducts();
				String productName = product.getProductName();
				double productPrice = product.getProductPrice();
				int quantity = productInfo.getOrderDetailsQuantity();
				
				odi.setProductName(productName);
				odi.setProductPrice(productPrice);
				odi.setQuantity(quantity);
				odi.setTotalprice();
				
				orderDetailsInfo.add(odi);
			}
			oI.setOrderNum(orderNum);
			oI.setCustomerEmail(cEmail);
			oI.setOrderDate(orderDate);
			oI.setGrandTotal(grandTotal);
			oI.setItemDetails(orderDetailsInfo);
			
			orderInfo.add(oI);
		}
		
		return orderInfo;
	}



	@Transactional
	public OrdersInfo getOrdersByID(int id) {
		
		Orders orders = ordersDAO.getOrdersByID(id);
		
		OrdersInfo ordersInfo = new OrdersInfo();
		
		List<OrderDetailsInfo> orderDetailsInfo = new ArrayList<>();
		
		int orderNum = orders.getOrderId();
		Date orderDate = orders.getOrderDate();
		Customer customer = orders.getCustomerID();
		String cEmail = customer.getCustomerEmail();
		double grandTotal = orders.getOrderAmount();
		
		Set<OrderDetails> ordersDetails = orders.getOrderDetails();
		
		for(Iterator<OrderDetails> it = ordersDetails.iterator(); it.hasNext();){
			OrderDetailsInfo odi = new OrderDetailsInfo();
			
			OrderDetails productInfo = it.next();
			
			Products product = productInfo.getProducts();
			String productName = product.getProductName();
			double productPrice = product.getProductPrice();
			int quantity = productInfo.getOrderDetailsQuantity();
			
			odi.setProductName(productName);
			odi.setProductPrice(productPrice);
			odi.setQuantity(quantity);
			odi.setTotalprice();
			
			orderDetailsInfo.add(odi);
			
		}
		
		ordersInfo.setOrderNum(orderNum);
		ordersInfo.setCustomerEmail(cEmail);
		ordersInfo.setOrderDate(orderDate);
		ordersInfo.setGrandTotal(grandTotal);
		ordersInfo.setItemDetails(orderDetailsInfo);
		
		return ordersInfo;
	}
	
	@Transactional
	public Orders placeOrder(Customer customer,List<Items> items,List<Products> products){
		
		int orderId = ordersDAO.maxOfOrderId()+1;
		int orderNum = orderId;
		double TotalAmount = 0;
		Set<OrderDetails> orderDetails = new HashSet<OrderDetails>();
		
		//for calculating total price
		for(Items item : items){
			Products itemProduct = productService.getProductByID(item.getItemID());
			int qty = item.getItemQuantity();
			TotalAmount = TotalAmount+itemProduct.getProductPrice()*qty;
		}
		
		Orders order = new Orders();
		order.setOrderId(orderId);
		order.setCustomerID(customer);
		order.setOrderDate();
		order.setOrderNum(orderNum);
		order.setOrderAmount(TotalAmount);
		
		List<Products> qtyProd = new ArrayList<>();
		
		
		//for orderDetails
		int maxItemDetailID = orderDetailsDAO.getmaxOfOrderDetailsId()+1;
		
		for(Items item : items){
			OrderDetails itemDetail = new OrderDetails();
			
			Products itemProduct = productService.getProductByID(item.getItemID());
			double itemPrice = itemProduct.getProductPrice();
			int Qty = item.getItemQuantity();
			double oDA = TotalAmount;
			
			itemProduct.setProductQuantity(itemProduct.getProductQuantity()-Qty);
			qtyProd.add(itemProduct);
			
			itemDetail.setOrderDetailsID(maxItemDetailID);
			itemDetail.setOrderDetailsAmount(oDA);
			itemDetail.setOrderDetailsPerPrice(itemPrice);
			itemDetail.setOrderDetailsQuantity(Qty);
			itemDetail.setProducts(itemProduct);
			itemDetail.setOrders(order);
			
			orderDetails.add(itemDetail);
			
			
			maxItemDetailID++;
		}

		
		order.setOrderDetails(orderDetails);
		
		boolean Flag = ordersDAO.createOrder(order);
		
		//updating the products quantity in DB
		if(Flag == true){
			for(Products p : qtyProd){
				productService.editProduct(p);
			}
		}
		return order;
	}

}
