package com.sistic.shopservice.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistic.shopservice.model.Product;
import com.sistic.shopservice.service.ProductService;

@RestController
@RequestMapping("api/products")
public class ProductController {
	
	private static Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.listAllProduct();
		logger.info("All product returned");
		return ResponseEntity.ok(products);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Product> getProduct(@PathVariable int id) {
		Product product = productService.getProductById(id);
		logger.info("Product return, id: " + id);
		return ResponseEntity.ok(product);
	}
	
	@GetMapping("/item_type/{item_type}")
	public ResponseEntity<List<Product>> getAllProductsByItemType(@PathVariable(name = "item_type") String itemType) {
		List<Product> products = productService.listAllProductByItemType(itemType);
		logger.info("Product return by item type: " + itemType);
		return ResponseEntity.ok(products);
	}
	
	@PostMapping
	public ResponseEntity<Product> addProduct(@RequestBody Product product) {
		Product addedProduct = productService.addProduct(product);
		logger.info("Product added");
		return ResponseEntity.status(HttpStatus.CREATED).body(addedProduct);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Product> updateProduct(@PathVariable int id, @RequestBody Product product) {
		product.setId(id);
		Product updatedProduct = productService.updateProduct(product);
		logger.info("Product updated");
		return ResponseEntity.status(HttpStatus.OK).body(updatedProduct);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable int id) {
		productService.deleteProduct(id);
		logger.info("Product deleted, id: " + id);
		return ResponseEntity.status(HttpStatus.OK).body("Deleted");
	}
}
