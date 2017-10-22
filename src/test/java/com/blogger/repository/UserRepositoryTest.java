package com.blogger.repository;

import static org.assertj.core.api.Assertions.assertThat;
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

import com.blogger.domain.User;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {
	
	@Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;
    
    @Before
    public void setUp(){
    
    }

    @Test
    public void testAddUser() throws Exception {
    	User u1 = new User();
    	u1.setUsername("user1");
    	u1.setEmail("user1@blogger.com");
    	u1.setPassword("password");
    	//adds to db
    	this.entityManager.persist(u1);
    	
        User user = this.repository.findByUsername("user1");
        assertThat(user.getUsername()).isEqualTo("user1");
        assertThat(user.getEmail()).isEqualTo("user1@blogger.com");
    }
    
    @Test
    public void testUserCount() throws Exception {
    	User u1 = new User();
    	u1.setUsername("user1");
    	u1.setEmail("user1@blogger.com");
    	u1.setPassword("password");
    	//adds to db
    	this.entityManager.persist(u1);
    	
        List<User> users = (List<User>) this.repository.findAll();
        assertEquals("Failure : Users count match failed", 1, users.size());
    }
}
