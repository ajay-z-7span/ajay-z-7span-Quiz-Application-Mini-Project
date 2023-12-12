package com.miniproject.onlinequizapplication.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Setter
@Getter
public class RuntimeOperationException extends RuntimeException{
    String message;

    HttpStatus httpStatus;
    public RuntimeOperationException(String message, HttpStatus httpStatus) {
        super(message);
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
