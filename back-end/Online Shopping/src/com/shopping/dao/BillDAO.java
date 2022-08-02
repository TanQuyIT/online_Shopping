package com.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Bill;
import com.shopping.util.DBConnection;

public class BillDAO {
	public List<Bill> getBill() throws SQLException, ClassNotFoundException {
		List<Bill> billList = new ArrayList<Bill>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"bill\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Bill bill = new Bill(rs.getLong(1), rs.getString(2), rs.getLong(3), rs.getString(4), rs.getString(5),
					rs.getLong(6), rs.getDate(7), rs.getInt(8));
			billList.add(bill);
		}

		return billList;
	}

	public void insertBill(Bill bill) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"bill\"(id, name, id_user, address , phoneNumber , total , date , status) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, bill.getId());
		ps.setString(2, bill.getName());
		ps.setLong(3, bill.getId_user());
		ps.setString(4, bill.getAddress());
		ps.setString(5, bill.getPhoneNumber());
		ps.setLong(6, bill.getTotal());
		ps.setDate(7, (Date) bill.getDate());
		ps.setInt(8, bill.getStatus());

		ps.executeUpdate();
	}

	public void updateBill(Bill bill) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"bill\" SET id = ?, name = ?, id_user = ?, address = ?, phoneNumber = ?, total = ?, date = ?, status = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, bill.getId());
		ps.setString(2, bill.getName());
		ps.setLong(3, bill.getId_user());
		ps.setString(4, bill.getAddress());
		ps.setString(5, bill.getPhoneNumber());
		ps.setLong(6, bill.getTotal());
		ps.setDate(7, (Date) bill.getDate());
		ps.setInt(8, bill.getStatus());
		ps.setLong(9, bill.getId());

		ps.executeUpdate();
	}

	public Boolean deleteBill(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"bill\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
