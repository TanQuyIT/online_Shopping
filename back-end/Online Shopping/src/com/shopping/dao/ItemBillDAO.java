package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.ItemBill;
import com.shopping.util.DBConnection;

public class ItemBillDAO {
	public List<ItemBill> getItemBill() throws SQLException, ClassNotFoundException {
		List<ItemBill> itemBillList = new ArrayList<ItemBill>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"item_bill\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			ItemBill itemBill = new ItemBill(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getLong(4));
			itemBillList.add(itemBill);	
		}

		return itemBillList;
	}

	public void insertItemBill(ItemBill itemBill) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"item_bill\"(id, name , id_item , id_bill) VALUES(?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, itemBill.getId());
		ps.setString(2, itemBill.getName());
		ps.setLong(3, itemBill.getId_item());
		ps.setLong(4, itemBill.getId_bill());

		ps.executeUpdate();
	}

	public void updateItemBill(ItemBill itemBill) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"item_bill\" SET id = ?, name = ?, id_item = ?, id_bill = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, itemBill.getId());
		ps.setString(2, itemBill.getName());
		ps.setLong(3, itemBill.getId_item());
		ps.setLong(4, itemBill.getId_bill());
		ps.setLong(5, itemBill.getId());

		ps.executeUpdate();
	}

	public Boolean deleteItemBill(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"item_bill\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
