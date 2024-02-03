package com.example.bookmanagment.Exceptions;

import lombok.Getter;

@Getter
public class BadRequestAlertException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private final Integer errorCode;

    public BadRequestAlertException(Integer errorCode, String message) {
        super( message );
        this.errorCode = errorCode;
    }
}