package com.empmngt.exceptions;

import java.util.List;

public class NotFoundExceptions extends RuntimeException{

	public NotFoundExceptions(String message) {
		super(message);
	}
	
	public NotFoundExceptions(String message, Throwable cause) {
		super(message,cause);
	}
}
