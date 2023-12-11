package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.LoginReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.RegisterReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.LoginRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.RegisterRes;
import com.miniproject.onlinequizapplication.service.UserService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<APIResponse> register(@RequestBody RegisterReq registerReq) {
        APIResponse<RegisterRes> response = APIResponse.<RegisterRes>builder()
                .result(userService.register(registerReq))
                .status("Success").build();
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<APIResponse> login(@RequestBody LoginReq loginReq, HttpServletRequest request) {
        APIResponse<LoginRes> response = APIResponse.<LoginRes>builder()
                .result(userService.login(loginReq,request))
                .status("Success").build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
