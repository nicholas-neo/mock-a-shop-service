package com.sistic.shopservice.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistic.shopservice.dto.CartItem;
import com.sistic.shopservice.dto.Checkout;
import com.sistic.shopservice.model.OrderItem;
import com.sistic.shopservice.model.Orders;
import com.sistic.shopservice.model.Product;
import com.sistic.shopservice.repository.OrdersRepository;
import com.sistic.shopservice.repository.ProductRepository;
import com.sistic.shopservice.service.CartService;

@Service
public class CartServiceImpl implements CartService {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private OrdersRepository ordersRepository;

	@Override
	public Orders checkout(Checkout checkout) {
		
		Orders newOrder = new Orders();
		newOrder.setTrxId(UUID.randomUUID().toString());
		newOrder.setAddress(checkout.getAddress());
		newOrder.setPostalCode(checkout.getPostalCode());
		newOrder.setEmail(checkout.getEmail());
		newOrder.setPhoneNumber(checkout.getPhoneNumber());
		
		BigDecimal totalAmt = BigDecimal.ZERO;
		
		List<OrderItem> orderItems = new ArrayList<>();
		
		for (CartItem i : checkout.getCartItem()) {
			try {
				Product p = productRepository.findById(i.getId()).orElseThrow(Exception::new);
				
				OrderItem oi = new OrderItem();
				BigDecimal linePrice = p.getItemUnitPrice().multiply(new BigDecimal(i.getQuantity()));
				oi.setLinePrice(linePrice);
				oi.setProduct(p);
				
				orderItems.add(oi);
				totalAmt = totalAmt.add(linePrice);
			} catch (Exception e) {
				e.printStackTrace();
				continue;
			}
			
		}
		
		newOrder.setTotalAmount(totalAmt);
		newOrder.setItems(orderItems);
		return ordersRepository.save(newOrder);
	}

}
