package com.ga.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.ga.entity.User;

public interface UserService extends UserDetailsService{

	public List<User> listUsers();
	public User signup(User user);
	public Long login(User user);
	public Long updateUser(User user, String username);
	public User deleteUser(Long userId);
	public User addSong(String username, int songId);

}
