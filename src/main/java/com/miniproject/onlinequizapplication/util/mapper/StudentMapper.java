package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.response.ResultResponse;
import com.miniproject.onlinequizapplication.entity.Result;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentMapper {
    ResultResponse modelToDTO(Result result);

}
