package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.ItemDAO;
import com.shopping.model.Item;
import com.shopping.model.Order;
import com.shopping.model.Product;
import com.shopping.model.Sale;
import com.shopping.service.ItemService;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

	@Autowired
	private ItemDAO itemDao;
	
	@Override
	public void insert(Item item) {
		
		Product product = new Product();
		product.setProductId(item.getProduct().getProductId());
		Order order = new Order();
		order.setOrderId(item.getOrder().getOrderId());
		
		Item item1 = new Item();
		item1.setItemId(item.getItemId());
		item1.setProduct(product);
		item1.setUnitPrice(item.getUnitPrice());
		item1.setQuantity(item.getQuantity());
		item1.setOrder(order);
		
		itemDao.insert(item1);
	}

	@Override
	public void update(Item item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long itemId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Item> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Item> findByOrderId(long orderId) {
		List<Item> items = itemDao.findByOrderId(orderId);
		List<Item> itemDTOs = new ArrayList<>();
		for (Item item : items) {
			
			Order orderDTO = new Order();
			orderDTO.setOrderId(item.getOrder().getOrderId());
			
			Sale saleDTO = new Sale();
			saleDTO.setSaleId(item.getProduct().getSale().getSaleId());
			saleDTO.setSalePercent(item.getProduct().getSale().getSalePercent());
			
			Product productDTO = new Product();
			productDTO.setProductId(item.getProduct().getProductId());
			productDTO.setImage(item.getProduct().getImage());
			productDTO.setProductName(item.getProduct().getProductName());
			productDTO.setPrice(item.getProduct().getPrice());
			productDTO.setSale(saleDTO);
			
			Item itemDTO = new Item();
			itemDTO.setItemId(item.getItemId());
			itemDTO.setOrder(orderDTO);
			itemDTO.setProduct(productDTO);
			itemDTO.setQuantity(item.getQuantity());
			itemDTO.setUnitPrice(item.getUnitPrice());
			
			itemDTOs.add(itemDTO);
		}
		return itemDTOs;
	}

}