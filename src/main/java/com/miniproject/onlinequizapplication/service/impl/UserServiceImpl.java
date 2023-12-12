package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.LoginRequest;
import com.miniproject.onlinequizapplication.dtos.request.RegisterRequest;
import com.miniproject.onlinequizapplication.dtos.response.LoginResponse;
import com.miniproject.onlinequizapplication.dtos.response.RegisterResponse;
import com.miniproject.onlinequizapplication.entity.User;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.UserRepository;
import com.miniproject.onlinequizapplication.service.UserService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.UserSession;
import com.miniproject.onlinequizapplication.util.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public RegisterResponse register(RegisterRequest request) {
        RegisterResponse response;

        try {
            log.info("UserServiceImpl:register execution started.");
            log.debug("UserServiceImpl:register request parameter {}.", request);
            User user = userMapper.dtoToModel(request);
            userRepository.save(user);
            response = new RegisterResponse(Constant.REGISTRATION_SUCCESS);
            log.debug("UserServiceImpl:register received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("UserServiceImpl:register exception occurred while register user in database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while register user in database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("UserServiceImpl:register execution ended.");
        return response;
    }

    @Override
    public LoginResponse login(LoginRequest request, HttpServletRequest servletRequest) {

        LoginResponse response;
        try {
            log.info("UserServiceImpl:login execution started.");
            User user = userRepository.findByUserNameAndPassword(request.userName(), request.password()).orElseThrow(() -> new RuntimeOperationException("Bad credentials", HttpStatus.BAD_REQUEST));
            log.debug("UserServiceImpl:login request parameter {}.", request);
            HttpSession session = servletRequest.getSession();
            session.setAttribute("User", user);
            UserSession.setUserInfo(servletRequest);
            response = new LoginResponse(Constant.LOGIN_SUCCESS);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("UserServiceImpl:login exception occurred while login user in database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while login user in database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("UserServiceImpl:login execution ended.");
        return response;
    }
}
