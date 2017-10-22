package com.blogger.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.blogger.domain.User;
import com.blogger.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public void addUser(User user){
		user.setPassword(this.bCryptPasswordEncoder.encode(user.getPassword()));
		this.userRepository.save(user);
	}
	
	public User getUser(String username){
		return this.userRepository.findByUsername(username);
	}
	
	public void updateUser(User user){
		this.userRepository.save(user);
	}
	
	public void deleteUser(Long id){
		this.userRepository.delete(id);
	}
}
