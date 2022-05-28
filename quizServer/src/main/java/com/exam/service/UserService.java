package com.exam.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;


public interface UserService {
    
	
	//Creating a user
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//Get user by username
	public User getUser(String uName);
	
	//Delete User by username
	public void deleteUser(Long userId);
	
}
