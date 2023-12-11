package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.LoginReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.RegisterReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.LoginRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.RegisterRes;
import com.miniproject.onlinequizapplication.entity.User;
import com.miniproject.onlinequizapplication.repository.UserRepository;
import com.miniproject.onlinequizapplication.service.UserService;
import com.miniproject.onlinequizapplication.util.UserSession;
import com.miniproject.onlinequizapplication.util.mapper.UserMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public RegisterRes register(RegisterReq registerReq) {

        User user = UserMapper.USER_MAPPER.dtoToModel(registerReq);
        if(userRepository.save(user)!=null){
           return UserMapper.USER_MAPPER.registerMsgToDTO("Registration Successfull");
        }else {
            return UserMapper.USER_MAPPER.registerMsgToDTO("Something went wrong please try again");
        }

    }

    @Override
    public LoginRes login(LoginReq loginReq,HttpServletRequest request) {

        Optional<User> user = userRepository.findByUserNameAndPassword(loginReq.userName(),loginReq.password());
        if(user.isPresent()){
            HttpSession session = request.getSession();
            session.setAttribute("User",user.get());
            UserSession.setUserInfo(request);
            return UserMapper.USER_MAPPER.loginMsgToDTO("Login Successfull");
        }else {
            return UserMapper.USER_MAPPER.loginMsgToDTO("Bad credentials");
        }

    }
}
