package com.miniproject.onlinequizapplication.exception;

import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.BeanValidationDTO;
import com.miniproject.onlinequizapplication.util.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RuntimeExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeOperationException.class)
    public ResponseEntity<ErrorMessage> runtimeOperationException(RuntimeOperationException e){
        return new ResponseEntity<>(new ErrorMessage(e.getMessage(), e.getHttpStatus()),e.getHttpStatus());
    }
}
