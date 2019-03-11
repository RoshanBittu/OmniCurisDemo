package com.shoppingDemo.Model;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="CUSTOMER")
public class Customer implements Serializable{
	private static final long serialVersionUID = -2576670215015463100L;
	
	@Id
	@Column(name="C_ID",nullable = false)
	private int customerID;
	
	@Column(name="C_NAME",nullable=false)
	private String customerName;
	
	@Column(name="C_ADDRESS",nullable=false)
	private String customerAddress;
	
	@Column(name="C_EMAIL",nullable=false)
	private String customerEmail;
	

	public int getCustomerID() {
		return customerID;
	}

	public void setCustomerID(int customerID) {
		this.customerID = customerID;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	
}
