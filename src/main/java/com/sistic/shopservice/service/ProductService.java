package com.sistic.shopservice.service;

import java.util.List;

import com.sistic.shopservice.model.Product;

public interface ProductService {
	
	public List<Product> listAllProduct();
	public Product getProductById(int id);
	public List<Product> listAllProductByItemType(String itemType);
	public Product addProduct(Product product);
	public Product updateProduct(Product product);
	public void deleteProduct(int id);
}
