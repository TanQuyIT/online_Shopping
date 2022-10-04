package com.shopping.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shopping.dao.CategoryDAO;
import com.shopping.model.Category;
import com.shopping.service.CategoryService;


@Transactional
@Service
public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryDAO categoryDao;
	
	@Override
	public List<Category> findAll() {
		List<Category> categories = categoryDao.findAll();
		List<Category> categoryDTOs = new ArrayList<Category>();
		for (Category category : categories) {
			Category categoryDTO = new Category();
			categoryDTO.setCategoryId(category.getCategoryId());
			categoryDTO.setCategoryName(category.getCategoryName());
			categoryDTOs.add(categoryDTO);
		}
		return categoryDTOs;
	}

}