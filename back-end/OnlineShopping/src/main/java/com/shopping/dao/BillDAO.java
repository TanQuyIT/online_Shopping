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

@SuppressWarnings({ "unchecked", "deprecation" })
public class BillDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public BillDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public List<Bill> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Bill.class);
			criteria.addOrder(Order.asc("bill_id"));
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public void insert(Bill bill) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(bill);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Bill find(Long id) {
		Bill bill = null;
		try {
			Session session = sessionFactory.openSession();
			bill = (Bill) session.get(Bill.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return bill;
	}

	public void update(Bill bill) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(bill);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Bill bill) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(bill);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

}
