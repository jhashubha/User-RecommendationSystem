package com.recommendationsystem.user.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.recommendationsystem.user.enums.SkillGroups;
import com.recommendationsystem.user.enums.Teams;
import com.recommendationsystem.user.exception.UserNotFoundException;
import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.repository.UserRepository;

public class UserServiceTest {
	@InjectMocks
	private UserService userService;

	@Mock
	private UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUser_UserDoesNotExist() {
		User newUser = new User();
		newUser.setEmail("newuser@example.com");
		when(userRepository.findByEmail("newuser@example.com")).thenReturn(Optional.empty());
		when(userRepository.save(newUser)).thenReturn(newUser);
		User savedUser = userService.createUser(newUser);
		assertEquals(newUser, savedUser);
	}

	@Test
	public void testGetUserByEmailId_UserExists() {
		String userEmail = "test@example.com";
		User existingUser = new User();
		existingUser.setEmail(userEmail);
		existingUser.setName("Test User");
		when(userRepository.findByEmail(userEmail)).thenReturn(Optional.of(existingUser));
		User userResult = userService.getUserByEmailId(userEmail);
		assertNotNull(userResult);
		assertEquals(existingUser, userResult);
	}

	@Test
	public void testGetUserByEmailId_UserDoesNotExist() {
		String nonExistentUserEmail = "nonexistent@example.com";
		when(userRepository.findByEmail(nonExistentUserEmail)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userService.getUserByEmailId(nonExistentUserEmail));
	}

}
