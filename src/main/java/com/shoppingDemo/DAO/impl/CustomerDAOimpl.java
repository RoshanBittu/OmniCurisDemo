package com.shoppingDemo.DAO.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingDemo.DAO.CustomerDAO;
import com.shoppingDemo.Model.Customer;

@Repository
public class CustomerDAOimpl implements CustomerDAO {
	
	@Autowired
	private SessionFactory factory;
	
	@Override
	public Customer getCustomerByEmail(String email) {
		Session session = factory.getCurrentSession();
		
		String hql = "FROM Customer C where C.customerEmail = :email";
		Query query = session.createQuery(hql);
		query.setParameter("email",email);
		Customer customer = (Customer)query.uniqueResult();
		return customer;
	}

}
