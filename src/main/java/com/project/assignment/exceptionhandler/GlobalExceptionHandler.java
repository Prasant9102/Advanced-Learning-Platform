package com.project.assignment.exceptionhandler;

import com.project.assignment.exception.ErrorMessage;
import com.project.assignment.exception.UserAlreadyExistException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Order(Integer.MIN_VALUE)
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyExistException.class)
    public ResponseEntity<Object> handleClientAlreadyExistsException(UserAlreadyExistException ex) {
        ErrorMessage message = new ErrorMessage(HttpStatus.CONFLICT.value(), ex.getLocalizedMessage());
        return new ResponseEntity<>(message, HttpStatus.CONFLICT);
    }
}
