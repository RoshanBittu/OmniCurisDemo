package com.shoppingDemo.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shoppingDemo.DAO.CustomerDAO;
import com.shoppingDemo.Model.Customer;
import com.shoppingDemo.service.CustomerService;


@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerDAO customerDAO;

	@Transactional
	public Customer getCustomerByEmail(String email) {
		
		return customerDAO.getCustomerByEmail(email);
	}

}
