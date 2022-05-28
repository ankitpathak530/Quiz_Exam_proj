package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
@AllArgsConstructor
public class UserController {

	
	
	private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	
	
	
	
	@PostMapping("/")
	public User createUser(@RequestBody User user) throws Exception {
		LOGGER.info("User Controller createUser called...");
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));

		Set<UserRole> roles = new HashSet<>();

		Role rol2 = new Role();
		rol2.setRoleId(12L);
		rol2.setRoleName("NORMAL");

		UserRole userRole = new UserRole();
		userRole.setUser(user);
		userRole.setRole(rol2);

		roles.add(userRole);

		return this.userService.createUser(user, roles);
	}

	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") String userId) {
		LOGGER.info("User Controller getUser called...");
		return this.userService.getUser(userId);
	}

	@DeleteMapping("/{userId}")
	public void deleteUser(@PathVariable("userId") Long userId) {
		LOGGER.info("User Controller deleteUser called...");
		this.userService.deleteUser(userId);
	}
}
