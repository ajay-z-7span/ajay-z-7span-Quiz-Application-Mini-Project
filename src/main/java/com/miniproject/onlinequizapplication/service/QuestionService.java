package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuestionReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuestionRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;

import java.util.List;

public interface QuestionService {
    String addQuestionForQuiz(Integer id, QuestionReq questionReq);

    List<QuestionRes> getQuestions() ;
}
