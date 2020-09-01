package com.sistic.shopservice.dto;

import java.util.ArrayList;
import java.util.List;

public class Checkout {
	
	private String address;
	
	private String postalCode;
	
	private String email;
	
	private String phoneNumber;
	
	private List<CartItem> cartItem = new ArrayList<>();

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<CartItem> getCartItem() {
		return cartItem;
	}

	public void setCartItem(List<CartItem> cartItem) {
		this.cartItem = cartItem;
	}

	@Override
	public String toString() {
		return "Checkout [address=" + address + ", postalCode=" + postalCode + ", email=" + email + ", phoneNumber="
				+ phoneNumber + ", cartItem=" + cartItem + "]";
	}
}
