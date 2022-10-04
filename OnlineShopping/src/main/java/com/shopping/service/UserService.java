package com.shopping.service;

import java.util.List;

import com.shopping.model.User;

public interface UserService {

	void insert(User userDTO);
	
	void update(User userDTO);
	
	void delete(long userId);
	
	User findById(long userId);
	
	List<User> findAll(int pageIndex, int PageSize);

	User findByEmailOrPhoneAndPassword(String account, String password, boolean verity);
	
	User findByEmail(String email);
	
	int count();
	
}
