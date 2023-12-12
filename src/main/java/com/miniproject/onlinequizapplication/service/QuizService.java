package com.miniproject.onlinequizapplication.service;


import com.miniproject.onlinequizapplication.dtos.request.QuizModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.QuizRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;

import java.util.List;

public interface QuizService {
    QuizResponse createQuizByCategory(QuizRequest request);
    List<QuizResponse> getQuizzes();
    QuizResponse modifyQuiz(QuizModifyRequest request);
    String deleteQuiz(Integer id);
    String quizStatus(Integer id,String status);
}
