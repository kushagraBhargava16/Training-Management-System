package com.yash.training.tmp.service;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.LocalBean;
import javax.ejb.Stateful;

import com.yash.training.tmp.domain.User;
import com.yash.training.tmp.util.DBUtil;

/**
 * Session Bean implementation class UserServiceEJB
 */
@Stateful
@LocalBean
public class UserServiceEJB implements UserServiceEJBLocal {

	/**
	 * Default constructor.
	 */
	public UserServiceEJB() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public User checkAuthentication(String username, String password) {
		User user = new User();
		String sql = "select * from user where username='" + username + "' and password='" + password + "'";
		ResultSet resultSet = DBUtil.select(sql);
		try {
			if (resultSet.next()) {
				user.setUsername(resultSet.getString("username"));
				user.setPassword(resultSet.getString("password"));
				user.setName(resultSet.getString("name"));
				user.setContact(resultSet.getString("contact"));
				user.setDesignation(resultSet.getInt("designation"));
				user.setEmailid(resultSet.getString("emailid"));
				user.setUser_id(resultSet.getInt("user_id"));
				user.setRole(resultSet.getInt("role"));
				user.setStatus(resultSet.getInt("status"));

			} else {
				String usernameSql = "select username from user where username='" + username + "'";
				String passwordSql = "select password from user where password='" + password + "'";
				ResultSet resultSetUsername = DBUtil.select(usernameSql);
				ResultSet resultSetPassword = DBUtil.select(passwordSql);
				if (resultSetUsername.next()) {
					user.setUsername(resultSetUsername.getString("username"));

				}
				if (resultSetPassword.next()) {
					user.setPassword(resultSetPassword.getString("password"));

				}
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("failed in userEjb");
			e.printStackTrace();
		}

		return user;
	}

	@Override
	public int registerNewUser(User user) {
		String sql = "insert into user(name,contact,emailid,designation,role,status,username,password) values('"
				+ user.getName() + "','" + user.getContact() + "','" + user.getEmailid() + "','" + user.getDesignation()
				+ "','" + user.getRole() + "','" + user.getStatus() + "','" + user.getUsername() + "','"
				+ user.getPassword() + "')";
		int check = DBUtil.update(sql);
		return check;
	}

}
