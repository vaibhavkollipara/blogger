package com.blogger.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blogger.domain.User;
import com.blogger.repository.UserRepository;

@Service
public class UserDetailsServiceImp implements UserDetailsService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = null;
		try{
		user = this.userRepository.findByUsername(username);

		}catch(Exception e){
			throw new UsernameNotFoundException("Invalid Credentials");
		}
		
		return new org.springframework.security.core.userdetails.
				User(user.getUsername(), user.getPassword(),Collections.emptyList());
		
	}

}
