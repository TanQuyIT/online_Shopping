package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.shopping.model.BillProduct;
import com.shopping.util.HibernateUtil;

@Component
public class BillProductDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public BillProductDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	@SuppressWarnings({ "unchecked", "deprecation"})
	public List<BillProduct> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(BillProduct.class);
			return criteria.list();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public void insert(BillProduct billProduct) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(billProduct);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public BillProduct find(Long id) {
		BillProduct bill = null;
		try {
			Session session = sessionFactory.openSession();
			bill = (BillProduct) session.get(BillProduct.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return bill;
	}

	public void update(BillProduct billProduct) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(billProduct);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(BillProduct billProduct) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(billProduct);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

}
