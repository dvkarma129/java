package com.empmngt.exceptions;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class EmpManagementExceptions {

	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	public EmpManagementExceptions(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}	
}
