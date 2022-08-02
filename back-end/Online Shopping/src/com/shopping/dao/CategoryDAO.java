package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Category;
import com.shopping.util.DBConnection;

public class CategoryDAO {
	public List<Category> getCategory() throws SQLException, ClassNotFoundException {
		List<Category> categoryList = new ArrayList<Category>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"category\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Category category = new Category(rs.getLong(1), rs.getString(2));
			categoryList.add(category);	
		}

		return categoryList;
	}

	public void insertCategory(Category category) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"category\"(id, name) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, category.getId());
		ps.setString(2, category.getName());

		ps.executeUpdate();
	}

	public void updateCategory(Category category) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"category\" SET id = ?, name = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, category.getId());
		ps.setString(2, category.getName());
		ps.setLong(3, category.getId());

		ps.executeUpdate();
	}

	public Boolean deleteCategory(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"category\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
