package com.shopping.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
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
	}

	public void update(Item item) {
		// TODO Auto-generated method stub

	}

	public void delete(long itemId) {
		// TODO Auto-generated method stub

	}

	public List<Item> findAll(int pageIndex, int pageSize) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Item> findByOrderId(long orderId) {
		return null;
	}
}
