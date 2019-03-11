package com.shoppingDemo.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDER_DETAILS")
public class OrderDetails  implements Serializable {
		private static final long serialVersionUID = 7550745928843183535L;
		
		@Id
		@Column(name="O_D_ID",nullable=false)
		private int orderDetailsID;
		
		@Column(name="O_D_AMOUNT",nullable=false)
		private double orderDetailsAmount;
		
		@Column(name="O_D_PRICE",nullable=false)
		private double orderDetailsPerPrice;
		
		@Column(name="O_D_QUANTITY",nullable=false)
		private int orderDetailsQuantity;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="ORDER_ID",referencedColumnName="ID",nullable=false)
		private Orders orders;
		
		@ManyToOne(fetch=FetchType.EAGER)
		@JoinColumn(name="PRODUCT_ID",referencedColumnName="P_ID",nullable=false)
		private Products products;

		public int getOrderDetailsID() {
			return orderDetailsID;
		}

		public void setOrderDetailsID(int orderDetailsID) {
			this.orderDetailsID = orderDetailsID;
		}

		public double getOrderDetailsAmount() {
			return orderDetailsAmount;
		}

		public void setOrderDetailsAmount(double orderDetailsAmount) {
			this.orderDetailsAmount = orderDetailsAmount;
		}

		public double getOrderDetailsPerPrice() {
			return orderDetailsPerPrice;
		}

		public void setOrderDetailsPerPrice(double orderDetailsPerPrice) {
			this.orderDetailsPerPrice = orderDetailsPerPrice;
		}

		public int getOrderDetailsQuantity() {
			return orderDetailsQuantity;
		}

		public void setOrderDetailsQuantity(int orderDetailsQuantity) {
			this.orderDetailsQuantity = orderDetailsQuantity;
		}

		public Orders getOrders() {
			return orders;
		}

		public void setOrders(Orders orders) {
			this.orders = orders;
		}

		public Products getProducts() {
			return products;
		}

		public void setProducts(Products products) {
			this.products = products;
		}
		
		
		
		
}
