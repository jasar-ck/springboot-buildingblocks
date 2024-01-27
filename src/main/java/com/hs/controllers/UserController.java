package com.hs.controllers;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.hs.entities.User;
import com.hs.exception.UserExistsException;
import com.hs.exception.UserNameNotFoundException;
import com.hs.exception.UserNotFoundExcpetion;
import com.hs.services.UserService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@RestController
@Validated
@RequestMapping(value = "/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	@PostMapping
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {
		
		try {
			 userService.createUser(user);
			 HttpHeaders headers = new HttpHeaders();
			 headers.setLocation(builder.path("/usesr/{id}").buildAndExpand(user.getId()).toUri());
			 return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@GetMapping("/{id}")
	public Optional<User> getUserById(@PathVariable @Min(1) Long id){
		 Optional<User> user;
		try {
			user = userService.getUserbyId(id);
			return user;
		} catch (UserNotFoundExcpetion e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		 
	}
	
	@PutMapping("/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {
		try {
			userService.updateUserById(id, user);
			return user;
		} catch (UserNotFoundExcpetion e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable Long id) throws UserNotFoundExcpetion {
		 userService.deleteById(id);
	}
	
	@GetMapping("/byusername/{username}")
	public User getUserByName(@PathVariable String username) throws UserNameNotFoundException{
		User user = userService.getUserbyUserName(username);
		if(user == null) {
			throw new UserNameNotFoundException("Username " + username + " Not Found in the repository");
		}
		 return user;
	}
}
