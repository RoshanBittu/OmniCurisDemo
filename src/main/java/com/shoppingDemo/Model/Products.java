package com.shoppingDemo.Model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;

@Entity
@Table(name="PRODUCTS")
public class Products implements Serializable {
	private static final long serialVersionUID = -2054386655979281969L;
	
	@Id 
	@Column(name="P_ID",nullable=false)
	private int productId;
	
	@Column(name="P_IM_URL",nullable=true)
	private String productURL;
	
	@Column(name="P_NAME",nullable=false)
	private String productName;
	
	@Column(name="P_QUANTITY",nullable=false)
	private int productQuantity;
	
	@Column(name="P_PRICE",nullable=false)
	private double productPrice;
	
	@Column(name="P_STATUS",nullable=false)
	private String status;
	
	@CreatedDate
	@Temporal(javax.persistence.TemporalType.DATE)
	@Column(name="P_CREATE_DATE",nullable=false)
	private Date createDate = new java.sql.Date(new java.util.Date().getTime());
	
	public void setCreateDate(){
		createDate = new java.sql.Date(new java.util.Date().getTime());
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductURL() {
		return productURL;
	}

	public void setProductURL(String productURL) {
		this.productURL = productURL;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
