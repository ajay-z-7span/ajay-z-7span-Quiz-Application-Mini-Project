package com.miniproject.onlinequizapplication.dto.ResponseDTO;

import com.miniproject.onlinequizapplication.entity.Question;

import java.util.List;

public record QuizWithQuestionRes(Integer id, String name, Integer passingMarks, List<QuestionRes> questions) {
}
