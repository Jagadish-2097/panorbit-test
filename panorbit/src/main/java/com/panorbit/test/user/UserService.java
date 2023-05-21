package com.panorbit.test.user;

import java.sql.Connection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	public Boolean addNewUser(UserModel userModel, Connection conn) {
		return userDao.addNewUser(userModel, conn);
	}
}
