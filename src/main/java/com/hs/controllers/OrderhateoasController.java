package com.hs.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.entities.Order;
import com.hs.entities.User;
import com.hs.exception.UserNotFoundExcpetion;
import com.hs.reporsitory.OrderRepository;
import com.hs.reporsitory.UserRepository;

@RestController
@RequestMapping(value = "/hateoas/users")

public class OrderhateoasController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	
	@GetMapping("/{userId}/orders")
	public List<Order> getAllOrders(@PathVariable Long userId) throws UserNotFoundExcpetion{
		
		Optional<User> userOptional  = userRepository.findById(userId);
		if(!userOptional.isPresent())
			throw new UserNotFoundExcpetion("User Not Found Whicle Checking Order");
			
		return userOptional.get().getOrders();
		}
		

}
