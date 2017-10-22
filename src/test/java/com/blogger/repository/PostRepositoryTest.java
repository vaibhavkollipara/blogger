package com.blogger.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.blogger.domain.Post;
import com.blogger.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class PostRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PostRepository repository;
    
    private User u1;
    
    @Before
    public void setUp(){
    	u1 = new User();
    	u1.setUsername("user1");
    	u1.setEmail("user1@blogger.com");
    	u1.setPassword("password");
    	//adds to db
    	this.entityManager.persist(u1);
    }
    
    @Test
    public void testAddPost(){
    	Post p = new Post();
    	p.setUser(this.u1);
    	p.setText("Post from user 1");
    	this.u1.getPostsList().add(p);
    	this.entityManager.persist(p);
    	
    	assertEquals("Failed : posts count did not match",1,this.repository.count());
    }
    
    @Test
    public void testPostsByUserName(){
    	Post p = new Post();
    	p.setUser(this.u1);
    	p.setText("Post from user 1");
    	this.u1.getPostsList().add(p);
    	this.entityManager.persist(p);
    	assertEquals("Failed : users post count did not match", 1, this.repository.findByUser(this.u1).size());
    	
    }
    
    
}
