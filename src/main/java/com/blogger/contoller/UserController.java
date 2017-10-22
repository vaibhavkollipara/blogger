package com.blogger.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.User;
import com.blogger.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userSerivce;
	
	@GetMapping("/{username}")
	public User getUser(@PathVariable String username){
		return this.userSerivce.getUser(username);
	}
	
	@PostMapping
	public void addUser(@RequestBody User user){
		this.userSerivce.addUser(user);
	}
	
	@PutMapping
	public void updateUser(@RequestBody User user,Long id){
		this.userSerivce.updateUser(user);
	}
	
	@DeleteMapping
	public void deleteUser(Long id){
		this.userSerivce.deleteUser(id);
	}
	
}
