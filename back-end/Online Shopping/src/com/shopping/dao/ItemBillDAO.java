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
	private Connection connection;

	public ItemBillDAO() {
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

	public List<ItemBill> getItemBill() {
		List<ItemBill> itemBillList = new ArrayList<ItemBill>();

		String query = "SELECT * FROM \"item_bill\"";
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				ItemBill itemBill = new ItemBill(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
				itemBillList.add(itemBill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return itemBillList;
	}

	public List<ItemBill> getItemBillByIdBill(String id_bill) {
		List<ItemBill> itemBillList = new ArrayList<ItemBill>();

		String query = "SELECT * FROM \"item_bill\" where id_bill = " + id_bill;
		Statement statement = null;
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResultSet resultSet;
		try {
			resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				ItemBill itemBill = new ItemBill(resultSet.getLong(1), resultSet.getString(2), resultSet.getLong(3), resultSet.getLong(4));
				itemBillList.add(itemBill);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return itemBillList;
	}

	public void insertItemBill(ItemBill itemBill) {
		String query = "INSERT INTO \"item_bill\"(id, name , id_item , id_bill) VALUES(?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, itemBill.getId());
			preparedStatement.setString(2, itemBill.getName());
			preparedStatement.setLong(3, itemBill.getId_item());
			preparedStatement.setLong(4, itemBill.getId_bill());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateItemBill(ItemBill itemBill) {
		String query = "UPDATE \"item_bill\" SET id = ?, name = ?, id_item = ?, id_bill = ? WHERE id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, itemBill.getId());
			preparedStatement.setString(2, itemBill.getName());
			preparedStatement.setLong(3, itemBill.getId_item());
			preparedStatement.setLong(4, itemBill.getId_bill());
			preparedStatement.setLong(5, itemBill.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean deleteItemBill(long id) {
		String query = "DELETE FROM \"item_bill\" WHERE id = ?";
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
