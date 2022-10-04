package com.shopping.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.shopping.model.Item;
import com.shopping.util.HibernateUtil;

@Repository
@Transactional
public class ItemDAO {

	@Autowired
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public ItemDAO() {
		// TODO Auto-generated constructor stub
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public void insert(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(item);
		trans.commit();
		session.close();

	}

	public void update(Item item) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		session.update(item);
		transaction.commit();
		session.close();

	}

	public Item find(int id) {
		Session session = sessionFactory.openSession();
		Item item = (Item) session.get(Item.class, id);

		return item;
	}

	public void delete(int itemId) {
		// TODO Auto-generated method stub
		Item item = find(itemId);
		Session session = sessionFactory.openSession();
		Transaction trans = session.beginTransaction();
		session.save(item);
		trans.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	public List<Item> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();

		@SuppressWarnings("deprecation")
		Criteria cr = session.createCriteria(Item.class);
		cr.addOrder(Order.asc("item_id"));

		List<Item> allItems = cr.list();
		List<Item> resultItems = new ArrayList<>();

		for (int i = pageIndex * pageSize; i < pageIndex * (pageSize + 1); i++) {
			resultItems.add(allItems.get(i));
		}

		return resultItems;
		// return null;
	}

	@SuppressWarnings("unchecked")
	public List<Item> findByOrderId(long orderId) {
		Session session = sessionFactory.openSession();
		List<Item> result = (List<Item>) session.get(Item.class, orderId);

		return result;
	}
}
