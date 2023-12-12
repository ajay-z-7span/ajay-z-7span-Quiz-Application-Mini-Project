package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.request.RegisterRequest;
import com.miniproject.onlinequizapplication.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper {
    User dtoToModel(RegisterRequest registerRequest);
}
