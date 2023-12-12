package com.miniproject.onlinequizapplication.service;


import com.miniproject.onlinequizapplication.dtos.request.AnswerRequest;
import com.miniproject.onlinequizapplication.dtos.request.ResultRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizWithQuestionResponse;
import com.miniproject.onlinequizapplication.dtos.response.AnswerResponse;
import com.miniproject.onlinequizapplication.dtos.response.ResultResponse;
import com.miniproject.onlinequizapplication.entity.User;

import java.util.List;

public interface StudentService {
    List<QuizResponse> activeQuizzes();
    QuizWithQuestionResponse takeQuiz(Integer id);
    AnswerResponse answer(AnswerRequest request, User user);
    List<QuizResponse> attemptedQuiz(User user);
    ResultResponse result(ResultRequest request);
}
