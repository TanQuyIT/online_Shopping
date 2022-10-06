package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Component;
import org.hibernate.Query;

import com.shopping.model.User;
import com.shopping.util.HibernateUtil;

@Component
@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
public class UserDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public UserDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<User> findAll() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(User.class);
			criteria.addOrder(Order.asc("user_id"));
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public List<User> findAll(int pageIndex, int pageSize) {
		int first = pageIndex * pageSize;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class).setFirstResult(first)
				.setMaxResults(pageSize);
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

	public User findById(Long id) {
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

	public User findByEmailOrPhoneAndPassword(String account, String password, boolean verity) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT u FROM User u WHERE (u.email = '" + account + "' or u.phone = '" + account
				+ "') and u.password = '" + password + "'";
		Query query = session.createQuery(sql);
		return (User) query.uniqueResult();
	}

	public User loadUserByUsername(String account) {
		Session session = sessionFactory.openSession();
		String sql = "SELECT u FROM User u WHERE (u.email = '" + account + "' or u.phone = '" + account + "')"
				+ " and u.verify = true";
		Query query = session.createQuery(sql);
		return (User) query.uniqueResult();
	}

	public int count() {
		Session session = sessionFactory.openSession();
		String sql = "SELECT count(u) FROM User u";
		Query query = session.createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	public User findByEmail(String email) {
		try {
			Session session = sessionFactory.openSession();
			String sql = "SELECT u FROM User u WHERE u.email = :email";
			User user = (User) session.createQuery(sql).setParameter("email", email).uniqueResult();
			session.close();
			return user;
		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
	}

}
