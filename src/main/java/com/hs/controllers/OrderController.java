package com.hs.controllers;

import java.util.List;
import java.util.Optional;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hs.entities.Order;
import com.hs.entities.User;
import com.hs.exception.OrderNotFoundExcpetion;
import com.hs.exception.UserNotFoundExcpetion;
import com.hs.reporsitory.OrderRepository;
import com.hs.reporsitory.UserRepository;

@RestController
@RequestMapping("/users")
public class OrderController {

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
		
	
	@PostMapping("/{userId}/orders")
	public Order createOrder(@PathVariable Long userId, @RequestBody Order order) throws UserNotFoundExcpetion {
		Optional<User> userOptional  = userRepository.findById(userId);
		if(!userOptional.isPresent())
			throw new UserNotFoundExcpetion("User Not Found Whicle Checking Order");
		
		User user = userOptional.get();
		
		order.setUser(user);
		Order order_saved = orderRepository.save(order);
		return order_saved;
		
	}
	
	
	@GetMapping("/{userId}/orders/{orderId}")
	public Order getOrderByOrderId(@PathVariable Long userId, @PathVariable Long orderId) throws UserNotFoundExcpetion, OrderNotFoundExcpetion {
		
		Optional<User> userOptional  = userRepository.findById(userId);
		if(!userOptional.isPresent())
			throw new UserNotFoundExcpetion("User Not Found Whicle Checking Order");
		
		List<Order> orders = userOptional.get().getOrders();
		
		Optional<Order> order =  orderRepository.findById(orderId);
		
		if(orders.isEmpty() || !order.isPresent())
			throw new OrderNotFoundExcpetion("Your Order Not Found Whicle Checking Order");
		
		if(order.get().getUser().getId() != userId) {
			throw new UserNotFoundExcpetion("Please use correct order Info");
		}
		
	//	Order order = orders.stream().filter( e -> e.getOrderId() == orderId).collect(Collectors.toList()).get(0);
		
		return order.get();
		}
		
}
