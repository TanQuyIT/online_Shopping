package com.shopping.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.Query;

import com.shopping.model.Product;
import com.shopping.util.HibernateUtil;

@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
public class ProductDAO {
	SessionFactory sessionFactory;
	Criteria criteria = null;

	public ProductDAO() {
		sessionFactory = HibernateUtil.getSessionFactory();
	}

	public List<Product> list() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("product_id"));
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return criteria.list();
	}
	
	public List<Product> listOrderByPrice() {
		try {
			Session session = sessionFactory.openSession();
			criteria = session.createCriteria(Product.class);
			criteria.addOrder(Order.asc("price"));
		} catch (HibernateException e) {
			// TODO: handle exception
			System.out.println(e.toString());
		}
		return criteria.list();
	}

	public void insert(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.save(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public Product findById(Long id) {
		Product product = null;
		try {
			Session session = sessionFactory.openSession();
			product = (Product) session.get(Product.class, id);
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
		return product;
	}

	public void update(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.update(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public void delete(Product product) {
		try {
			Session session = sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();
			session.delete(product);
			transaction.commit();
			session.close();
		} catch (HibernateException e) {
			System.out.println(e.toString());
		}
	}

	public List<Product> findAll(int pageIndex, int pageSize) {
		int first = pageIndex * pageSize;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Product.class).setFirstResult(first).setMaxResults(pageSize);
		return criteria.list();
	}

	public List<Product> findAllByCategoryId(long catgoryId, int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = " + catgoryId;
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	public int count() {
		String sql = "SELECT COUNT(p) FROM Product p";
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	public int countByCategoryId(long categoryId) {
		String sql = "SELECT COUNT(p) FROM Product p where p.category.categoryId = " + categoryId; 
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}

	public List<Product> hotProducts(int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p ORDER BY p.price DESC";
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	public List<Product> featuredProducts(int pageIndex, int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = 6 ORDER BY p.price DESC";
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	public List<Product> search(long categoryId, String pricing, float priceFrom, float priceTo, String sort, String text, int pageIndex,
			int pageSize) {
		String sql = "SELECT p FROM Product p WHERE p.category.categoryId = " + categoryId;
		if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
			sql += " and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom + " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
		}
		
		if (text != null) {
			sql += " and p.productName like '%" + text + "%'";
		}
		
		if (sort != null && !sort.equals("default")) {
			sql += " ORDER BY (p.price - (p.price * p.sale.salePercent / 100)) " + sort;
		}
		
		int first = pageIndex * pageSize;
		Query query = sessionFactory.getCurrentSession().createQuery(sql).setFirstResult(first).setMaxResults(pageSize);
		return query.list();
	}

	public int countBySearch(long categoryId, String pricing, float priceFrom, float priceTo, String text) {
		String sql = "SELECT COUNT(p) FROM Product p where p.category.categoryId = " + categoryId; 
		
		if (pricing != null && !pricing.equals("default") && !pricing.equals("")) {
			sql += " and ((p.price - (p.price * p.sale.salePercent / 100)) >= " + priceFrom + " and (p.price - (p.price * p.sale.salePercent / 100)) <= " + priceTo + ")";
		}
		
		if (text != null) {
			sql += " and p.productName like '%" + text + "%'";
		}
		Query query = sessionFactory.getCurrentSession().createQuery(sql);
		long count = (long) query.uniqueResult();
		return (int) count;
	}
}
