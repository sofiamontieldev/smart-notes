package com.montiel.smartnotes.exception;

public class BadRequestException extends RuntimeException {

    private final String message;

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}
