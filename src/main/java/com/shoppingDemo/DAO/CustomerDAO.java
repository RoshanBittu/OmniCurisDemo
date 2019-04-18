package com.shoppingDemo.DAO;

import com.shoppingDemo.Model.Customer;

public interface CustomerDAO {
	
	Customer getCustomerByEmail(String email);
}
