package com.todo.exceptionss;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NotFoundExceptions.class})
    public ResponseEntity<Object> userNotFoundException(NotFoundExceptions notFoundExceptions) {
        TodoExceptions todoExceptions = new TodoExceptions(notFoundExceptions.getMessage(), 
        		notFoundExceptions.getCause(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(todoExceptions, HttpStatus.NOT_FOUND);
    }
}
