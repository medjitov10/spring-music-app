package com.ga.controller.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;
import com.ga.entity.UserRole;
import com.ga.service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class UserServiceTest {
	@Mock
	private UserDao userDao;

	@Mock
	private JwtUtil jwtUtil;

	@Mock
	private PasswordEncoder bCryptPasswordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	@InjectMocks
	private UserRole userRole;
	
	@InjectMocks
	private User user;

	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void signup_ReturnsJwt_Success() {
		String expectedToken = "12345";

		when(userDao.signup(any())).thenReturn(user);
		when(userDao.getUserByUsername(anyString())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");

		String actualToken = userService.signup(user);

		assertEquals(actualToken, expectedToken);

	}
	
	@Test
	public void login_ReturnsJwtSUccess() {
		String expectedToken = "12345";

		when(userDao.login(any())).thenReturn(user);
		when(userDao.getUserByUsername(anyString())).thenReturn(user);
		when(bCryptPasswordEncoder.matches(any(), any())).thenReturn(true);
		when(jwtUtil.generateToken(any())).thenReturn(expectedToken);
		when(bCryptPasswordEncoder.encode(user.getPassword())).thenReturn("robin");
		
		
		String actualToken = userService.login(user);

        assertEquals(actualToken, expectedToken);
	}
	
	@Test 
	public void updateUser_User_Success() {
		when(userDao.updateUser(any(), anyLong())).thenReturn(user);
		
		User user1 = userService.updateUser(user, 1L);
		
		assertEquals(user1, user);
	}
	
	
	@Test 
	public void deleteUser_User_Success() {
		when(userDao.deleteUser(any())).thenReturn(user);
		
		User user1 = userService.deleteUser(1L);
		
		assertEquals(user1, user);
	}
	
	@Test 
	public void addSongUser_User_Success() {
		when(userDao.addSong("batman", 1)).thenReturn(user);
		
		User user1 = userService.addSong("batman", 1);
		
		assertEquals(user1, user);
	}
	
	@Before
	public void initializeDummyUser() {
		userRole.setName("ROLE_ADMIN");
        user.setUserId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        user.setUserRole(userRole);
	}

}
