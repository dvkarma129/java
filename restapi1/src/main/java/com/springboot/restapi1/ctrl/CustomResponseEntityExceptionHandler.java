package com.springboot.restapi1.ctrl;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<UserNotFound> handleAllException(Exception ex, WebRequest request){
		UserNotFound userNotFound = new UserNotFound(LocalDate.now(), ex.getMessage(), request.getDescription(false));
				return new ResponseEntity<UserNotFound>(userNotFound, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFound.class)
	public final ResponseEntity<UserNotFound> handleUserNotFoundException(Exception ex, WebRequest request){
		UserNotFound userNotFound = new UserNotFound(LocalDate.now(), ex.getMessage(), request.getDescription(false));
				return new ResponseEntity<UserNotFound>(userNotFound, HttpStatus.NOT_FOUND);
	}
}
