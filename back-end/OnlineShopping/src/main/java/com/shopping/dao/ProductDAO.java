package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.shopping.model.Product;
import com.shopping.util.HibernateUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
public class ProductDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public ProductDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<Product> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("product_id"));
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return criteria.list();
	}
	
	public List<Product> listOrderByPrice() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("price"));
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public void insert(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Product find(Long id) {
		Product product = null;
		try {
			Session session = sessionFactory.openSession();
			product = (Product) session.get(Product.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return product;
	}

	public void update(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	
}
