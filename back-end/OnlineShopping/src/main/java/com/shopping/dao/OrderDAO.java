package com.shopping.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.shopping.model.Order;
import com.shopping.util.HibernateUtil;

@Component
@SuppressWarnings({ "unchecked", "rawtypes", "deprecation" })
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

	public List<Order> findAll(int pageIndex, int pageSize) {
		String sql = "SELECT o FROM Order o ORDER BY o.buyDate DESC";
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
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

	public List<Order> findByBuyer(long userId) {
		String sql = "SELECT o FROM Order o WHERE user_id = " + userId + " ORDER BY o.buyDate DESC";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		return query.list();
	}

	public int count() {
		String sql = "SELECT COUNT(o) FROM Order o";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	public Order findById(long orderId) {
		return (Order) sessionFactory.getCurrentSession().get(Order.class, orderId);
	}

}
