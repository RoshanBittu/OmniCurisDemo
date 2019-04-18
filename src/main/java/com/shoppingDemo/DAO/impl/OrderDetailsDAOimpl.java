package com.shoppingDemo.DAO.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingDemo.DAO.OrderDetailsDAO;
import com.shoppingDemo.Model.OrderDetails;
import com.shoppingDemo.Model.Orders;

@Repository
public class OrderDetailsDAOimpl implements OrderDetailsDAO {
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public List<OrderDetails> getAllOrdersDetails() {
		List<OrderDetails> orderDetails = factory.getCurrentSession().createQuery("from OrderDetails").list();
		return orderDetails;
	}

	@Override
	public OrderDetails getOrdersDetailsByID(int id) {
		
		return (OrderDetails)factory.getCurrentSession().get(OrderDetails.class, id);
	}
	
	@Override
	public int getmaxOfOrderDetailsId(){
		int maxId;
		Session session = factory.getCurrentSession();
		Criteria criteria = session.createCriteria(OrderDetails.class).setProjection(Projections.max("orderDetailsID"));
		maxId = (int)criteria.uniqueResult();
		return maxId;
	}

}
