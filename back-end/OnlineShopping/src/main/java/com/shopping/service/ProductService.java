package com.shopping.service;

import java.util.List;

import com.shopping.model.Product;


public interface ProductService {

	void insert(Product productDTO);

	void update(Product productDTO);

	void delete(long productId);

	Product findById(long productId);

	List<Product> findAll(int pageIndex, int pageSize);

	List<Product> findAllByCategoryId(long categoryId, int pageIndex, int pagesize);
	
	int count();
	
	int countByCategoryId(long categoryId);
	
	List<Product> hotProducts(int pageIndex, int pageSize);
	
	List<Product> featuredProducts(int pageIndex, int pageSize);
	
	List<Product> search(long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex, int pageSize);

	int countBySearch(long categoryId, String pricing, float priceFrom, float priceTo, String text);
}