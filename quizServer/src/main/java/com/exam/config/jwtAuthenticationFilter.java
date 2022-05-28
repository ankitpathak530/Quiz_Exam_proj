package com.exam.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;

@Component
public class jwtAuthenticationFilter extends OncePerRequestFilter{

	 @Autowired
	 private UserDetailsService userDetailsService;
	 
	 @Autowired
	 private JwtUtil jwtUtil; 
	 
	 private static Logger LOGGER = LoggerFactory.getLogger(jwtAuthenticationFilter.class);
	
	 
	 
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
	
		
		String requestTokenHeader = request.getHeader("Authorization");
		LOGGER.info("JwtAuthentication Filter Called...");
		LOGGER.info("Token header---->"+requestTokenHeader);
		String username = null;
		String jwtToken = null;
		
		if(requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) 
		{
			jwtToken = requestTokenHeader.substring(7);
			
			try 
			{
			    username = this.jwtUtil.extractUsername(jwtToken);
			}
			catch(ExpiredJwtException e ) 
			{
				e.printStackTrace();
				LOGGER.info("JWT token has expired");
			}
			catch(Exception e) 
			{
				LOGGER.info("Invalid JWT");
				e.printStackTrace();
			}
		}
		else{
			LOGGER.info("Invalid Token , Not starts with bearer string");
		}
		
		
		
	    //Validate Token
	    if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
		{
				final UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			    if(this.jwtUtil.validateToken(jwtToken, userDetails)) 
			    {
			    	//valid token
			    	UsernamePasswordAuthenticationToken  usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());  
			    	usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));		    	
			    	SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			    }
		} 
		else {
                System.out.println("Token is not valid");
		}
			

		filterChain.doFilter(request, response);		
	}



	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		return request.getRequestURI().equals("/generate-token");
	}


	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
