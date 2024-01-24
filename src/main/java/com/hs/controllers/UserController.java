package com.hs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hs.entities.User;
import com.hs.services.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
		
	}
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable Long id){
		 Optional<User> user = userService.getUserbyId(id);
		 return user;
	}
	
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {
		userService.updateUserById(id, user);
		return user;
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteById(@PathVariable Long id) {
		 userService.deleteById(id);
	}
	
	@GetMapping("/users/byusername/{username}")
	public User getUserById(@PathVariable String username){
		 User user = userService.getUserbyUserName(username);
		 return user;
	}
}
