package com.shoppingDemo.Model;

import java.io.Serializable;

public class OrderDetailsInfo implements Serializable {
	private static final long serialVersionUID = 7540745928843183535L;
	
	private String productName;
	private  double productPrice;
	private int quantity;
	private double totalprice;
	
	
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getTotalprice() {
		return totalprice;
	}
	public void setTotalprice() {
		this.totalprice = this.productPrice*this.quantity;
	}
	
	
}
