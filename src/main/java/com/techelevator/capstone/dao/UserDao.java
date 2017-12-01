package com.techelevator.capstone.dao;

import java.util.List;

import org.springframework.stereotype.Component;

import com.techelevator.capstone.model.User;


@Component
public interface UserDao {
	public boolean checkUsernameAndPassword(String username, String password);
	public User getUserByUsername(String username);	
	public void updatePassword(String username, String password);
	public void saveUser(String username, String password, String role);
	public void saveUser(String username, String password, String role, int breweryId);
	public void deleteUser(String username);

}
