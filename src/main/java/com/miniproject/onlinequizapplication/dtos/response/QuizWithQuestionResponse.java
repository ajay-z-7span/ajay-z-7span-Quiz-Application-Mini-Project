package com.miniproject.onlinequizapplication.dtos.response;

import java.util.List;
public record QuizWithQuestionResponse(Integer id, String name, Integer passingMarks, List<QuestionResponse> questions) {
}
