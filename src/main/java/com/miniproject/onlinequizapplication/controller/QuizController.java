package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.QuizModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.QuizRequest;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.service.QuizService;
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
@RequestMapping("quiz")
public class QuizController {
    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<QuizResponse>> create(@Valid @RequestBody QuizRequest request) {
        log.info("QuizController:add execution started.");
        APIResponse<QuizResponse> response = new APIResponse<>(Constant.SUCCESS,quizService.createQuizByCategory(request));
        log.info("QuizController:add execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<QuizResponse>>> get()  {
        log.info("QuizController:get execution started.");
        APIResponse<List<QuizResponse>> response  =  new APIResponse<>(Constant.SUCCESS,quizService.getQuizzes());
        log.info("QuizController:get execution ended.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<QuizResponse>> modify(@Valid @RequestBody QuizModifyRequest request){
        log.info("QuizController:modify execution started.");
        APIResponse<QuizResponse> response = new APIResponse<>(Constant.SUCCESS,quizService.modifyQuiz(request));
        log.info("QuizController:modify execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable("id") Integer quizId){
        log.info("QuizController:delete execution started.");
        APIResponse<String> response  = new APIResponse<>(Constant.SUCCESS,quizService.deleteQuiz(quizId));
        log.info("QuizController:delete execution ended.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/activate/{id}")
    public ResponseEntity<APIResponse<String>> activate(@PathVariable("id") Integer quizId){
        log.info("QuizController:activate execution started.");
        APIResponse<String> response  = new APIResponse<>(Constant.SUCCESS,quizService.quizStatus(quizId,"Activate"));
        log.info("QuizController:activate execution ended.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PutMapping("/end/{id}")
    public ResponseEntity<APIResponse<String>> end(@PathVariable("id") Integer quizId){
        log.info("QuizController:end execution started.");
        APIResponse<String> response  =  new APIResponse<>(Constant.SUCCESS,quizService.quizStatus(quizId,"End"));
        log.info("QuizController:end execution ended.");
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

}
