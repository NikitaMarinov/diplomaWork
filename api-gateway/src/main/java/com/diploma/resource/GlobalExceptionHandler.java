package com.diploma.resource;

import com.diploma.exception.ErrorResponse;
import com.diploma.exception.LocationServiceException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(LocationServiceException.class)
    public ResponseEntity<ErrorResponse> handleLocationServiceException(LocationServiceException ex) {
        return ResponseEntity
                .status(ex.getHttpStatus())
                .body(new ErrorResponse(ex.getErrorCode(), ex.getMessage(), ex.getHttpStatus()));
    }
}
