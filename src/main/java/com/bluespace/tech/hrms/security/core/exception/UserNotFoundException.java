package com.bluespace.tech.hrms.security.core.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3002945392728627938L;
	
	public UserNotFoundException(Throwable cause) {
		super(cause);
	}
	
	public UserNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public UserNotFoundException(String message) {
		super(message);
	}
	
	public UserNotFoundException() {}

}