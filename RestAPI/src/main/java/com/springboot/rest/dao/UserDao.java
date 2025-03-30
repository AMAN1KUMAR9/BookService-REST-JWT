package com.springboot.rest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.entity.Users;

@Repository
public interface UserDao extends JpaRepository<Users, Integer> {
	
	public Users findByUsername(String username); // it finds the user by its username

}
