package com.blogger.contoller;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.Post;
import com.blogger.service.PostService;

@RestController
@RequestMapping("/posts")
public class PostController {
	
	@Autowired
	private PostService postService;
	
	@GetMapping
	public Set<Post> getAllPosts(){
		return this.postService.getAllPosts();
	}
	
	@GetMapping("/{username}")
	public Set<Post> getPostsByUsername(@PathVariable String username){
		return this.postService.getPostByUser(username);
	}
	
	@PostMapping
	public void addPost(Principal principal,@RequestBody Post post){
		this.postService.createPost(principal.getName() ,post);
	}
	
	@PutMapping
	public void updatePost(Principal principal,Long id,@RequestBody Post post){
		post.setId(id);
		this.postService.updatePost(principal.getName(), post);
	}
}
