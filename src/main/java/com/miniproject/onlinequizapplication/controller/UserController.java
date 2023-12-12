package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.LoginRequest;
import com.miniproject.onlinequizapplication.dtos.request.RegisterRequest;
import com.miniproject.onlinequizapplication.dtos.response.LoginResponse;
import com.miniproject.onlinequizapplication.dtos.response.RegisterResponse;
import com.miniproject.onlinequizapplication.service.UserService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.Constant;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<APIResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
        log.info("UserController:register execution started.");
        APIResponse<RegisterResponse> response = new APIResponse<>(Constant.SUCCESS,userService.register(request));
        log.info("UserController:register execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse<LoginResponse>> login(@Valid @RequestBody LoginRequest request, HttpServletRequest servletRequest) {
        log.info("UserController:login execution started.");
        APIResponse<LoginResponse> response = new APIResponse<>(Constant.SUCCESS,userService.login(request,servletRequest));
        log.info("UserController:login execution ended.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
