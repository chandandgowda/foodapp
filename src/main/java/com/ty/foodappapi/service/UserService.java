package com.ty.foodappapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ty.foodappapi.dao.UserDao;
import com.ty.foodappapi.dto.Login;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.exception.IdnotFoundException;
import com.ty.foodappapi.exception.InvalidCredentialsException;
import com.ty.foodappapi.repository.UserRepository;
import com.ty.foodappapi.util.ResponseStructure;

@Service
public class UserService {
	@Autowired
	UserDao dao;
	@Autowired
	UserRepository repository;
	
	public ResponseEntity<ResponseStructure<User>> saveUser(User user){
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		responseStructure.setStatus(HttpStatus.CREATED.value());
		responseStructure.setMessage("SUCCESSFULLY INSERTED");
		responseStructure.setData(dao.saveUser(user));
		return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);	
	}
	
	public ResponseEntity<ResponseStructure<User>> updateUser(int id, User user){
		Optional<User> opt = repository.findById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if(opt.isPresent()) {
			User us = opt.get();
			us.setName(user.getName());
			us.setEmail(user.getEmail());
			us.setGender(user.getGender());
			us.setPassword(user.getPassword());
			us.setPhone(user.getPhone());
			us.setRole(user.getRole());
			responseStructure.setStatus(HttpStatus.CREATED.value());
			responseStructure.setMessage("SUCCESSFULLY INSERTED");
			responseStructure.setData(dao.saveUser(us));
			return new ResponseEntity<>(responseStructure, HttpStatus.CREATED);	
		}else {
			throw new IdnotFoundException("Given ID: "+id+", does not Exist");
		}
	}
	public ResponseEntity<ResponseStructure<User>> validateUser(Login login){
		User u = dao.validateUser(login.getEmail(), login.getPassword());
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if(u!=null){
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESSFULLY INSERTED");
		responseStructure.setData(u);
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		} else {
			throw new InvalidCredentialsException("Given credentials are invalid "+ u);
//			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
//			responseStructure.setMessage("NOT FOUND");
//			responseStructure.setData(null);
//			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> deleteUser(int id){
		Optional<User> opt = repository.findById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if(opt.isPresent()) {
			User us =opt.get();
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("DELETED SUCCESSFULLY");
			repository.delete(us);
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("NOT FOUND");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<ResponseStructure<User>> getUser(int id) {
		Optional<User> opt = repository.findById(id);
		ResponseStructure<User> responseStructure = new ResponseStructure<User>();
		if(opt.isPresent()) {
			responseStructure.setStatus(HttpStatus.OK.value());
			responseStructure.setMessage("UCCESS");
			responseStructure.setData(opt.get());
			return new ResponseEntity<>(responseStructure, HttpStatus.OK);
		}else {
			responseStructure.setStatus(HttpStatus.NOT_FOUND.value());
			responseStructure.setMessage("ID NOT FOUND");
			responseStructure.setData(null);
			return new ResponseEntity<>(responseStructure, HttpStatus.NOT_FOUND);
		}
	}
	
	
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser() {
		ResponseStructure<List<User>> responseStructure = new ResponseStructure<List<User>>();
		responseStructure.setStatus(HttpStatus.OK.value());
		responseStructure.setMessage("SUCCESS");
		responseStructure.setData(repository.findAll());
		return new ResponseEntity<>(responseStructure, HttpStatus.OK);
	}
}
