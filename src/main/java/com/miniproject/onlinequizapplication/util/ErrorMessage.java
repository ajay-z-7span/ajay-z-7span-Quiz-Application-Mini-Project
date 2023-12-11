package com.miniproject.onlinequizapplication.util;

import org.springframework.http.HttpStatus;

public record ErrorMessage(String msg, HttpStatus httpStatus) {

}
