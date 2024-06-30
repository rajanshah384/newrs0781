package com.retailshop.demo.exceptions;

public class GenericExceptionHandler {
    
    protected Exception handleConflict(RuntimeException ex) {
        return new Exception("Invalid Input : "+ex.getCause());
    }
}
