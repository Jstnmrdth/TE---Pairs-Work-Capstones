package com.techelevator.capstone.dao;

import java.util.List;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.Brewery;
import com.techelevator.capstone.model.User;

@Component
public class JDBCUserDao implements UserDao {
	
	private JdbcTemplate jdbcTemplate;
	private BreweryDao breweryDao;
	private PasswordHasher passwordHasher;
	
	@Autowired
	public JDBCUserDao(BreweryDao breweryDao, DataSource dataSource, PasswordHasher passwordHasher) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.breweryDao = breweryDao;
		this.passwordHasher = passwordHasher;
		
	}
	
	@Override
	public User getUserByUsername(String username) {
		String sqlSelectUser = "SELECT * FROM users WHERE username = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUser, username);
		if(results.next()) {
			return mapUserToRow(results);
		}
		return null;
	}

	@Override
	public boolean checkUsernameAndPassword(String username, String password) {
		String sqlSelectUser = "SELECT * FROM users WHERE UPPER(username) = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSelectUser, username.toUpperCase());
		
		if(results.next()) {
			String storedSalt = results.getString("salt");
			String storedPassword = results.getString("password");
			String hashedPassword = passwordHasher.computeHash(password, Base64.decode(storedSalt));
			return storedPassword.equals(hashedPassword);
		}
		return false;
	}
	
	@Override
	public void updatePassword(String username, String password) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		String sqlUpdatePassword = "UPDATE users SET password = ?, salt = ? WHERE username = ?";
		jdbcTemplate.update(sqlUpdatePassword, hashedPassword, saltString, username);
	}
	
	private User mapUserToRow(SqlRowSet results) {
		User thisUser = new User();
		thisUser.setUserId(results.getInt("user_id"));
		thisUser.setUsername(results.getString("username"));
		thisUser.setPassword(results.getString("password"));
		thisUser.setRole(results.getString("role"));
		if(results.getInt("brewery_id") != 0) {
			Brewery thisBrewery = breweryDao.getBreweryByBreweryId(results.getInt("brewery_id"));
			thisUser.setBrewery(thisBrewery);
		}
		
		return thisUser;
		
	}

	@Override
	public void saveUser(String username, String password, String role) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		String sqlAddUser = "INSERT INTO users (username, password, salt, role) VALUES (?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddUser, username, hashedPassword, saltString, role);
		
	}
	
	@Override
	public void saveUser(String username, String password, String role, int breweryId) {
		byte[] salt = passwordHasher.generateRandomSalt();
		String hashedPassword = passwordHasher.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		String sqlAddUser = "INSERT INTO users (username, password, salt, role, brewery_id) VALUES (?, ?, ?, ?, ?)";
		jdbcTemplate.update(sqlAddUser, username, hashedPassword, saltString, role, breweryId);
		
	}

	@Override
	public void deleteUser(String username) {
		User thisUser = getUserByUsername(username);
		String sqlDeleteUser = "DELETE FROM users WHERE user_id = ?";
		jdbcTemplate.update(sqlDeleteUser, thisUser.getUserId());
		
	}

}
