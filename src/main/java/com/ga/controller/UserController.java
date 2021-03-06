package com.ga.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ga.entity.JwtResponse;
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
	public ResponseEntity<?> signup(@RequestBody User user) {
		return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		return ResponseEntity.ok(new JwtResponse(userService.login(user)));
	}

	@PutMapping("/{userId}")
	public User update(@RequestBody User user, @PathVariable Long userId) {
		return userService.updateUser(user, userId);
	}

	@DeleteMapping("/{userId}")
	public User deleteUser(@PathVariable Long userId) {
		return userService.deleteUser(userId);
	}

	@PutMapping("/{username}/song/{songId}")
	public User addSong(@PathVariable String username, @PathVariable int songId) {
		return userService.addSong(username, songId);
	}
}
