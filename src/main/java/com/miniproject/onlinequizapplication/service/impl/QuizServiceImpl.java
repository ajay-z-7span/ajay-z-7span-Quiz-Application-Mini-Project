package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.QuizModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.QuizRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.entity.Quiz;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.CategoryRepository;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.service.QuizService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.mapper.QuizMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QuizServiceImpl implements QuizService {

    private final QuizRepository quizRepository;

    private final CategoryRepository categoryRepository;

    private final QuizMapper quizMapper;

    public QuizServiceImpl(QuizRepository quizRepository, CategoryRepository categoryRepository, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
        this.quizMapper = quizMapper;
    }

    @Override
    public QuizResponse createQuizByCategory(QuizRequest request) {
        QuizResponse response;
        try {
            log.info("QuizServiceImpl:createQuizByCategory execution started.");
            Category category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_CATEGORY_FOUND + request.categoryId(), HttpStatus.NOT_FOUND));
            log.debug("QuizServiceImpl:createQuizByCategory request parameter {}.", request);
            Quiz quiz = quizMapper.dtoToModel(request);
            quiz.setCategory(category);
            response = quizMapper.modelToDTO(quizRepository.save(quiz));
            log.debug("QuizServiceImpl:createQuizByCategory received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("QuizServiceImpl:createQuizByCategory exception occurred while persisting to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("QuizServiceImpl:createQuizByCategory execution ended.");
        return response;
    }

    @Override
    public List<QuizResponse> getQuizzes() {
        List<QuizResponse> response;
        try {
            log.info("QuizServiceImpl:getQuizzes execution started.");
            List<Quiz> quizRes = quizRepository.findAll();
            if (quizRes.isEmpty()) {
                throw new RuntimeOperationException(Constant.NO_RECORD_FOUND, HttpStatus.NO_CONTENT);
            }
            response = quizMapper.listOfModelToDTO(quizRes);
            log.debug("QuizServiceImpl:createQuizByCategory received response from database {}.", response);

        } catch (Exception e) {
            log.error("QuizServiceImpl:createQuizByCategory exception occurred while persisting to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("QuizServiceImpl:getQuizzes execution ended.");
        return response;
    }

    @Override
    public QuizResponse modifyQuiz(QuizModifyRequest request) {
        QuizResponse response;
        try {
            log.info("QuizServiceImpl:modifyQuiz execution started.");
            Quiz quiz = quizRepository.findById(request.quizId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND + request.quizId(), HttpStatus.NOT_FOUND));
            log.debug("QuizServiceImpl:modifyQuiz request parameter {}.", request);
            quiz.setName(request.name());
            quiz.setIsActive(request.isActive());
            quiz.setPassingMarks(request.passingMarks());
            Quiz updatedQuiz = quizRepository.save(quiz);
            response = quizMapper.modelToDTO(updatedQuiz);
            log.debug("QuizServiceImpl:modifyQuiz received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("QuizServiceImpl:modifyQuiz exception occurred while updating quiz to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("QuizServiceImpl:modifyQuiz execution ended.");
        return response;
    }

    @Override
    public String deleteQuiz(Integer id) {
        String response;
        try {
            log.info("QuizServiceImpl:deleteQuiz execution started.");
            quizRepository.findById(id).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND + id, HttpStatus.NOT_FOUND));
            quizRepository.deleteById(id);
            response = Constant.QUIZ_DELETED;
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("QuizServiceImpl:deleteQuiz exception occurred while delete quiz from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("QuizServiceImpl:deleteQuiz execution ended.");
        return response;
    }

    @Override
    public String quizStatus(Integer id, String status) {
        String response;
        try {
            log.info("QuizServiceImpl:quizStatus execution started.");
            Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND + id, HttpStatus.NOT_FOUND));
            quiz.setIsActive(status.equals("Activate"));
            Quiz quizResponse = quizRepository.save(quiz);
            response = quizResponse.getIsActive() ? "Quiz activated" : "Quiz ended";
            log.debug("QuizServiceImpl:quizStatus received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("QuizServiceImpl:deleteQuiz exception occurred while status updating to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        log.info("QuizServiceImpl:quizStatus execution started.");
        return response;
    }
}

