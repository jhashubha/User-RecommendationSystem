package com.recommendationsystem.user.exception;

public class ReviewAlreadyExistException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ReviewAlreadyExistException() {
		super();
	}

	
	public ReviewAlreadyExistException(String arg0) {
		super(arg0);
	}

	
}