package com.recommendationsystem.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	@Autowired
	private UserService userService;

	@GetMapping("/findByEmailId")
	public User getUserByEmailId(@RequestParam String email) {
		return userService.getUserByEmailId(email);
	}

	@PostMapping("/create")
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

}