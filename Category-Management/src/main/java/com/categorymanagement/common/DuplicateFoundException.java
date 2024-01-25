package com.categorymanagement.common;

public class DuplicateFoundException extends RuntimeException {

    public DuplicateFoundException(String message) {
        super(message);
    }
}
