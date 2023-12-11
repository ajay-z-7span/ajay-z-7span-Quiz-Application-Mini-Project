package com.miniproject.onlinequizapplication.dto.ResponseDTO;

import com.miniproject.onlinequizapplication.entity.Options;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

public record QuestionRes(Integer id, String content, List<OptionsRes> options){
}
