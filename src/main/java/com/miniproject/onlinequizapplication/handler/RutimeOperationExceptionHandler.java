package com.miniproject.onlinequizapplication.handler;

import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.util.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class RutimeOperationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeOperationException.class)
    public ResponseEntity<ErrorMessage> runtimeOperationException(RuntimeOperationException e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), e.getHttpStatus()),e.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorMessage> generalExceptions(Exception e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), HttpStatus.BAD_REQUEST),HttpStatus.BAD_REQUEST);
    }
}
