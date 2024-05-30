package com.exception;

public class NewCustomerException extends Exception {
    private String message;

    public String getMessage() {
        return message;
    }

    public NewCustomerException(String message) {
        this.message = message;
    }

    private static final long serialVersionUID = 2563272640903576703L;
}