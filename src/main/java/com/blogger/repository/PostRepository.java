package com.blogger.repository;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.blogger.domain.Post;
import com.blogger.domain.User;

@Repository
public interface PostRepository extends CrudRepository<Post, Long>{
	
	public Set<Post> findByUser(User user);
}
