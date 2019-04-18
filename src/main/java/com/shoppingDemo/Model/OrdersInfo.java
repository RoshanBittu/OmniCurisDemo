package com.shoppingDemo.Model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;

public class OrdersInfo implements Serializable{
	private static final long serialVersionUID = -2476670215015463100L;
	
	private int orderNum;
	private String customerEmail;
	@CreatedDate
	private Date orderDate;
	private double grandTotal;
	List<OrderDetailsInfo> itemDetails;
	
	
	
	public List<OrderDetailsInfo> getItemDetails() {
		return itemDetails;
	}
	public void setItemDetails(List<OrderDetailsInfo> itemDetails) {
		this.itemDetails = itemDetails;
	}
	public int getOrderNum() {
		return orderNum;
	}
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	public String getCustomerEmail() {
		return customerEmail;
	}
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public double getGrandTotal() {
		return grandTotal;
	}
	public void setGrandTotal(double grandTotal) {
		this.grandTotal = grandTotal;
	}
	
}
