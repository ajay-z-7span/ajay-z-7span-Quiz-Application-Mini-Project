package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.QuestionRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuestionResponse;
import com.miniproject.onlinequizapplication.service.QuestionService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.Constant;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<String>> add(@Valid @RequestBody QuestionRequest request) {
        log.info("QuestionController:add execution started.");
        APIResponse<String> response =  new APIResponse<>(Constant.SUCCESS,questionService.addQuestionForQuiz(request)) ;
        log.info("QuestionController:add execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<QuestionResponse>>> get()  {
        log.info("QuestionController:get execution started.");
        APIResponse<List<QuestionResponse>>  response = new APIResponse<>(Constant.SUCCESS,questionService.getQuestions());
        log.info("QuestionController:get execution ended.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
