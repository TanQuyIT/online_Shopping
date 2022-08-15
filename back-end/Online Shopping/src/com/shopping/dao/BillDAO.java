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
	private Connection connection;

	public BillDAO() {
		try {
			connection = DBConnection.getConnection();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public List<Bill> getBill(){
		List<Bill> billList = new ArrayList<Bill>();

		String query = "SELECT * FROM \"bill\"";
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				Bill bill = new Bill(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getLong(6), resultSet.getDate(7), resultSet.getInt(8));
				billList.add(bill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return billList;
	}

	public Bill getBillById(String id) {
		Bill bill = null;

		String query = "SELECT * FROM \"bill\" where id = " + id;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				bill = new Bill(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getLong(6), resultSet.getDate(7), resultSet.getInt(8));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return bill;
	}
	
	public List<Bill> getBillByIdUser(String id_user){
		List<Bill> billList = new ArrayList<Bill>();

		String query = "SELECT * FROM \"bill\" where id_user = " + id_user;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet = null;
		try {
			resultSet = statement.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			while (resultSet.next()) {
				Bill bill = new Bill(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getString(4), resultSet.getString(5),
						resultSet.getLong(6), resultSet.getDate(7), resultSet.getInt(8));
				billList.add(bill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return billList;
	}

	public void insertBill(Bill bill){
		String query = "INSERT INTO \"bill\"(id, name, id_user, address , phoneNumber , total , date , status) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, bill.getId());
			preparedStatement.setString(2, bill.getName());
			preparedStatement.setLong(3, bill.getId_user());
			preparedStatement.setString(4, bill.getAddress());
			preparedStatement.setString(5, bill.getPhoneNumber());
			preparedStatement.setLong(6, bill.getTotal());
			preparedStatement.setDate(7, (Date) bill.getDate());
			preparedStatement.setInt(8, bill.getStatus());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateBill(Bill bill){
		String query = "UPDATE \"bill\" SET id = ?, name = ?, id_user = ?, address = ?, phoneNumber = ?, total = ?, date = ?, status = ? WHERE id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, bill.getId());
			preparedStatement.setString(2, bill.getName());
			preparedStatement.setLong(3, bill.getId_user());
			preparedStatement.setString(4, bill.getAddress());
			preparedStatement.setString(5, bill.getPhoneNumber());
			preparedStatement.setLong(6, bill.getTotal());
			preparedStatement.setDate(7, (Date) bill.getDate());
			preparedStatement.setInt(8, bill.getStatus());
			preparedStatement.setLong(9, bill.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean deleteBill(long id){
		String query = "DELETE FROM \"bill\" WHERE id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, id);
			int i = preparedStatement.executeUpdate();
			if (i != 0) {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}
}
