package com.challenge.exceptions;

public class NotFoundException extends RuntimeException  {

    public NotFoundException(String errorMessage) {
        super(errorMessage);
    }

    public NotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
