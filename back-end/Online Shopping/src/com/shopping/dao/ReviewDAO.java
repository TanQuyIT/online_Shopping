package com.shopping.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Review;
import com.shopping.util.DBConnection;

public class ReviewDAO {
	public List<Review> getReview() throws SQLException, ClassNotFoundException {
		List<Review> reviewList = new ArrayList<Review>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"review\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Review review = new Review(rs.getLong(1), rs.getLong(2), rs.getDate(3), rs.getLong(4), rs.getString(5));
			reviewList.add(review);
		}

		return reviewList;
	}

	public void insertReview(Review review) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"review\"(id, id_user , date , id_item , content) VALUES(?,?,?,?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, review.getId());
		ps.setLong(2, review.getId_user());
		ps.setDate(3, (Date) review.getDate());
		ps.setLong(4, review.getId_item());
		ps.setString(5, review.getContent());

		ps.executeUpdate();
	}
	
	public void updateReview(Review review) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"review\" SET id = ?, id_user = ?, date = ?, id_item = ?, content = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, review.getId());
		ps.setLong(2, review.getId_user());
		ps.setDate(3, (Date) review.getDate());
		ps.setLong(4, review.getId_item());
		ps.setString(5, review.getContent());
		ps.setLong(6, review.getId());

		ps.executeUpdate();
	}
	
	public Boolean deleteReview(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"review\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
