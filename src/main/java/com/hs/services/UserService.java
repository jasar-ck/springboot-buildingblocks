package com.hs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hs.entities.User;
import com.hs.reporsitory.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//get All Users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) {
		return userRepository.save(user);
	}
	
	//get UserBy ID
	public Optional<User> getUserbyId(Long id) {
		Optional<User> user = userRepository.findById(id);
		return user;
	}
	
	//update User By Id
	
	public User updateUserById(Long id, User user) {
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete User By Id
	
	public void deleteById(Long id) {
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
		}
		
	}

	//get UserBy ID
	public User getUserbyUserName(String username) {
		User user = userRepository.findByUsername(username);
		return user;
	}
}
