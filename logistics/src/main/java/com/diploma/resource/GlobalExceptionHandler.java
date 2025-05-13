package com.diploma.resource;

import com.diploma.exception.ErrorResponse;
import com.diploma.exception.InvalidProductDataException;
import com.diploma.exception.InvalidTransportDataException;
import com.diploma.exception.LocationNotFoundException;
import com.diploma.exception.ProductNotFoundException;
import com.diploma.exception.TransportNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static com.diploma.constants.ErrorConstants.LOCATION_NOT_FOUND;
import static com.diploma.constants.ErrorConstants.TRANSPORT_VALIDATION_ERROR;

@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidTransportDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransport(InvalidTransportDataException ex) {
        ErrorResponse error = new ErrorResponse(
                TRANSPORT_VALIDATION_ERROR,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidProductDataException.class)
    public ResponseEntity<ErrorResponse> handleInvalidTransport(InvalidProductDataException ex) {
        ErrorResponse error = new ErrorResponse(
                TRANSPORT_VALIDATION_ERROR,
                ex.getMessage(),
                HttpStatus.BAD_REQUEST
        );

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(LocationNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFound(LocationNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LOCATION_NOT_FOUND,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(TransportNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFound(TransportNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LOCATION_NOT_FOUND,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLocationNotFound(ProductNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                LOCATION_NOT_FOUND,
                ex.getMessage(),
                HttpStatus.NOT_FOUND
        );

        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }
}