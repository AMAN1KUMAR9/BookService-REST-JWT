package com.springboot.rest.services;



 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.springboot.rest.dao.UserDao;
import com.springboot.rest.entity.Users;

@Service
public class UserServices {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private JWTservice jwTservice;
	
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	public Users userRegistration(Users users) {
		return userDao.save(users);
	}

	public String verifyUser(Users userInfo)  {
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userInfo.getUsername(), userInfo.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwTservice.generateToken(userInfo.getUsername());
		}
		return "Failed";
	}
}
