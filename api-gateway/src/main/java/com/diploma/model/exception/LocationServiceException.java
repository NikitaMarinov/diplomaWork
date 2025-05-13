package com.diploma.model.exception;

import org.springframework.http.HttpStatus;

public class LocationServiceException extends RuntimeException {
    private final String errorCode;
    private final HttpStatus httpStatus;

    public LocationServiceException(String errorCode, String message, HttpStatus httpStatus) {
        super(message);
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}

