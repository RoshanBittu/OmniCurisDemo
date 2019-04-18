package com.shoppingDemo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingDemo.DAO.OrderDetailsDAO;
import com.shoppingDemo.DAO.OrdersDAO;
import com.shoppingDemo.Model.OrderDetails;
import com.shoppingDemo.service.OrderDetailsService;

@Service
public class OrderDeatilsServiceImpl implements OrderDetailsService {
	
	
	@Autowired
	private OrderDetailsDAO ordersDetailsDAO;
	
	@Transactional
	public List<OrderDetails> getAllOrdersDetails() {
		return ordersDetailsDAO.getAllOrdersDetails();
	}

	@Transactional
	public OrderDetails getOrdersDetailsByID(int id) {
		return ordersDetailsDAO.getOrdersDetailsByID(id);
	}

}
