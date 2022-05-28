 package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.helper.UserFoundException;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;



@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepostiory;
	
	@Autowired
	private RoleRepository roleRepository;
	

	//Creating User
	@Override   
	public User createUser(User user, Set<UserRole> userRoles) throws Exception
	{
	    User local_user = this.userRepostiory.findByUsername(user.getUsername());	
		
	    if(local_user != null)
	    {
			 throw new  UserFoundException ();	
		}
		else 
		{
	    	// Create User
			user.setImage("default.png");
			for(UserRole ur:userRoles) {
				  this.roleRepository.save(ur.getRole());
			}
			
			user.getUserRoles().addAll(userRoles);
			local_user = this.userRepostiory.save(user);
	    }
		
		return local_user;
	}


	@Override
	public User getUser(String uName) {
		return this.userRepostiory.findByUsername(uName);
	}


	@Override
	public void deleteUser(Long userId) {
	   this.userRepostiory.deleteById(userId);
	}

}
