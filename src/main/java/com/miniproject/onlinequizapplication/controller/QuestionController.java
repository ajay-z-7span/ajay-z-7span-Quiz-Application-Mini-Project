package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuestionReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuestionRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.CategoryService;
import com.miniproject.onlinequizapplication.service.QuestionService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }


    @PostMapping("/question/{quizId}")
    public ResponseEntity<APIResponse> addQuestionForQuiz(@PathVariable("quizId") Integer id, @RequestBody QuestionReq questionReq) {
        APIResponse<String> response = APIResponse.<String>builder().result(questionService.addQuestionForQuiz(id,questionReq)).status("Success").build() ;
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/questions")
    public ResponseEntity<APIResponse> getQuestions()  {
       APIResponse<List<QuestionRes>>  response = APIResponse.<List<QuestionRes>>builder().result(questionService.getQuestions()).status("Success").build() ;
    return new ResponseEntity<>(response,HttpStatus.OK);
    }
}
