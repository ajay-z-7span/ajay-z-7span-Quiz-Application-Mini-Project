package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.request.QuizRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizWithQuestionResponse;
import com.miniproject.onlinequizapplication.entity.Quiz;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface QuizMapper {

    Quiz dtoToModel(QuizRequest quizRequest);

    QuizResponse modelToDTO(Quiz quiz);

    List<QuizResponse> listOfModelToDTO(List<Quiz> quizzes);

    QuizWithQuestionResponse modelWithQuestionToDTO(Quiz quiz);
}
