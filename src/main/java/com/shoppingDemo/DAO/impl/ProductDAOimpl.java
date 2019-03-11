package com.shoppingDemo.DAO.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shoppingDemo.DAO.ProductDAO;
import com.shoppingDemo.Model.Products;

@Repository
public class ProductDAOimpl implements ProductDAO {
	@Autowired
	private SessionFactory factory;
	
	
	public void createProduct(Products product){
		factory.getCurrentSession().save(product);
	}
	
	public void editProduct(Products product){
		factory.getCurrentSession().update(product);
	}
	
	public void deleteProduct(int productID){
		factory.getCurrentSession().delete(getProductByID(productID));
	}
	
	public Products getProductByID(int productID){
		return (Products) factory.getCurrentSession().get(Products.class, productID);
	}

	public List<Products> getAllProduct(){
		List<Products> allProducts = factory.getCurrentSession().createQuery("from Products").list();
		return allProducts;
	}
	
}
