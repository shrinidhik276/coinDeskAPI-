package com.mystore.customException;

public class InvalidCredentialsException extends Exception{
    // Default constructor
    public InvalidCredentialsException(){
        super("Invalid credentials");
    }
    // Constructor with custom message
    // Constructor with custom message
    public InvalidCredentialsException(String message) {
        super(message);
    }
    // Constructor with custom message and cause
    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

}
