package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.request.QuestionRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuestionResponse;
import com.miniproject.onlinequizapplication.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface  QuestionMapper {
    Question dtoToModel(QuestionRequest questionRequest);
    @Mapping(target = "options.id",source = "question.options.id")
    @Mapping(target = "options.content",source = "question.options.content")
    List<QuestionResponse> listOfModelToDTO(List<Question> question);
}
