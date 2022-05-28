package com.exam.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.exam.config.JwtUtil;
import com.exam.model.JwtRequest;
import com.exam.model.JwtResponse;
import com.exam.model.User;
import com.exam.service.impl.UserDetailsServiceImpl;

import lombok.AllArgsConstructor;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class AuthenticateController {

	   private static Logger LOGGER = LoggerFactory.getLogger(AuthenticateController.class);
	  
	   private final UserDetailsServiceImpl userDetailsService;
	   private final AuthenticationManager authenticationManager;
	   private final JwtUtil jwtUtil;
	
	   
	   
	   
	   
	   @PostMapping("/generate-token")
	   public ResponseEntity<?> generateToken(@RequestBody JwtRequest jwtRequest) throws Exception
	   { 
		   LOGGER.info("Authentication Controller Generate Token Called...");
		 
		   try{     
			    authenticate(jwtRequest.getUsername(), jwtRequest.getPassword());
		   }catch(UsernameNotFoundException e){
	 			throw new Exception("User not found exception");
		   }
		   
           
		   ///////////////////////////// Authenticated  /////////////////////////////
		   UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
		   String generateToken = this.jwtUtil.generateToken(userDetails);


	       LOGGER.info("Token Successfully Generated."+generateToken);
		   return ResponseEntity.ok(new JwtResponse(generateToken));
	   }
	   
	   
	   

	   @GetMapping("/current-user")
	   public User getCurrentUser(Principal principal) {
		   LOGGER.info("Authentication Controller CurrentUser Called...");
		   return (User)this.userDetailsService.loadUserByUsername(principal.getName());
	   }
	   
	   
	   
	   
	   
	   
	   private void authenticate(String username,String password) throws Exception
	   {	   
		  	LOGGER.error("Authenticate Controller authenticate internal method called...");
		    try {
		    	
		    	authenticationManager.authenticate(new  UsernamePasswordAuthenticationToken(username,password));
		    	
		    	
		    }catch(DisabledException e) {
		    	LOGGER.error("Token  Generation failed.");
		    	throw new Exception("USER DISABLED");
		    }catch(BadCredentialsException e) {
		    	LOGGER.error("Token  Generation failed.");
		    	throw new Exception("Invalid Credential-)"+e.getMessage());
		    }
	   }


	   
	   
}
