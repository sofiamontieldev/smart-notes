package com.montiel.smartnotes.exception;

public class MyValidationException extends RuntimeException {
    public MyValidationException(String message) { super(message); }
    public MyValidationException(String message, Throwable cause) { super(message, cause); }
}
