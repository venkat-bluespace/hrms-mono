package com.bluespace.tech.hrms.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -1074156154275120857L;

	public UserNotFoundException(Throwable cause) {
		super(cause);
	}

	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public UserNotFoundException(String message) {
		super(message);
	}

	public UserNotFoundException() {
	}

}