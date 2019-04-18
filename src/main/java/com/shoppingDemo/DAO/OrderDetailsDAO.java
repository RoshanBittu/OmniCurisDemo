package com.shoppingDemo.DAO;

import java.util.List;

import com.shoppingDemo.Model.OrderDetails;
import com.shoppingDemo.Model.Orders;

public interface OrderDetailsDAO {
	public List<OrderDetails> getAllOrdersDetails();
	public OrderDetails getOrdersDetailsByID(int id);
	public int getmaxOfOrderDetailsId();
}
