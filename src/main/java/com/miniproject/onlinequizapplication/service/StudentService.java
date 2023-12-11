package com.miniproject.onlinequizapplication.service;


import com.miniproject.onlinequizapplication.dto.RequestDTO.AnswerReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.ResultReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizWithQuestionRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.AnswerRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.ResultRes;
import com.miniproject.onlinequizapplication.entity.User;

import java.util.List;

public interface StudentService {
    List<QuizRes> activeQuizzes();

    QuizWithQuestionRes takeQuiz(Integer id);

    AnswerRes answer(AnswerReq answerReq, User userSession);

    List<QuizRes> attemptedQuiz(User userSession);

    ResultRes result(ResultReq resultReq);
}
