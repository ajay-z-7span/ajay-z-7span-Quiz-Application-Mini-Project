package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.ResponseDTO.AnswerRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.ResultRes;
import com.miniproject.onlinequizapplication.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper

public interface StudentMapper {

    StudentMapper STUDENT_MAPPER = Mappers.getMapper(StudentMapper.class);
    AnswerRes msgToDTO(String msg);

    ResultRes modelToDTO(Result result);

}
