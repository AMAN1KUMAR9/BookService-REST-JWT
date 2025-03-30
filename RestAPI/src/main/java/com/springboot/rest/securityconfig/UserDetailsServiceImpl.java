package com.springboot.rest.securityconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.springboot.rest.dao.UserDao;
import com.springboot.rest.entity.Users;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	
	
	@Override // loads user details by username
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users users = userDao.findByUsername(username);
		if(users == null) {
			throw new UsernameNotFoundException("User does not exist");
		}
		return new UserDetailsImpl(users);
	}

}
