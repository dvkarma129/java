package com.categorymanagement.common;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomExceptionHandler {

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
		List<String> validationErrors = ex.getBindingResult().getFieldErrors().stream()
				.map(error -> error.getDefaultMessage()).collect(Collectors.toList());
		return new ResponseEntity<>(Map.of("error", "Validation failed", "message", validationErrors),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<Object> handleNotFoundException(NotFoundException ex) {
		CustomException customException = new CustomException(HttpStatus.NOT_FOUND,ex.getMessage());
		return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
	}

	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(DataAccessException.class)
	public Map<String, String> handleDataAccessException(DataAccessException e) {
		Map<String, String> errors = new HashMap<>();
		errors.put("message", "operation failed: " + e.getRootCause().getMessage());
		return errors;
	}
	
	@ExceptionHandler(DuplicateFoundException.class)
	@ResponseStatus(HttpStatus.CONFLICT)
	public ResponseEntity<Object> handleUniqueConstraintViolation(DuplicateFoundException ex) {
		CustomException customException = new CustomException(HttpStatus.NOT_FOUND,ex.getMessage());
		return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleNoSuchElementException(NoSuchElementException ex) {
        CustomException customException = new CustomException(HttpStatus.NOT_FOUND, ex.getMessage());
        return new ResponseEntity<>(customException, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(BadRequestException.class)
    public ResponseEntity<String> handleBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

	

}
