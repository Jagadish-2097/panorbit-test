package com.panorbit.test.user;

import java.sql.Connection;
import java.util.Base64;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class);

	@Autowired
	private UserDao userDao;

	@Autowired
	private OtpGenerator otpGenerator;

	@Autowired
	private EmailService emailService;

	public Boolean addNewUser(UserModel userModel, Connection conn) {
		return userDao.addNewUser(userModel, conn);
	}

	public Boolean verifyEmailAndGenerateOtp(String email, Connection conn) throws InterruptedException, ExecutionException {
		if (userDao.verifyEmail(email, conn)) {
			CompletableFuture.supplyAsync(() -> generateOtp(email.trim()));
			return true;
		}
		return false;
	}

	public Boolean generateOtp(String key) {
		logger.info("inside the generate otp and the key is "+key);
		Integer otpValue = otpGenerator.generateOTP(key);
		if (otpValue == -1) {
			logger.error("Inside LogInService:generateOtp:OTP generator is not working...");
			return false;
		}

		logger.info("Inside LogInService:generateOtp:Generated OTP: " + otpValue);
		return emailService.sendSimpleMessage(key, otpValue);
	}
	
	public Optional<String> ValidateOtp(OtpInput otpInput) {
		if (otpGenerator.getOPTByKey(otpInput.email().toString()).equals(otpInput.otp().toString())) {
			otpGenerator.clearOTPFromCache(otpInput.otp().toString());
			otpGenerator.updateSessionInCache(otpInput.email());
			return Optional.of(Base64.getEncoder().encodeToString(otpInput.email().getBytes()));
		}
		return Optional.empty();
	}
	
	public String invalidateSession(String session) {
		otpGenerator.clearOTPFromCache(session);
		return "Success";
	}
}
