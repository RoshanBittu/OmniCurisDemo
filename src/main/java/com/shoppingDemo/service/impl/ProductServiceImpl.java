package com.shoppingDemo.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import com.shoppingDemo.DAO.ProductDAO;
import com.shoppingDemo.Model.Products;
import com.shoppingDemo.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductDAO productDAO;
	
	@Transactional
	public void createProduct(Products product) {
		productDAO.createProduct(product);
	}

	@Transactional
	public void editProduct(Products product) {
		productDAO.editProduct(product);

	}

	@Transactional
	public void deleteProduct(int productID) {
		productDAO.deleteProduct(productID);
	}

	@Transactional
	public Products getProductByID(int productID) {
		return productDAO.getProductByID(productID);
	}

	@Transactional
	public List<Products> getAllProduct() {
		
		return productDAO.getAllProduct();
	}

}
