package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
// TODO: initialize db with existing users for development speed/testing	
	@Autowired
	UserService userService;

	@GetMapping("/hello")
	public String hello() {
		return "Hello World!";
	}
	
	@GetMapping("/list")
	public List<User> listUsers() {
		return userService.listUsers();
	}
	
	@PostMapping("/signup")
	public User signup(@RequestBody User user) {
		return userService.signup(user);
	}

	@PostMapping("/login")
	public Long login(@RequestBody User user) {
		return userService.login(user);
	}

	@PutMapping("/{username}")
	public Long update(@RequestBody User user, @PathVariable String username) {
		return userService.updateUser(user, username);
//	public String update(@RequestBody User user, @PathVariable String username) {
//		return username;
	}
}
