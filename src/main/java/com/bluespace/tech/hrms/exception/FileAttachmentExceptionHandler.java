package com.bluespace.tech.hrms.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class FileAttachmentExceptionHandler {

	@ExceptionHandler(MultipartException.class)
	public String handleError(MultipartException e, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("message", e.getCause().getMessage());
		return "redirect:/documents";
	}

}