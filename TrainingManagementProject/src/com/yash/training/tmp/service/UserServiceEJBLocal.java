package com.yash.training.tmp.service;

import javax.ejb.Local;

import com.yash.training.tmp.domain.User;

@Local
public interface UserServiceEJBLocal {
	public User checkAuthentication(String username,String password);

	public int registerNewUser(User user);
}
