package com.appetize.exception;

import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.validation.ConstraintViolationException;

import java.util.Date;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, String message){
        ErrorResponse error = new ErrorResponse();
        error.setMessage(message);
        error.setStatus(httpStatus.value());
        error.setDate(new Date());
        return new ResponseEntity<>(error, httpStatus);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleException(NoSuchElementException exc){
        return buildResponseEntity(HttpStatus.NOT_FOUND, exc.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleException(DuplicateKeyException exc){
        return buildResponseEntity(HttpStatus.CONFLICT, exc.getMessage());
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleException(IllegalArgumentException exc){
        return buildResponseEntity(HttpStatus.BAD_REQUEST, exc.getMessage());
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        String errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return buildResponseEntity(HttpStatus.BAD_REQUEST, errors);
    }

    @ExceptionHandler
    protected ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException ex){
        String errors = ex.getConstraintViolations()
                .stream()
                .map(cv -> cv.getPropertyPath() + ": " + cv.getMessage())
                .collect(Collectors.joining(", "));

        return buildResponseEntity(HttpStatus.BAD_REQUEST, errors);
    }
}
