package com.sistic.shopservice.service;

import com.sistic.shopservice.dto.Checkout;
import com.sistic.shopservice.model.Orders;

public interface CartService {
	
	public Orders checkout(Checkout checkout);
}
