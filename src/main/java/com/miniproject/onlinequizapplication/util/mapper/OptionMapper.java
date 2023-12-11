package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.RequestDTO.OptionReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuestionRes;
import com.miniproject.onlinequizapplication.entity.Options;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OptionMapper {

    OptionMapper OPTION_MAPPER = Mappers.getMapper(OptionMapper.class);

    Options dtoToModel(OptionReq optionReq);


}
