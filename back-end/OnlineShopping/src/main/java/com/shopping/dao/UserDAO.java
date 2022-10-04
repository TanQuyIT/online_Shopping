package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;

import com.shopping.model.User;
import com.shopping.util.HibernateUtil;

@SuppressWarnings({ "unchecked", "deprecation" })
public class UserDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public UserDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public List<User> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(User.class);
			criteria.addOrder(Order.asc("user_id"));
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public void insert(User user) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(user);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public User find(Long id) {
		User user = null;
		try {
			Session session = sessionFactory.openSession();
			user = (User) session.get(User.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return user;
	}

	public void update(User user) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(user);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(User user) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(user);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

}
