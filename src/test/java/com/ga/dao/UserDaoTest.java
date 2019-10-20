package com.ga.dao;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.ga.entity.Song;
import com.ga.entity.User;
import com.ga.entity.UserRole;

public class UserDaoTest {
	@Rule
	public MockitoRule rule = MockitoJUnit.rule();

	@InjectMocks
	private User user;

	@InjectMocks
	private UserRole userRole;

	@InjectMocks
	private UserDaoImpl userDao;
	
	@InjectMocks
	private Song song;
	
	@Mock
	private SessionFactory sessionFactory;

	@Mock
	Session session;

	@Mock
	Transaction transaction;
	
	@Mock
    Query<User> query;
	
	@Mock
	private UserRoleDao userRoleDao;

	@Test
	public void signUp_UserDao_Success() {
		when(userRoleDao.getRole(anyString())).thenReturn(userRole);
		User savedUser = userDao.signup(user);
		System.out.println(savedUser.getUsername());
		assertNotNull("Test returned null object, expected non-null", savedUser);
		
		assertEquals(user, savedUser);
		
	}
	
	@Test
	public void login_UserDao_Success() {
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.getSingleResult()).thenReturn(user);
		
		User savedUser = userDao.login(user);

		assertNotNull("Test returned null object, expected non-null", savedUser);
		assertEquals(savedUser, user);
		
	}
	
	@Test
	public void update_UserDao_Success() {
		when(session.get(User.class, 1L)).thenReturn(user);
		User savedUser = userDao.updateUser(user, user.getUserId());
		assertNotNull("Test returned null object, expected non-null", savedUser);
		assertEquals(savedUser, user);
	}
	
	@Test
	public void delete_UserDao_Success() {
		when(session.get(User.class, 1L)).thenReturn(user);
		User savedUser = userDao.deleteUser(user.getUserId());
		assertNotNull("Test returned null object, expected non-null", savedUser);
		assertEquals(savedUser, user);
	}
	
	@Test
	public void getUserByUsername_UserDao_Success() {
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.uniqueResult()).thenReturn(user);
		User savedUser = userDao.getUserByUsername(user.getUsername());
		assertNotNull("Test returned null object, expected non-null", savedUser);
		assertEquals(savedUser, user);
	}
	
	@Test
	public void addSong_UserDao_Success() {
		when(session.createQuery(anyString())).thenReturn(query);
		when(query.uniqueResult()).thenReturn(user);
		when(session.get(Song.class, 1)).thenReturn(song);
		
		User savedUser = userDao.addSong(user.getUsername(), 1);
		assertNotNull("Test returned null object, expected non-null", savedUser);
		assertEquals(savedUser, user);
	}
	
	@Before
	public void init() {
		when(sessionFactory.getCurrentSession()).thenReturn(session);
		when(session.getTransaction()).thenReturn(transaction);
	}
	
	@Before
	public void initializeDummyUser() {
		userRole.setRoleId(1);
		userRole.setName("ROLE_ADMIN");
		
		
		song.setArtist("Boy");
		song.setId(1);
		user.setUserId(1L);
		user.setUsername("batman");
		user.setPassword("robin");
		user.setUserRole(userRole);
	}
}
