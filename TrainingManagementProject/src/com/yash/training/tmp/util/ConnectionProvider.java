package com.yash.training.tmp.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	private static String url = "jdbc:mysql://localhost:3306/trainingmanagement";
	private static String user = "root";
	private static String password = "root";
	private static Connection connection;

	static public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
			return connection;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("The problem occured here:" + e.getMessage());
			return connection;
		}

	}

	static public void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
