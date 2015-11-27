package com.yash.training.tmp.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {
	static Connection connection;

	private static Connection connectDB() {
		connection = ConnectionProvider.getConnection();
		System.out.println("connection------->"+connection);
		return connection;
	}

	public static void disconDB(Connection connection) throws SQLException {
		connection.close();
	}

	public static ResultSet select(String sql) {
		Connection connection = connectDB();
		ResultSet resultSet = null;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			resultSet = preparedStatement.executeQuery();
			// disconDB(connection);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return resultSet;
	}

	public static int update(String sql) {
		Connection connection = connectDB();
		System.out.println(connection);
		int count = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement(sql);
			count = preparedStatement.executeUpdate();
			disconDB(connection);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}
}
