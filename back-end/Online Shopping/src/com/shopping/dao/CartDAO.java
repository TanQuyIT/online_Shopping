package com.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Cart;
import com.shopping.util.DBConnection;

public class CartDAO {
	public List<Cart> getCart() throws SQLException, ClassNotFoundException {
		List<Cart> cartList = new ArrayList<Cart>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"cart\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Cart cart = new Cart(rs.getLong(1), rs.getDate(2), rs.getLong(3), rs.getInt(4));
			cartList.add(cart);
		}

		return cartList;
	}

	public void insertCart(Cart cart) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"cart\"(id, date, id_user, count) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, cart.getId());
		ps.setDate(2, (Date) cart.getDate());
		ps.setLong(3, cart.getId_user());
		ps.setInt(4, cart.getCount());
		
		ps.executeUpdate();
	}

	public void updateCart(Cart cart) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"cart\" SET id = ?, date = ?, id_user = ?, count = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, cart.getId());
		ps.setDate(2, (Date) cart.getDate());
		ps.setLong(3, cart.getId_user());
		ps.setInt(4, cart.getCount());
		ps.setLong(5, cart.getId());
		
		ps.executeUpdate();
	}

	public Boolean deleteCart(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"cart\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
