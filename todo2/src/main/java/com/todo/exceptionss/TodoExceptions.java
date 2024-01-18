package com.todo.exceptionss;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class TodoExceptions {
	private final String message;
	private final Throwable throwable;
	private final HttpStatus httpStatus;
	public TodoExceptions(String message, Throwable throwable, HttpStatus httpStatus) {
		super();
		this.message = message;
		this.throwable = throwable;
		this.httpStatus = httpStatus;
	}
}
