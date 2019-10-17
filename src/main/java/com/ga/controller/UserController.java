package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	UserService userService;

	// TODO: Delete after sure Controller works
	@GetMapping("/hello")
	public String hello() {
		return "UserController Sanity Check";
	}

	@GetMapping("/list")
	public List<User> listUsers() {
		return userService.listUsers();
	}

	@PostMapping("/signup")
	public String signup(@RequestBody User user) {
		return userService.signup(user);
	}

	@PostMapping("/login")
	public String login(@RequestBody User user) {
		return userService.login(user);
	}

	@PutMapping("/{userId}")
	public User update(@RequestBody User user, @PathVariable Long userId) {
		return userService.updateUser(user, userId);
	}

	@DeleteMapping("/{userId")
	public User deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}
}
