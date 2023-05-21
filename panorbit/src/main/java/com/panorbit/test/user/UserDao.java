package com.panorbit.test.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.stereotype.Component;

import com.panorbit.test.exceptionhandler.InvalidRequestException;
import com.panorbit.test.util.Queries;

@Component
public class UserDao {

	public Boolean addNewUser(UserModel userModel, Connection conn) {
		try (PreparedStatement ps = conn.prepareStatement(Queries.INSERT_NEW_USER)) {
			ps.setString(1, userModel.firstName());
			ps.setString(2, userModel.lastName());
			ps.setString(3, userModel.gender().name());
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
	
}
