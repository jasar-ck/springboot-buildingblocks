package com.hs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.entities.Order;
import com.hs.reporsitory.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getAllOrder(){
		return orderRepository.findAll();
	}

	
	public Optional<Order> getOrderById(Long id) {
		return orderRepository.findById(id);
	}
}
