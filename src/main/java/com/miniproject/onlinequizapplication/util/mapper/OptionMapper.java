package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.request.OptionRequest;
import com.miniproject.onlinequizapplication.entity.Options;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OptionMapper {
    Options dtoToModel(OptionRequest request);

}
