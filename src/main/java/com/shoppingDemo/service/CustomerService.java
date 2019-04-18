package com.shoppingDemo.service;

import com.shoppingDemo.Model.Customer;

public interface CustomerService {
	Customer getCustomerByEmail(String email);
}
