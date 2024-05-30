package com.exception;

public class InvalidRatingsException extends Exception {
    private String message;

    public String getMessage() {
        return message;
    }

    public InvalidRatingsException(String message) {
        this.message = message;
    }

    private static final long serialVersionUID = 2563272640903576703L;
}
