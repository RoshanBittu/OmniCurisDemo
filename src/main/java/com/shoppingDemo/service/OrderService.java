package com.shoppingDemo.service;

import java.util.List;

import com.shoppingDemo.Model.Customer;
import com.shoppingDemo.Model.Items;
import com.shoppingDemo.Model.Orders;
import com.shoppingDemo.Model.OrdersInfo;
import com.shoppingDemo.Model.Products;

public interface OrderService {
	public List<OrdersInfo> getAllOrders();
	public OrdersInfo getOrdersByID(int id);
	public Orders placeOrder(Customer customer,List<Items> items,List<Products> products);	
}
