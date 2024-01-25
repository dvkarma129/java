package com.categorymanagement.common;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class CustomException {

	private final HttpStatus httpStatus;
	private final String message;
	public CustomException( HttpStatus httpStatus, String message) {
		super();
		this.httpStatus = httpStatus;
		this.message = message;
	}	
}
