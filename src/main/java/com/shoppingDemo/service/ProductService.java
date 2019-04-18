package com.shoppingDemo.service;

import java.util.List;

import com.shoppingDemo.Model.Products;

public interface ProductService {
	
	public void createProduct(Products product);
	public void editProduct(Products product);
	public void deleteProduct(int productID);
	public Products getProductByID(int productID);
	public List<Products> getAllProduct();

}
