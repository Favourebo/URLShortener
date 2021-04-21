package com.carbon.urlshortener.exceptions;

public class BaseException extends RuntimeException{
    protected static final String DEFAULT_MESSAGE = "An error has occurred.";

    public BaseException() {
        super(DEFAULT_MESSAGE);
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(Throwable cause) {
        super(cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
