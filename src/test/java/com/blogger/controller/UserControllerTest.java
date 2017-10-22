package com.blogger.controller;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import com.blogger.BloggerApp;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes=BloggerApp.class
	)
@AutoConfigureMockMvc
public class UserControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Autowired
	private ObjectMapper objectMapper;

	private String token;
	
	@Before
	public void setUp(){
		signUp();
		authenticate();
	}

	
	private void signUp(){
		Map<String,String> payload = new HashMap<>();
		payload.put("username","user1");
		payload.put("password","password");
		payload.put("email", "user1@blogger.com");
		String userJson = "";
		try {
			userJson = this.objectMapper.writeValueAsString(payload);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			this.mockMvc.perform(
					post("/users")
					.contentType(MediaType.APPLICATION_JSON)
					.content(userJson)
				).andExpect(status().isOk());
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	public void authenticate(){
		Map<String,String> payload = new HashMap<>();
		payload.put("username","user1");
		payload.put("password","password");
		String usercreds = "";
		try {
			usercreds = this.objectMapper.writeValueAsString(payload);
		} catch (JsonProcessingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MvcResult result = this.mockMvc.perform(
					post("/authenticate")
					.contentType(MediaType.APPLICATION_JSON)
					.content(usercreds)
				).andReturn();
			assertEquals(200, result.getResponse().getStatus());
			this.token = result.getResponse().getHeader("authorization").substring(7);
			
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
	@Test
	public void testViewUserWithoutAuthorization(){
		try {
			MvcResult result = this.mockMvc.perform(
					get("/users/user1")
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
				).andReturn();
			assertEquals(403, result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testViewUserWithAuthorization(){
		try {
			MvcResult result = this.mockMvc.perform(
					get("/users/user1")
					.header("Authorization", this.token)
					.contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
				).andReturn();
			assertEquals(200, result.getResponse().getStatus());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
