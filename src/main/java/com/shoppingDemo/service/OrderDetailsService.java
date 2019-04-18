package com.shoppingDemo.service;

import java.util.List;

import com.shoppingDemo.Model.OrderDetails;

public interface OrderDetailsService {
	public List<OrderDetails> getAllOrdersDetails();
	public OrderDetails getOrdersDetailsByID(int id);
}
