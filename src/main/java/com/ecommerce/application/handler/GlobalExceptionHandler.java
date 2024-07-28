package com.ecommerce.application.handler;

import com.ecommerce.application.exception.ApiException;
import com.ecommerce.application.exception.InvalidRequestException;
import com.ecommerce.application.exception.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(NOT_FOUND.value(), ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ExceptionResponse> handleApiException(ApiException ex) {
        var exception = new ExceptionResponse(BAD_REQUEST.value(), ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(exception, BAD_REQUEST);
    }

    @ExceptionHandler(InvalidRequestException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidRequestException(InvalidRequestException ex) {
        var invalidReqException = new ExceptionResponse(BAD_REQUEST.value(), ex.getMessage(), ex.getCode());
        return new ResponseEntity<>(invalidReqException, BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleException(Exception ex) {
        var exception = new ExceptionResponse(INTERNAL_SERVER_ERROR.value(), ex.getMessage(), "GEN_001");
        return new ResponseEntity<>(exception, INTERNAL_SERVER_ERROR);
    }
}
