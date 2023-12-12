package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dtos.request.LoginRequest;
import com.miniproject.onlinequizapplication.dtos.request.RegisterRequest;
import com.miniproject.onlinequizapplication.dtos.response.LoginResponse;
import com.miniproject.onlinequizapplication.dtos.response.RegisterResponse;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    RegisterResponse register(RegisterRequest request);
    LoginResponse login(LoginRequest request, HttpServletRequest servletRequest);
}
