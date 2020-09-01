package com.sistic.shopservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistic.shopservice.model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>{
	
	List<Product> findByItemType(String itemType);
}
