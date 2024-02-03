package com.example.bookmanagment.Exceptions;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(BadRequestAlertException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorVM> handleBadRequestException(BadRequestAlertException exp) {
        return new ResponseEntity<>(
                ErrorVM.builder().errorKey(exp.getErrorCode()).errorMessage(exp.getMessage()).build(),
                HttpStatus.BAD_REQUEST);
    }

}