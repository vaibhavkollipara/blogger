package com.blogger.service;

import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogger.domain.Comment;
import com.blogger.domain.Post;
import com.blogger.domain.User;
import com.blogger.repository.CommentRepository;
import com.blogger.repository.PostRepository;
import com.blogger.repository.UserRepository;

@Service
public class CommentService {
	
	@Autowired
	private CommentRepository commentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void addComment(String username,Long postId,Comment comment){
		User user;
		Post post;
		try{
			user = this.userRepository.findByUsername(username);
			post = this.postRepository.findOne(postId);
		}catch(Exception e){
			throw new RuntimeException("Invalid details provided");
		}
		comment.setUser(user);
		comment.setPost(post);
		comment.setCommented_on(new Date());
		post.getCommentsList().add(comment);
		this.commentRepository.save(comment);
	}
	
	public List<Comment> viewCommentsByPost(Long postId){
		return this.commentRepository.findByPostId(postId);
	}
	
	public void updateComment(String username,Long postId,Long commentId,Comment comment){
		Comment comment_old;
		try{
			comment_old = this.commentRepository.findOne(commentId);
		}catch(Exception e){
			throw new RuntimeException("Invalid Comment Id");
		}
		if(!comment_old.getUser().getUsername().equals(username)){
			throw new RuntimeException("Its not your comment to edit !!");
		}
		comment_old.setComment(comment.getComment());
		comment_old.setCommented_on(new Date());
		this.commentRepository.save(comment_old);
	}
	
	public void deleteComment(String username,Long postId,Long commentId){
		Comment comment = this.commentRepository.findOne(commentId);
		if(!comment.getUser().getUsername().equals(username)){
			throw new RuntimeException("It is not your comment to delete !!");
		}
		this.commentRepository.delete(commentId);
	}
}
