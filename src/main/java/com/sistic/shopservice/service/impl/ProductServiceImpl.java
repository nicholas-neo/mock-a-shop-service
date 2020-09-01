package com.sistic.shopservice.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistic.shopservice.model.Product;
import com.sistic.shopservice.repository.ProductRepository;
import com.sistic.shopservice.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public List<Product> listAllProduct() {
		return productRepository.findAll();
	}
	
	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).get();
	}
	
	@Override
	public List<Product> listAllProductByItemType(String itemType) {
		return productRepository.findByItemType(itemType);
	}

	@Override
	public Product addProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);
	}

}
