package com.miniproject.onlinequizapplication.dtos.response;

import java.util.List;
public record QuestionResponse(Integer id, String content, List<OptionsResponse> options){
}
