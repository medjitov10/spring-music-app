package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public List<User> listUsers() {
		return userDao.listUsers();
	}

	@Override
	// TODO: Decide what DataType/Value to return for found user
	public User signup(User user) {
		return userDao.signup(user);
	}

	@Override
	public Long login(User user) {
		return userDao.login(user).getUserId();
	}

	@Override
	public Long updateUser(User user, String username) {
		return userDao.updateUser(user, username).getUserId();
	}

	@Override
	public User deleteUser(Long userId) {
		return userDao.deleteUser(userId);
	}

	@Override
	public User addSong(String username, int songId) {
		return null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			User user = userDao.getUserByUsername(username);

			if(user == null) {
				throw new UsernameNotFoundException("Unknown user: " + username);
			}
			// TODO: finish return after adding bCrypt
		return null;
	}
}
