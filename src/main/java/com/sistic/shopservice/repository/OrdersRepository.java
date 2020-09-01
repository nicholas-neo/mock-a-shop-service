package com.sistic.shopservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sistic.shopservice.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, String>{

}
