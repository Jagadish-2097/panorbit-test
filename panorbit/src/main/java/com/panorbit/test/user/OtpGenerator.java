package com.panorbit.test.user;

import java.util.Base64;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

@Service
public class OtpGenerator {

	private static Logger logger = Logger.getLogger(OtpGenerator.class);
	private LoadingCache<String, String> otpCache;

	/**
	 * Constructor configuration.
	 */
	public OtpGenerator() {
		super();

		otpCache = CacheBuilder.newBuilder().expireAfterWrite(60, TimeUnit.MINUTES)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String key) throws Exception {
						return null;
					}
				
				});
	}

	/**
	 * Method for generating OTP and put it in cache.
	 *
	 * @param key - cache key
	 * @return cache value (generated OTP number)
	 */
	public Integer generateOTP(String key) {
		Random random = new Random();
		int OTP = 100000 + random.nextInt(900000);
		otpCache.put(key, String.valueOf(OTP));
		logger.info("Inside OtpGenerator:generateOTP:Generated Otp is:  " + otpCache.toString());
		return OTP;
	}

	/**
	 * Method for getting OTP value by key.
	 *
	 * @param key - target key
	 * @return OTP value
	 */
	public String getOPTByKey(String key) {
		logger.info("Inside OtpGenerator:getOPTByKey:Passed Key is:  " + key);
		return otpCache.getIfPresent(key);
	}

	/**
	 * Method for removing key from cache.
	 *
	 * @param key - target key
	 */
	public void clearOTPFromCache(String key) {
		logger.info("Inside OtpGenerator:clearOTPFromCache:Cleared the Cache For the Key:  " + key);
		otpCache.invalidate(key);
	}
	
	public void updateSessionInCache(String value) {
		logger.info("session id before update "+Base64.getEncoder().encodeToString(value.getBytes()).toString());
		otpCache.put(Base64.getEncoder().encodeToString(value.getBytes()).toString(), value);
		logger.info("updated session in cache "+otpCache.toString());
	}

}
