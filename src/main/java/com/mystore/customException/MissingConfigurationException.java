package com.mystore.customException;

public class MissingConfigurationException extends Exception {

    public MissingConfigurationException(String message) {
        super(message);
    }
    public MissingConfigurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
