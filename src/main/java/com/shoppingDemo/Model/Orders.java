package com.shoppingDemo.Model;

import java.io.Serializable;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Table(name="ORDERS",uniqueConstraints = { @UniqueConstraint(columnNames = "ORDER_NUM") })
public class Orders implements Serializable{
	private static final long serialVersionUID = -2576670215015463100L;
	
	@Id
	@Column(name="ID",nullable=false)
	private int orderId;
	
	@Column(name="AMOUNT",nullable=false)
	private double OrderAmount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="C_ID",nullable=false)
	private Customer customer;
	
	@CreatedDate
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name="ORDER_DATE",nullable=false)
	private Date orderDate = new java.sql.Date(new java.util.Date().getTime());;
	
    @Column(name = "ORDER_NUM", nullable = false)
    private int orderNum;
    
    //@OneToMany(mappedBy="orders",cascade=CascadeType.ALL,fetch=FetchType.EAGER,targetEntity=OrderDetails.class)
    @OneToMany(mappedBy="orders",fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    Set <OrderDetails> orderDetails;



    public Set<OrderDetails> getOrderDetails() {
	return orderDetails;
    }

    public void setOrderDetails(Set<OrderDetails> orderDetails) {
	this.orderDetails = orderDetails;
    }


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


	public Customer getCustomerID() {
		return customer;
	}

	public void setCustomerID(Customer customerID) {
		this.customer = customerID;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate() {
		this.orderDate = new java.sql.Date(new java.util.Date().getTime());;
	}

	public int getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
    
    
	
}
