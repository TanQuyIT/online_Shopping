package com.shopping.util;

import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.shopping.model.Bill;
import com.shopping.model.BillProduct;
import com.shopping.model.Cart;
import com.shopping.model.Category;
import com.shopping.model.Item;
import com.shopping.model.Order;
import com.shopping.model.Product;
import com.shopping.model.Role;
import com.shopping.model.Sale;
import com.shopping.model.User;

public class HibernateUtil {
	private static SessionFactory sessionFactory = null;

	static {
		try {
			Logger log = Logger.getLogger("org.hibernate");
			log.setLevel(Level.OFF);
			System.setProperty("org.apache.commons.logging.Log", "org.apache.commons.logging.impl.NoOpLog");
			ResourceBundle rb = ResourceBundle.getBundle("com.shopping.util.oracle");
			Configuration configuration = new Configuration();
			configuration.configure("hibernate.cfg.xml");
			configuration.setProperty("hibernate.connection.url", rb.getString("db.url"));
			configuration.setProperty("hibernate.connection.username", rb.getString("db.username"));
			configuration.setProperty("hibernate.connection.password", rb.getString("db.password"));
			configuration.addPackage("com.shopping.model");
			configuration.addAnnotatedClass(Bill.class);
			configuration.addAnnotatedClass(BillProduct.class);
			configuration.addAnnotatedClass(Cart.class);
			configuration.addAnnotatedClass(Category.class);
			configuration.addAnnotatedClass(Item.class);
			configuration.addAnnotatedClass(Order.class);
			configuration.addAnnotatedClass(Product.class);
			configuration.addAnnotatedClass(Role.class);
			configuration.addAnnotatedClass(Sale.class);
			configuration.addAnnotatedClass(User.class);
			sessionFactory = configuration.buildSessionFactory();
		} catch (Throwable ex) {
			System.err.println("SessionFactory initial creation error." + ex);
		}
	}

	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
}