package com.panorbit.test.user;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.panorbit.test.dbconfig.ConnectionPool;
import com.panorbit.test.exceptionhandler.DataInsertionFailedException;
import com.panorbit.test.model.ErrorResponse;
import com.panorbit.test.model.GenericResponse;
import com.panorbit.test.model.GenericResponseModel;

import jakarta.validation.Valid;

@RestController
@Validated
public class UserController {
	
	private ConnectionPool connectionPool;
	private UserService userService;
	
	@Autowired
	public UserController(ConnectionPool connectionPool, UserService userService) {
		this.connectionPool = connectionPool;
		this.userService = userService;
	}
	
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> addUser(@Valid @RequestBody UserModel userModel) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			Boolean isNewUserAdded = userService.addNewUser(userModel, conn);
			if (isNewUserAdded) {
				return new GenericResponse<>(new GenericResponseModel<>("Success", null), HttpStatus.OK);
			}
		}
		throw new DataInsertionFailedException("Sign up error");
	}
	
	//TODO: yet to be implemented
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> userLogin() {
		return null;
	}
	
	
}
