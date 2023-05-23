package com.panorbit.test.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.panorbit.test.exceptionhandler.InvalidRequestException;
import com.panorbit.test.util.Queries;

@Component
public class UserDao {
	
	private static Logger logger=Logger.getLogger(UserDao.class);

	public Boolean addNewUser(UserModel userModel, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.INSERT_NEW_USER)) {
			ps.setString(1, userModel.firstName());
			ps.setString(2, userModel.lastName());
			ps.setString(3, userModel.gender().gender);
			ps.setString(4, userModel.email());
			ps.setString(5, userModel.phone());
			Integer isInserted = ps.executeUpdate();
			if (isInserted > 0) {
				return true;
			}
		} catch (SQLIntegrityConstraintViolationException e) {
			throw new InvalidRequestException("Email is already registered");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Boolean verifyEmail(String email, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.GET_USER_BY_EMAIL)) {
			ps.setString(1, email);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next() && rs.getString("Email").trim().equals(email.trim())) {
					logger.info("Verify email success");
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
}
