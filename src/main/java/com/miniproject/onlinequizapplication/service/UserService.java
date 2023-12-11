package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dto.RequestDTO.LoginReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.RegisterReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.LoginRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.RegisterRes;
import jakarta.servlet.http.HttpServletRequest;

public interface UserService {
    RegisterRes register(RegisterReq registerReq);

    LoginRes login(LoginReq loginReq, HttpServletRequest request);
}
