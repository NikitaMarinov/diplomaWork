package com.diploma.exception;

import org.springframework.http.HttpStatus;

public class ErrorResponse {
    private String errorCode;
    private HttpStatus httpStatus;
    private String message;


    public ErrorResponse(String errorCode, String message, HttpStatus httpStatus) {
        this.errorCode = errorCode;
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public ErrorResponse setErrorCode(String errorCode) {
        this.errorCode = errorCode;
        return this;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public ErrorResponse setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ErrorResponse setMessage(String message) {
        this.message = message;
        return this;
    }
}
