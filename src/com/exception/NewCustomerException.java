package com.exception;

import java.io.Serial;

public class NewCustomerException extends Exception {
    private String message;

    public String getMessage() {
        return message;
    }

    public NewCustomerException(String message) {
        this.message = message;
    }

    @Serial
    private static final long serialVersionUID = 2563272640903576703L;
}