package com.sistic.shopservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sistic.shopservice.dto.Checkout;
import com.sistic.shopservice.model.Orders;
import com.sistic.shopservice.service.CartService;

@RestController
@RequestMapping("api/cart")
public class CartController {
	
	@Autowired
	private CartService cartService;
	
	@PostMapping("/checkout")
	public ResponseEntity<Orders> placeOrder(@RequestBody Checkout checkout) {
		Orders result = cartService.checkout(checkout);
		return ResponseEntity.status(HttpStatus.CREATED).body(result);
	}
}
