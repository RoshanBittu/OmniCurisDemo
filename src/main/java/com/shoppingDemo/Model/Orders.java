package com.shoppingDemo.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name="ORDERS",uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_NUM") })
public class Orders implements Serializable{
	private static final long serialVersionUID = -2576670215015463100L;
	
	@Id
	@Column(name="ID",nullable=false)
	private int orderId;
	
	@Column(name="AMOUNT",nullable=false)
	private double OrderAmount;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="C_ID",referencedColumnName="C_ID",nullable=false)
	private Customer customer;
	
	@Column(name="ORDER_DATE",nullable=false)
	private Date orderDate;
	
    @Column(name = "ORDER_NUM", nullable = false)
    private int orderNum;

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public double getOrderAmount() {
		return OrderAmount;
	}

	public void setOrderAmount(double orderAmount) {
		OrderAmount = orderAmount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
    
    
	
}
