package com.shoppingDemo.DAO;

import java.util.List;

import com.shoppingDemo.Model.Orders;

public interface OrdersDAO {
	public List<Orders> getAllOrders();
	public Orders getOrdersByID(int id);
	public int maxOfOrderId();
	public boolean createOrder(Orders orders);
}
