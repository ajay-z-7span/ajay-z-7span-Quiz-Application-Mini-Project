package com.miniproject.onlinequizapplication.service;


import com.miniproject.onlinequizapplication.dto.RequestDTO.QuizReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;

import java.util.List;

public interface QuizService {
    QuizRes createQuizByCategory(int id, QuizReq quizReq);

    List<QuizRes> getQuizzes();

    QuizRes modifyQuiz(Integer quizId, QuizReq quizReq);

    String deleteQuiz(Integer quizId);

    String quizStatus(Integer quizId,String status);
}
