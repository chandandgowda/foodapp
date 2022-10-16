package com.ty.foodappapi.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.repository.UserRepository;

@Repository
public class UserDao {
	@Autowired
	UserRepository repository;
	
	public User saveUser(User user) {
		return repository.save(user);
	}
	
	public User upateUser(int id, User user) {
		Optional<User> opt = repository.findById(id);
		if(opt.isPresent()) {
			User u = opt.get();
			return u;
		}else {
			return null;
		}
	}
	
	public User validateUser(String email, String password) {
		return repository.findByEmailAndPassword(email, password);
		}
	
	public String deleteUser(int id) {
		Optional<User> opt = repository.findById(id);
		if(opt.isPresent()) {
			User user = opt.get();
			repository.delete(user);
			return "Deleted Successfully";
		}else {
			return "ID is not Exist";
		}
	}
	
	public User getUserById(int id) {
		Optional<User> opt = repository.findById(id);
		if(opt.isPresent()) {
			User u = opt.get();
			return u;
		}else {
			return null;
		}
	}
	
	public List<User> getAllUser(){
		return repository.findAll();
	}
}
