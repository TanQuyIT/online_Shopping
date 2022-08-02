package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.ItemCart;
import com.shopping.util.DBConnection;

public class ItemCartDAO {
	public List<ItemCart> getItemCart() throws SQLException, ClassNotFoundException {
		List<ItemCart> itemCartList = new ArrayList<ItemCart>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"item_cart\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			ItemCart itemCart = new ItemCart(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4));
			itemCartList.add(itemCart);
		}

		return itemCartList;
	}

	public void insertItemCart(ItemCart itemCart) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"item_cart\"(id, name , id_item , id_cart) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, itemCart.getId());
		ps.setString(2, itemCart.getName());
		ps.setLong(3, itemCart.getId_item());
		ps.setLong(4, itemCart.getId_cart());

		ps.executeUpdate();
	}

	public void updateItemCart(ItemCart itemCart) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"item_cart\" SET id = ?, name = ?, id_item = ?, id_cart = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, itemCart.getId());
		ps.setString(2, itemCart.getName());
		ps.setLong(3, itemCart.getId_item());
		ps.setLong(4, itemCart.getId_cart());
		ps.setLong(5, itemCart.getId());

		ps.executeUpdate();
	}

	public Boolean deleteItemCart(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"item_cart\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
