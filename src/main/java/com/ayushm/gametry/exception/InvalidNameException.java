package com.ayushm.gametry.exception;

public class InvalidNameException extends Exception {
    private String message;

    public InvalidNameException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
