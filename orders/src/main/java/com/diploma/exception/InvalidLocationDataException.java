package com.diploma.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidLocationDataException extends RuntimeException {
    public InvalidLocationDataException(String message) {
        super(message);
    }
}