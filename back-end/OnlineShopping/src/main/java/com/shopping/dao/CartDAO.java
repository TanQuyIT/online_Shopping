package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.shopping.model.Cart;
import com.shopping.util.HibernateUtil;

public class CartDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public CartDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@SuppressWarnings({ "unchecked", "deprecation"})
	public List<Cart> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Cart.class);
			criteria.addOrder(Order.asc("cart_id"));
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public void insert(Cart cart) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(cart);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Cart find(Long id) {
		Cart bill = null;
		try {
			Session session = sessionFactory.openSession();
			bill = (Cart) session.get(Cart.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return bill;
	}

	public void update(Cart cart) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(cart);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Cart cart) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(cart);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}
}
