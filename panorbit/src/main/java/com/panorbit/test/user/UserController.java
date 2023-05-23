package com.panorbit.test.user;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.panorbit.test.dbconfig.ConnectionPool;
import com.panorbit.test.exceptionhandler.DataInsertionFailedException;
import com.panorbit.test.exceptionhandler.InvalidRequestException;
import com.panorbit.test.model.ErrorResponse;
import com.panorbit.test.model.GenericResponse;
import com.panorbit.test.model.GenericResponseModel;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@Validated
@CrossOrigin(origins = "*", maxAge = 3600, allowCredentials = "false", allowedHeaders = "*")
public class UserController {
	
	private static Logger logger = Logger.getLogger(UserController.class);
	
	private ConnectionPool connectionPool;
	private UserService userService;
	
	//constructor dependency injection
	@Autowired
	public UserController(ConnectionPool connectionPool, UserService userService) {
		this.connectionPool = connectionPool;
		this.userService = userService;
	}
	
	
	/***
	 * Sign-up api for adding new user to the database, UserModel is the model class for holding the json data as spring boot provides 
	 * built in functionality for serializing the JSON to java model classes
	 * @param userModel
	 * @return
	 * @throws SQLException
	 */
	@PostMapping("/sign-up")
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> addUser(@Valid @RequestBody UserModel userModel) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			Boolean isNewUserAdded = userService.addNewUser(userModel, conn);
			if (isNewUserAdded) {
				return new GenericResponse<>(new GenericResponseModel<>("Success", null), HttpStatus.OK);
			}
		}
		throw new DataInsertionFailedException("Sign up error");
	}
	
	/***
	 * Verify email is where user sends the login request and the email will verified with
	 * the corresponding database table column and if it is valid, then a otp will be generated
	 * and sent back to the given email
	 * @param emailInput
	 * @return
	 * @throws SQLException
	 * @throws InterruptedException
	 * @throws ExecutionException
	 */
	@PostMapping("/verify-email")
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> userLogin(@Valid @RequestBody EmailInput emailInput) throws SQLException, InterruptedException, ExecutionException {
		try (Connection conn = connectionPool.getConnection()) {
			Boolean isEmailValid = userService.verifyEmailAndGenerateOtp(emailInput.email(), conn);
			if (isEmailValid) {
				logger.info(isEmailValid);
				return new GenericResponse<>(new GenericResponseModel<>("Otp Generated", null), HttpStatus.OK);
			} else {
				return new GenericResponse<>(new GenericResponseModel<>("Failed to generate Otp, invalid email", null), HttpStatus.BAD_REQUEST);
			}
		}
	}
	
	/***
	 * Verify otp rest api verifies the otp from cache and clears the cache and returns a session id
	 * To keep it simple iam using base64 ecoded string as the session id and the same will be returned back
	 * --> emails are configurable inside the resource directory (email.properties)
	 * if otp process goes well, then otp api returns 200 success and on failing will return 400 bad request
	 * @param otpInput
	 * @return
	 * @throws SQLException
	 */
	@PostMapping("/verify-otp")
	public GenericResponse<GenericResponseModel<String, ErrorResponse>> verifyOtp(@Valid @RequestBody OtpInput otpInput) throws SQLException {
		try (Connection conn = connectionPool.getConnection()) {
			Optional<String> isOtpVerified = userService.ValidateOtp(otpInput);
			if (isOtpVerified.isPresent()) {
				return new GenericResponse<>(new GenericResponseModel<>(isOtpVerified.get(), null), HttpStatus.OK);
			}
			throw new InvalidRequestException("Invalid otp");
		}
	}
	
	/**
	 * logout api just clears the session id and returns 200 success
	 * @param session
	 * @return
	 */
	@PostMapping("/logout/{session}")
	public GenericResponse<String> logout(@PathVariable String session) {
		return new GenericResponse<String>(userService.invalidateSession(session), HttpStatus.OK); 
	}
	
}
