package com.springboot.rest.securityconfig;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.springboot.rest.entity.Users;

public class UserDetailsImpl implements UserDetails{
	
	private Users users;
	
	

	public UserDetailsImpl(Users users) {
		
		this.users = users;
	}

	@Override //// returns the role of the user as a single element  using it as singleton for single role of user
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		return Collections.singleton(new SimpleGrantedAuthority(users.getRole()));
	}

	@Override
	public String getPassword() {
		
		return users.getPassword();
	}

	@Override
	public String getUsername() {
	
		return users.getUsername();
	}

}
