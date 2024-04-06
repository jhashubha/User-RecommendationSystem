package com.recommendationsystem.user.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserNotFoundException() {
		super();
	}

	public UserNotFoundException(String arg0) {
		super(arg0);
	}

}