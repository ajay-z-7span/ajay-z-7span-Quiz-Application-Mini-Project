package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.AnswerRequest;
import com.miniproject.onlinequizapplication.dtos.request.ResultRequest;
import com.miniproject.onlinequizapplication.dtos.response.AnswerResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizWithQuestionResponse;
import com.miniproject.onlinequizapplication.dtos.response.ResultResponse;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.StudentService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.UserSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/activeQuizzes")
    public ResponseEntity<APIResponse<List<QuizResponse>>> activeQuizzes() {
        log.info("StudentController:activeQuizzes execution started.");
        if (UserSession.getUserSession() != null) {
            APIResponse<List<QuizResponse>> response = new APIResponse<>(Constant.SUCCESS,studentService.activeQuizzes());
            log.info("StudentController:activeQuizzes execution ended.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("StudentController:activeQuizzes exception occurred user not found");
            throw new RuntimeOperationException(Constant.LOGIN_ALERT, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/takeQuiz/{quizId}")
    public ResponseEntity<APIResponse<QuizWithQuestionResponse>> takeQuiz(@PathVariable("quizId") Integer id) {
        log.info("StudentController:takeQuiz execution started.");
        if (UserSession.getUserSession() != null) {
            APIResponse<QuizWithQuestionResponse> response = new APIResponse<>(Constant.SUCCESS, studentService.takeQuiz(id));
            log.info("StudentController:takeQuiz execution ended.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("StudentController:takeQuiz exception occurred user not found");
            throw new RuntimeOperationException(Constant.LOGIN_ALERT, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/answer")
    public ResponseEntity<APIResponse<AnswerResponse>> answer(@Valid @RequestBody AnswerRequest request) {
        log.info("StudentController:answer execution started.");
        if (UserSession.getUserSession() != null) {
            APIResponse<AnswerResponse> response = new APIResponse<>(Constant.SUCCESS,studentService.answer(request, UserSession.getUserSession()));
            log.info("StudentController:answer execution ended.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("StudentController:answer exception occurred user not found");
            throw new RuntimeOperationException(Constant.LOGIN_ALERT, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/attemptedQuiz")
    public ResponseEntity<APIResponse<List<QuizResponse>>> attemptedQuiz() {
        log.info("StudentController:attemptedQuiz execution started.");
        if (UserSession.getUserSession() != null) {
            APIResponse<List<QuizResponse>> response = new APIResponse<>(Constant.SUCCESS,studentService.attemptedQuiz(UserSession.getUserSession()));
            log.info("StudentController:attemptedQuiz execution ended.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("StudentController:attemptedQuiz exception occurred user not found");
            throw new RuntimeOperationException(Constant.LOGIN_ALERT, HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/result")
    public ResponseEntity<APIResponse<ResultResponse>> result(@Valid @RequestBody ResultRequest request) {
        log.info("StudentController:result execution started.");
        if (UserSession.getUserSession() != null) {
            APIResponse<ResultResponse> response = new APIResponse<>(Constant.SUCCESS,studentService.result(request));
            log.info("StudentController:result execution ended.");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            log.error("StudentController:result exception occurred user not found");
            throw new RuntimeOperationException(Constant.LOGIN_ALERT, HttpStatus.BAD_REQUEST);
        }
    }
}
