package com.blogger.service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.domain.Post;
import com.blogger.domain.User;
import com.blogger.repository.PostRepository;
import com.blogger.repository.UserRepository;

@Service
public class PostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public Set<Post> getAllPosts(){
		Set<Post> set = new HashSet<Post>();
		for(Post post: this.postRepository.findAll()){
			set.add(post);
		}
		return set;
	}
	
	public Set<Post> getPostByUser(String username){
		User user = this.userRepository.findByUsername(username);
		return user.getPostsList();
	}
	
	public void createPost(String username,Post post){
		User user = this.userRepository.findByUsername(username);
		post.setPost_date(new Date());
		user.getPostsList().add(post);
		post.setUser(user);
		this.postRepository.save(post);
	}
	
	public void updatePost(String username,Post post){
		this.postRepository.save(post);
	}
	
	public void deletePost(Long id){
		this.postRepository.delete(id);
	}
}
