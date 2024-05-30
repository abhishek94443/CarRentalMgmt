package com.exception;

public class WrongInformationException extends Exception{

    private String message;

    public WrongInformationException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    private static final long serialVersionUID = 106867174289775193L;


}