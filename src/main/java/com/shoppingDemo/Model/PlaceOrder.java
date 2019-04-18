package com.shoppingDemo.Model;

import java.io.Serializable;
import java.util.List;

public class PlaceOrder implements Serializable {
	private static final long serialVersionUID = 7592745928843183535L;
	
	private String custEmai;
	private List<Items> items;
	public String getCustEmai() {
		return custEmai;
	}
	public void setCustEmai(String custEmai) {
		this.custEmai = custEmai;
	}
	public List<Items> getItems() {
		return items;
	}
	public void setItems(List<Items> items) {
		this.items = items;
	}
	
	

}
