package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Shop;
import com.shopping.util.DBConnection;

public class ShopDAO {
	public List<Shop> getShop() throws SQLException, ClassNotFoundException {
		List<Shop> shopList = new ArrayList<Shop>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"shop\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Shop shop = new Shop(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
			shopList.add(shop);
		}

		return shopList;
	}

	public void insertShop(Shop shop) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"shop\"(id, name, address, phoneNumber, describe, img) VALUES(?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, shop.getId());
		ps.setString(2, shop.getName());
		ps.setString(3, shop.getAddress());
		ps.setString(4, shop.getPhoneNumber());
		ps.setString(5, shop.getDescribe());
		ps.setString(6, shop.getImg());
		
		ps.executeUpdate();
	}
	
	public void updateShop(Shop shop) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"shop\" SET id = ?, name = ?, address = ?, phoneNumber = ?, describe = ?, img = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, shop.getId());
		ps.setString(2, shop.getName());
		ps.setString(3, shop.getAddress());
		ps.setString(4, shop.getPhoneNumber());
		ps.setString(5, shop.getDescribe());
		ps.setString(6, shop.getImg());
		ps.setLong(7, shop.getId());

		ps.executeUpdate();
	}
	
	public Boolean deleteShop(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"shop\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
