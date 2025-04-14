package com.example.demo.model.exception;

public class AlreadyRentedException extends RuntimeException {
    public AlreadyRentedException(String message) {
        super(message);
    }
}
