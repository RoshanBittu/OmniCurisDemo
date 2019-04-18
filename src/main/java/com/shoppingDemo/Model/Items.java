package com.shoppingDemo.Model;

import java.io.Serializable;

public class Items implements Serializable {
	private static final long serialVersionUID = 7582745928843183535L;
	
	private int itemID;
	private int itemQuantity;
	public int getItemID() {
		return itemID;
	}
	public void setItemID(int itemID) {
		this.itemID = itemID;
	}
	public int getItemQuantity() {
		return itemQuantity;
	}
	public void setItemQuantity(int itemQuantity) {
		this.itemQuantity = itemQuantity;
	}
	
	
}
