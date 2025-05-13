package com.diploma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidTransportDataException extends RuntimeException {
    public InvalidTransportDataException(String message) {
        super(message);
    }
}