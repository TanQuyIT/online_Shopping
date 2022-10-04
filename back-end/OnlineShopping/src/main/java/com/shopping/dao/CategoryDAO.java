package com.shopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.model.Category;
import com.shopping.util.HibernateUtil;

@Repository
@Transactional
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
			criteria.addOrder(Order.asc("category_id"));
			
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return criteria.list();
		
	}
}
