package com.shopping.service;

import java.util.List;

import com.shopping.model.Order;


public interface OrderService {

	void insert(Order orderDTO);
	
	void update(Order orderDTO);
	
	void delete(long orderId);
	
	List<Order> findAll(int pageInde, int pageSize);
	
	List<Order> findByBuyer(long userId);
	
	int count();
	
	Order findById(long orderId);
	
}
