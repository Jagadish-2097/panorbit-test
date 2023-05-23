package com.panorbit.test.user;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

	private static Logger logger = Logger.getLogger(EmailService.class);

	@Autowired
	JavaMailSender javaMailSender;

	public Boolean sendSimpleMessage(String email, Integer otpValue) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		// mailMessage.setFrom("manursumit@gmail.com");
//	        mailMessage.setReplyTo("chetan.k@infosoftjoin.in");
		mailMessage.setTo(email);
		mailMessage.setSubject("login-OTP");
		mailMessage.setText("Hello " + email.substring(0, email.indexOf('@')) + ", \n Your OTP is: "
				+ otpValue.intValue() + "\n \n Best Regards");

		Boolean isSent = false;
		try {
			javaMailSender.send(mailMessage);
			isSent = true;
			logger.info("Inside EmailService:sendSimpleMessage:Mail Message: " + mailMessage.toString() + " Status: "
					+ isSent);
		} catch (Exception e) {
			logger.error("EmailService:sendSimpleMessage:Sending e-mail error: {}" + e.getMessage());
		}
		return isSent;
	}
}
