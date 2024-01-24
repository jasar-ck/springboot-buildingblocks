package com.hs.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.hs.entities.User;
import com.hs.exception.UserExistsException;
import com.hs.exception.UserNotFoundExcpetion;
import com.hs.reporsitory.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	//get All Users
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User createUser(User user) throws UserExistsException {
		User user_ = userRepository.findByUsername(user.getUsername());
		if(user_ != null) {
			throw new UserExistsException("User name Already exists");
		}
		return userRepository.save(user);
	}
	
	//get UserBy ID
	public Optional<User> getUserbyId(Long id) throws UserNotFoundExcpetion {
		Optional<User> user = userRepository.findById(id);
		
		if(!user.isPresent()) {
			throw new UserNotFoundExcpetion("User Not Found in the System");
		}
		return user;
	}
	
	//update User By Id
	
	public User updateUserById(Long id, User user) throws UserNotFoundExcpetion {
		Optional<User> user_ = userRepository.findById(id);
		if(!user_.isPresent()) {
			throw new UserNotFoundExcpetion("User Not Found in the System, Provide correct suer Id");
		}
		user.setId(id);
		return userRepository.save(user);
	}
	
	//Delete User By Id
	
	public void deleteById(Long id) throws UserNotFoundExcpetion {
		Optional<User> user_ = userRepository.findById(id);
		if(!user_.isPresent()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found in the System, Provide correct suer Id");
		}
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
