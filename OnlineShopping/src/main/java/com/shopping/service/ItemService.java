package com.shopping.service;

import java.util.List;

import com.shopping.model.Item;


public interface ItemService {

	void insert(Item itemDTO);
	
	void update(Item itemDTO);
	
	void delete(long itemId);
	
	List<Item> findAll(int pageIndex, int pageSize);
	
	List<Item> findByOrderId(long orderId);
}
