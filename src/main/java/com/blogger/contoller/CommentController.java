package com.blogger.contoller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogger.domain.Comment;
import com.blogger.service.CommentService;

@RestController
@RequestMapping("/posts/{postId}/comments")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
	@GetMapping
	public List<Comment> getCommentsForPost(@PathVariable Long postId){
		return this.commentService.viewCommentsByPost(postId);
	}
	
	@PostMapping
	public void addComment(Principal principal,@PathVariable Long postId,@RequestBody Comment comment){
		this.commentService.addComment(principal.getName(), postId, comment);
	}
	
	@PutMapping("/{commentId}")
	public void updateComment(Principal principal,@PathVariable Long postId,@PathVariable Long commentId,@RequestBody Comment comment){
		this.commentService.updateComment(principal.getName(), postId, commentId, comment);
	}
	
	@DeleteMapping("/{commentId}")
	public void deleteComment(Principal principal,@PathVariable Long postId,@PathVariable Long commentId){
		this.commentService.deleteComment(principal.getName(), postId, commentId);
	}

}
