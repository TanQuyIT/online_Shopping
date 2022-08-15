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
	private Connection connection;

	public ReviewDAO() {
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

	public List<Review> getAllReview(){
		List<Review> reviewList = new ArrayList<Review>();

		String query = "SELECT * FROM \"review\"";
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
				Review review = new Review(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDate(3), resultSet.getLong(4), resultSet.getString(5));
				reviewList.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reviewList;
	}

	public void insertReview(Review review){
		String query = "INSERT INTO \"review\"(id, id_user , date , id_item , content) VALUES(?,?,?,?,?)";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, review.getId());
			preparedStatement.setLong(2, review.getId_user());
			preparedStatement.setDate(3, (Date) review.getDate());
			preparedStatement.setLong(4, review.getId_item());
			preparedStatement.setString(5, review.getContent());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void updateReview(Review review){
		String query = "UPDATE \"review\" SET id = ?, id_user = ?, date = ?, id_item = ?, content = ? WHERE id = ?";
		PreparedStatement preparedStatement = null;
		try {
			preparedStatement = connection.prepareStatement(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			preparedStatement.setLong(1, review.getId());
			preparedStatement.setLong(2, review.getId_user());
			preparedStatement.setDate(3, (Date) review.getDate());
			preparedStatement.setLong(4, review.getId_item());
			preparedStatement.setString(5, review.getContent());
			preparedStatement.setLong(6, review.getId());
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean deleteReview(long id){
		String query = "DELETE FROM \"review\" WHERE id = ?";
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

	public Review getReviewById(String id){
		Review review = null;

		String query = "SELECT * FROM \"review\" where id=" + id;
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
				review = new Review(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDate(3), resultSet.getLong(4), resultSet.getString(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return review;
	}

	public List<Review> getReviewByIdUser(String id_user){
		List<Review> reviewList = new ArrayList<Review>();

		String query = "SELECT * FROM \"review\" where id_user = " + id_user;
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
				Review review = new Review(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDate(3), resultSet.getLong(4), resultSet.getString(5));
				reviewList.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reviewList;
	}

	public List<Review> getReviewByIdItem(String id_item){
		List<Review> reviewList = new ArrayList<Review>();

		String query = "SELECT * FROM \"review\" where id_user = " + id_item;
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
				Review review = new Review(resultSet.getLong(1), resultSet.getLong(2), resultSet.getDate(3), resultSet.getLong(4), resultSet.getString(5));
				reviewList.add(review);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return reviewList;
	}
}
