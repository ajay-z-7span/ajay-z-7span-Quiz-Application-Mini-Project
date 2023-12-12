package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.QuestionRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuestionResponse;
import com.miniproject.onlinequizapplication.entity.Question;
import com.miniproject.onlinequizapplication.entity.Quiz;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.QuestionRepository;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.service.QuestionService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.mapper.QuestionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuestionServiceImpl implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuizRepository quizRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuizRepository quizRepository, QuestionMapper questionMapper) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
        this.questionMapper = questionMapper;
    }

    @Override
    public String addQuestionForQuiz(QuestionRequest request) {
        String response;
        try {
            log.info("QuestionServiceImpl:addQuestionForQuiz execution started.");
            Quiz quiz = quizRepository.findById(request.quizId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND, HttpStatus.NOT_FOUND));
            log.debug("QuestionServiceImpl:addQuestionForQuiz request parameter {}.", request);
            Question question = questionMapper.dtoToModel(request);
            quiz.getQuestions().add(question);
            quizRepository.save(quiz);
            response = Constant.QUESTION_ADDED_SUCCESS;
            log.info("QuestionServiceImpl:addQuestionForQuiz request question added successful.");
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        }catch (Exception e) {
            log.error("QuestionServiceImpl:addQuestionForQuiz exception occurred while persisting to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while adding question to database", HttpStatus.EXPECTATION_FAILED);
        }
        return response;
    }

    @Override
    public List<QuestionResponse> getQuestions() {
        List<QuestionResponse> responses;
        try {
            log.info("QuestionServiceImpl:getQuestions execution started.");
            List<Question> questions = questionRepository.findAll();
            if (questions.isEmpty()) {
                throw new RuntimeOperationException(Constant.NO_RECORD_FOUND, HttpStatus.NO_CONTENT);
            }
            responses = questionMapper.listOfModelToDTO(questions);
            log.debug("QuestionServiceImpl:getQuestions received response from database {}.", responses);
        } catch (Exception e) {
            log.error("QuestionServiceImpl:getQuestions exception occurred while retrieving question from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while retrieving question from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("QuestionServiceImpl:getQuestions execution ended.");
        return responses;
    }
}
