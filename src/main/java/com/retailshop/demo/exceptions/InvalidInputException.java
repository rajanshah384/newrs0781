package com.retailshop.demo.exceptions;

public class InvalidInputException extends RuntimeException {
    private final String message;

    public InvalidInputException(String message) {
        this.message = message;
    }
}
