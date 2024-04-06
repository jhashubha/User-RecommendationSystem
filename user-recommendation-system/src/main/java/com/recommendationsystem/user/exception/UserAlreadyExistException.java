package com.recommendationsystem.user.exception;

public class UserAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException() {
		super();
	}

	public UserAlreadyExistException(String arg0) {
		super(arg0);
	}

}