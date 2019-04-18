package com.shoppingDemo.DAO.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingDemo.DAO.OrdersDAO;
import com.shoppingDemo.Model.Orders;
import com.shoppingDemo.service.ProductService;

@Repository
public class OrdersDAOimpl implements OrdersDAO {
	
	@Autowired
	private SessionFactory factory;
	
	
	@Override
	public List<Orders> getAllOrders() {
	
		List<Orders> orders = factory.getCurrentSession().createQuery("from Orders").list();
		
		return orders;
	}

	@Override
	public Orders getOrdersByID(int id) {
		
		return (Orders)factory.getCurrentSession().get(Orders.class, id);
	}
	
	
	@Override
	public int maxOfOrderId(){
		int maxId;
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(Orders.class).setProjection(Projections.max("orderId"));
		maxId = (int)criteria.uniqueResult();
		return maxId;
	}
	
	@Override
	public boolean createOrder(Orders orders){
		boolean status = false;
		factory.getCurrentSession().save(orders);
		return status;
	}
}
