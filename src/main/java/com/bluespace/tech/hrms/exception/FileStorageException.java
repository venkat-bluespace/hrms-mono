package com.bluespace.tech.hrms.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileStorageException extends RuntimeException {

	private static final long serialVersionUID = -3764648909377564920L;
	
	public FileStorageException() {
	}

	public FileStorageException(String message) {
		super(message);
	}

	public FileStorageException(Throwable cause) {
		super(cause);
	}
	
	public FileStorageException(String message, Throwable cause) {
		super(message, cause);
	}

	@ExceptionHandler(MultipartException.class)
	public String handleError(MultipartException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		return "redirect:/documents";
	}

}