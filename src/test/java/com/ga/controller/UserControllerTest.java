package com.ga.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ga.entity.User;
import com.ga.service.UserService;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	private MockMvc mockMvc;
	
	@InjectMocks
	private UserController userController;
	
	@Mock UserService userService;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
	}
	@InjectMocks
	User user;
	
	
	@Before
	public void initializer() throws JsonProcessingException {
		user.setUsername("Osman");
		user.setUserId(1L);
	}
	
	private static String createUserInJson(String username, String password) {
		return "{ \"username\": \"" + username + "\", " + "\"password\":\"" + password + "\"}";
	}
	
	
	@Test 
	public void helloWorld_HelloWorld_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/hello").accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string("UserController Sanity Check"));
	}
	
	@Test
	public void listUsers_User_Success() throws Exception {
		List<User> listOfUsers = new ArrayList<>();
		listOfUsers.add(user);
		ObjectMapper mapper = new ObjectMapper();
		String listUserMapper = mapper.writeValueAsString(listOfUsers);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.get("/user/list").accept(MediaType.APPLICATION_JSON);
		
		when(userService.listUsers()).thenReturn(listOfUsers);
		
		mockMvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(content().string(listUserMapper))
			.andReturn();
	}

	
	@Test
	public void signup_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/signup")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson("joe", "abc"));
		
		when(userService.signup(any())).thenReturn("123456");

		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"token\":\"123456\"}"))
	              .andReturn();		
	}
	@Test
	public void login_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post("/user/login")
				.contentType(MediaType.APPLICATION_JSON)
				.content(createUserInJson("joe", "abc"));
		
		when(userService.login(any())).thenReturn("123456");

		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json("{\"token\":\"123456\"}"))
	              .andReturn();
		
		System.out.println(result.getResponse().getContentAsString());
	}
	
	@Test
	public void deleteUser_User_Success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String userMapper = mapper.writeValueAsString(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.delete("user/{id}")
				.contentType(MediaType.APPLICATION_JSON);
		
		when(userService.deleteUser(1L)).thenReturn(user);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json(userMapper))
	              .andReturn();
	}
	
	@Test
	public void updateUser_User_Success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String userMapper = mapper.writeValueAsString(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("user/{id}")
				.contentType(MediaType.APPLICATION_JSON);
		
		when(userService.updateUser(user, 1L)).thenReturn(user);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json(userMapper))
	              .andReturn();
	}
	
	@Test
	public void addSong_User_Success() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String userMapper = mapper.writeValueAsString(user);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.put("/{username}/song/{songId}")
				.contentType(MediaType.APPLICATION_JSON);
		
		when(userService.addSong("Osman", 1)).thenReturn(user);
		
		MvcResult result = mockMvc.perform(requestBuilder)
	              .andExpect(status().isOk())
	              .andExpect(content().json(userMapper))
	              .andReturn();
	}
}
