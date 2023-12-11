package com.miniproject.onlinequizapplication.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Setter
@Getter
public class RuntimeOperationException extends RuntimeException{
    String msg;

    HttpStatus httpStatus;

    public RuntimeOperationException(String msg, HttpStatus httpStatus) {
        super(msg);
        this.msg = msg;
        this.httpStatus = httpStatus;
    }
}
