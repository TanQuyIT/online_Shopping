package com.shopping.dao;
import java.util.List;


import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.shopping.model.Order;
import com.shopping.util.HibernateUtil;
@SuppressWarnings({ "unchecked","rawtypes" })
public class OrderDAO {
	SessionFactory sessionFactory;
	

	public OrderDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	
	public List<Order> list() {
		Query query = null;
		try {
			
			Session session = sessionFactory.openSession();
			query = session.createQuery("from Order order by id");
			
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return query.list();
		
	}
	
	public List<Order> listOrderByStatus() {
		Query query = null;
		try {
			Session session = sessionFactory.openSession();
			query = session.createQuery("from Order order by status");
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return query.list();
	}

	public void insert(Order order) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(order);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Order find(Long id) {
		Order order = null;
		try {
			Session session = sessionFactory.openSession();
			order = (Order) session.get(Order.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return order;
	}

	public void update(Order order) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(order);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Order order) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(order);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}
}
