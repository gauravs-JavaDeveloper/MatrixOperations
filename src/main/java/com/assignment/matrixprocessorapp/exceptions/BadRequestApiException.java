package com.assignment.matrixprocessorapp.exceptions;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * Bad request for API exception class
 * 
 * @author gauravs
 *
 */

public class BadRequestApiException extends Exception {

	private HttpStatus status;

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	private String message;
	private List<String> errors;
	private static final long serialVersionUID = 1L;

	public BadRequestApiException(HttpStatus status, String message, List<String> errors) {
		super();
		this.status = status;
		this.message = message;
		this.errors = errors;
	}

	public BadRequestApiException(HttpStatus status, String message, String error) {
		super();
		this.status = status;
		this.message = message;
		errors = Arrays.asList(error);
	}
}
