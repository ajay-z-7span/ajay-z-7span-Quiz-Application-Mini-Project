package com.miniproject.onlinequizapplication.exception;

import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.BeanValidationDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class BeanValidationExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<APIResponse<List<BeanValidationDTO>>> onMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<BeanValidationDTO> validationDTOS = new ArrayList<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(fieldError -> {
                    BeanValidationDTO validationDTO = new BeanValidationDTO(fieldError.getField(),fieldError.getDefaultMessage());
                    validationDTOS.add(validationDTO);
                });
        APIResponse<List<BeanValidationDTO>> response = new APIResponse<>("error",validationDTOS);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    
}
