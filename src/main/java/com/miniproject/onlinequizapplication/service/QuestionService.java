package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dtos.request.QuestionRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuestionResponse;

import java.util.List;

public interface QuestionService {
    String addQuestionForQuiz(QuestionRequest request);
    List<QuestionResponse> getQuestions();
}
