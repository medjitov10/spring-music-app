package com.ga.service;

import java.util.List;

import com.ga.entity.User;

public interface UserService {

	public List<User> listUsers();
	public User signup(User user);
	public Long login(User user);
// TODO: replace with token
//	public User updateUser(User user, Long userId);
//	public User deleteUser(Long userId);
//	public User getUserByUsername(String username);

}
