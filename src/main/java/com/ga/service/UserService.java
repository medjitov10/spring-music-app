package com.ga.service;

import java.util.List;

import com.ga.entity.User;

public interface UserService {

	public List<User> listUsers();
	public User signup(User user);
// TODO: If user already exists: return placeholder existingUser
	public Long login(User user);
// TODO: replace with token
	public Long updateUser(User user, String username);
//	public User deleteUser(Long userId);
//	public User getUserByUsername(String username);

}
