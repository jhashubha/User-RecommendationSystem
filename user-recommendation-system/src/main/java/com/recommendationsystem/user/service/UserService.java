package com.recommendationsystem.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.recommendationsystem.user.exception.UserAlreadyExistException;
import com.recommendationsystem.user.exception.UserNotFoundException;
import com.recommendationsystem.user.model.User;
import com.recommendationsystem.user.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public User createUser(User user) {
		Optional<User> userData = userRepository.findByEmail(user.getEmail());
		if (userData.isPresent()) {
			throw new UserAlreadyExistException("User Already Exist");
		}
		return userRepository.save(user);
	}

	public User getUserByEmailId(String email) {
		Optional<User> optionalUser = userRepository.findByEmail(email);
		if (optionalUser.isPresent()) {
			return optionalUser.get();
		}
		throw new UserNotFoundException("User not found");

	}

}
