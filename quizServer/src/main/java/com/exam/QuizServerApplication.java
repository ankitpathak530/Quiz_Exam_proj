package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
@EnableAsync
public class QuizServerApplication implements CommandLineRunner{

	@Autowired
	private UserService userService;
	
	public static void main(String[] args)  {
		SpringApplication.run(QuizServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.print("starting code");
		
//		User user = new User("ankit555","Bankit","Pathak","akp1@gmail.com","123","8089898989","good boy","default.png");
//		
//		
//		Role rol1 = new Role();
//		  rol1.setRoleId(12L);
//		  rol1.setRoleName("ADMIN");
//	  
//		
//	      
//		  
//		  
//		Set<UserRole> userRoleSet = new HashSet<>();
//		
//				UserRole userRole1 = new UserRole();
//			    	userRole1.setRole(rol1);
//				    userRole1.setUser(user);
//				
//				    userRole2.setUser(user);
//				    
//		userRoleSet.add(userRole1);
//		userRoleSet.add(userRole2);
//		
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		
//		System.out.println(user1.getUsername());
		
	}

}
