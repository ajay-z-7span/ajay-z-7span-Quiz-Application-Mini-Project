package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuizReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizWithQuestionRes;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface QuizMapper {

    QuizMapper QUIZ_MAPPER = Mappers.getMapper(QuizMapper.class);

    Quiz dtoToModel(QuizReq quizReq);

    QuizRes modelToDTO(Quiz quiz);

    List<QuizRes> listOfModelToDTO(List<Quiz> quizzes);

    QuizWithQuestionRes modelWithQuestionToDTO(Quiz quiz);
}
