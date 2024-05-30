package com.service;

import java.sql.SQLException;

import com.dao.UserDao;
import com.dao.UserDaolmpl;
import com.exception.InvalidCredentialsException;
import com.model.User;

public class UserService {
	UserDao dao = new UserDaolmpl();
	
	public String insert(User user) throws SQLException {
		return dao.save(user);
	}
	
	public int getId(String email) throws SQLException {
		return dao.getId(email);
	}
	
	public User login(String username, String password) throws SQLException, InvalidCredentialsException {
		 
		User user = dao.login(username,password);
		if(user == null) {
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		return user;
	}
}
