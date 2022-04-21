package com.springboot.app.users.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class ResourceNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;

	public ResourceNotAllowedException(String message) {
		super(message);
	}
}
