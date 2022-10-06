package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Component;

import com.shopping.model.Role;
import com.shopping.util.HibernateUtil;

@Component
@SuppressWarnings({ "unchecked", "deprecation" })
public class RoleDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public RoleDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}
	
	public List<Role> findAll() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Role.class);
			return criteria.list();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return null;
	}

	public void insert(Role role) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(role);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Role find(Long id) {
		Role role = null;
		try {
			Session session = sessionFactory.openSession();
			role = (Role) session.get(Role.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return role;
	}

	public void update(Role role) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(role);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Role role) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(role);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

}
