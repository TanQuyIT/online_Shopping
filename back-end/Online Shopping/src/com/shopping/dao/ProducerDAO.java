package com.shopping.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.shopping.model.Producer;
import com.shopping.util.DBConnection;

public class ProducerDAO {
	public List<Producer> getProducer() throws SQLException, ClassNotFoundException {
		List<Producer> producerList = new ArrayList<Producer>();

		Connection conn = DBConnection.getConnection();
		String query = "SELECT * FROM \"producer\"";
		Statement statement = conn.createStatement();
		ResultSet rs = statement.executeQuery(query);

		while (rs.next()) {
			Producer producer = new Producer(rs.getLong(1), rs.getString(2));
			producerList.add(producer);
		}

		return producerList;
	}

	public void insertProducer(Producer producer) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "INSERT INTO \"producer\"(id, name) VALUES(?,?)";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, producer.getId());
		ps.setString(2, producer.getName());

		ps.executeUpdate();
	}
	
	public void updateProducer(Producer producer) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "UPDATE \"producer\" SET id = ?, name = ? WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, producer.getId());
		ps.setString(2, producer.getName());
		ps.setLong(3, producer.getId());

		ps.executeUpdate();
	}
	
	public Boolean deleteProducer(long id) throws ClassNotFoundException, SQLException {
		Connection conn = DBConnection.getConnection();
		String query = "DELETE FROM \"producer\" WHERE id = ?";
		PreparedStatement ps = conn.prepareStatement(query);
		ps.setLong(1, id);
		int i = ps.executeUpdate();

		if (i != 0) {
			return true;
		}
		return false;
	}
}
