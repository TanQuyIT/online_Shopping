package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.User;
import com.shopping.util.DBConnection;

public class UserDAO {

	public List<User> getUser() throws SQLException, ClassNotFoundException {
		List<User> userList = new ArrayList<User>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"user\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			User user = new User(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
					rs.getString(6), rs.getString(7), rs.getInt(8));
			userList.add(user);
		}

		return userList;
	}

	public void insertUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"user\"(id, username, password, fullName, email, phoneNumber, address, type) VALUES(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, user.getId());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getFullName());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPhoneNumber());
		ps.setString(7, user.getAddress());
		ps.setInt(8, user.getType());

		ps.executeUpdate();
	}

	public void updateUser(User user) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"user\" SET id = ?, username = ?, password = ?, fullName = ?, email = ?, phoneNumber = ?, address = ?, type = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, user.getId());
		ps.setString(2, user.getUsername());
		ps.setString(3, user.getPassword());
		ps.setString(4, user.getFullName());
		ps.setString(5, user.getEmail());
		ps.setString(6, user.getPhoneNumber());
		ps.setString(7, user.getAddress());
		ps.setInt(8, user.getType());
		ps.setLong(9, user.getId());

		ps.executeUpdate();
	}

	public Boolean deleteUser(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"user\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;

	}
}
