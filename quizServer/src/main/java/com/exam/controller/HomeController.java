package com.exam.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
	
	 @GetMapping("/index")
	 public String home() {
		 LOGGER.info("Index Page Called.");
		 return "index";
	 }
	 
	 
	 
	 
}
