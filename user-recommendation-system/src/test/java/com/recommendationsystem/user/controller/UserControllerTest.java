package com.recommendationsystem.user.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.service.UserService;

class UserControllerTest {
	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	void testGetUserByEmailId() {
		String email = "test@example.com";
		User expectedUser = new User();
		expectedUser.setEmail(email);
		expectedUser.setName("Test User");
		when(userService.getUserByEmailId(email)).thenReturn(expectedUser);

		User actualUser = userController.getUserByEmailId(email);

		assertEquals(expectedUser, actualUser);
	}

	@Test
	void testCreateUser() {
		User newUser = new User();
		newUser.setEmail("newuser@example.com");
		newUser.setName("New User");
		when(userService.createUser(newUser)).thenReturn(newUser);
		User createdUser = userController.createUser(newUser);
		assertEquals(newUser, createdUser);
	}
}
