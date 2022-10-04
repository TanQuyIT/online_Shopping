package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.shopping.model.Bill;
import com.shopping.util.HibernateUtil;
public class ProductDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;
	
	public ProductDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
}
