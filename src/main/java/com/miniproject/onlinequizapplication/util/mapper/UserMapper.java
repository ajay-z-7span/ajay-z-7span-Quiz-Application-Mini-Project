package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.RequestDTO.RegisterReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.LoginRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.RegisterRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.AnswerRes;
import com.miniproject.onlinequizapplication.entity.Result;
import com.miniproject.onlinequizapplication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UserMapper {
    UserMapper USER_MAPPER = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "role", expression = "java(\"User\")")
    User dtoToModel(RegisterReq registerReq);

    RegisterRes registerMsgToDTO(String msg);

    LoginRes loginMsgToDTO(String msg);



}
