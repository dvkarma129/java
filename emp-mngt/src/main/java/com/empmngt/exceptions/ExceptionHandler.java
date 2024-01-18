package com.empmngt.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
class ExceptionHandler extends RuntimeException {

	@org.springframework.web.bind.annotation.ExceptionHandler(value = { NotFoundExceptions.class })
	public ResponseEntity<Object> notFOundExceptions(NotFoundExceptions notFoundExceptions) {
		EmpManagementExceptions empManagementExceptions = new EmpManagementExceptions(notFoundExceptions.getMessage(),
				notFoundExceptions.getCause(), HttpStatus.NOT_FOUND);
		return new ResponseEntity<>(empManagementExceptions, HttpStatus.NOT_FOUND);
	}

	@org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getDefaultMessage()).collect(Collectors.toList());

		return new ResponseEntity<>(Map.of("error", "Validation failed", "message", validationErrors),
				HttpStatus.BAD_REQUEST);
	}

}
