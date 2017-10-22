package com.blogger.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogger.domain.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long>{
	
	public List<Comment> findByPostId(Long postId);

}
