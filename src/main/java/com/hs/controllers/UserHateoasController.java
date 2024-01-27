package com.hs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.hs.entities.User;
import com.hs.exception.UserNotFoundExcpetion;
import com.hs.reporsitory.UserRepository;
import com.hs.services.UserService;

import jakarta.validation.constraints.Min;

@RestController
@RequestMapping(value = "/hateoas/users")
@Validated
public class UserHateoasController {
	
	@Autowired
	private UserService userService;
	
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}

	
	@GetMapping("/{id}")
	public User getUserById(@PathVariable @Min(1) Long id){
		 Optional<User> userOPtional;
		try {
			User user = userService.getUserbyId(id).get();
			
//			Link  selflink = linkTo(UserHateoasController.class)
//			          .getOrderById(id, user.getId())).withSelfRel();
//			
			return user;
		} catch (UserNotFoundExcpetion e) {
			// TODO Auto-generated catch block
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
		 
	}
	
}
