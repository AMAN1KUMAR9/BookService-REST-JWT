package com.springboot.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.springboot.rest.entity.Users;
import com.springboot.rest.services.UserServices;

@RestController
public class UserSignUpController {
	
	@Autowired
	private UserServices userServices;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	// post request for signup
	@PostMapping("/users/signup")
	public ResponseEntity<Users> userSignup(@RequestBody Users userInfo) {
		//TODO: process POST request
		
		
		try {
			 userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
			 this.userServices.userRegistration(userInfo);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}
		catch (Exception e) {
			return ResponseEntity.internalServerError().build();
		}
		
		
		
		
	}
	// post request for login
	@PostMapping("/users/login")
	public ResponseEntity<String> userLogin(@RequestBody Users userInfo) {
		//TODO: process POST request
		try {
			

			return (ResponseEntity<String>) ResponseEntity.ok(this.userServices.verifyUser(userInfo));


		}
		catch (Exception e) {
			 return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}
		
		
	
	}
	
	

}
