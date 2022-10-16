package com.ty.foodappapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.foodappapi.dto.Login;
import com.ty.foodappapi.dto.User;
import com.ty.foodappapi.service.UserService;
import com.ty.foodappapi.util.ResponseStructure;

@RestController
public class UserController {
	@Autowired
	UserService service;
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@Validated @RequestBody User user){
		
		return service.saveUser(user);
	}
	@PostMapping("/users/login")
	public ResponseEntity<ResponseStructure<User>> validateUser(@RequestBody Login login){
		return service.validateUser(login);
	}
	@PutMapping("/users")
	public ResponseEntity<ResponseStructure<User>> updateUser( @RequestParam int id,@Validated @RequestBody User user){
		return service.updateUser(id, user);
	}
	@DeleteMapping("/users")
	public ResponseEntity<ResponseStructure<User>> deleteUser(@RequestParam int id){
		return service.deleteUser(id);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> getUser(@PathVariable int id){
		return service.getUser(id);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> getAllUser(){
		return service.getAllUser();
	}
	
}
