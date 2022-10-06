package com.shopping.dao;

import java.util.List;


import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shopping.model.Category;
import com.shopping.util.HibernateUtil;

@Component
public class CategoryDAO {

	@Autowired
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public CategoryDAO() {
		// TODO Auto-generated constructor stub
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public List<Category> findAll() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Category.class);
			return criteria.list();
			
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return null;
	}
}
