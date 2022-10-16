package com.ty.foodappapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.foodappapi.dto.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	public User findByEmailAndPassword(String email, String password);
}
