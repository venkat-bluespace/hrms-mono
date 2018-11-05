package com.bluespace.tech.hrms.exception;

public class FileNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 7948207638492055744L;

	public FileNotFoundException(String message) {
		super(message);
	}

	public FileNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

}