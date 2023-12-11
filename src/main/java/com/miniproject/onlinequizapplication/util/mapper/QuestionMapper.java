package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuestionReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.OptionsRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuestionRes;
import com.miniproject.onlinequizapplication.entity.Options;
import com.miniproject.onlinequizapplication.entity.Question;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface  QuestionMapper {

    QuestionMapper QUESTION_MAPPER = Mappers.getMapper(QuestionMapper.class);
    Question dtoToModel(QuestionReq questionReq);

    @Mapping(target = "options.id",source = "question.options.id")
    @Mapping(target = "options.content",source = "question.options.content")
    List<QuestionRes> listOfModelToDTO(List<Question> question);
}
