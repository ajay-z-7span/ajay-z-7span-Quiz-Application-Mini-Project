package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuizReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.QuizService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {


    private final QuizService quizService;

    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }


    @PostMapping("/quiz/{categoryId}")
    public ResponseEntity<APIResponse> createQuizByCategory(@PathVariable("categoryId") int id, QuizReq quizReq) {
      APIResponse<QuizRes> apiResponse = APIResponse.<QuizRes>builder().status("Success").result(quizService.createQuizByCategory(id, quizReq)).build();
    return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/quizzes")
    public ResponseEntity<APIResponse>getQuizzes()  {
        APIResponse<List<QuizRes>> apiResponse  = APIResponse.<List<QuizRes>>builder().status("Success").result(quizService.getQuizzes()).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/quiz/{quizId}")
    public ResponseEntity<APIResponse> modifyQuiz(@PathVariable("quizId")Integer quizId,@RequestBody QuizReq quizReq){
        APIResponse<QuizRes> apiResponse = APIResponse.<QuizRes>builder().status("Quiz update successful").result(quizService.modifyQuiz(quizId,quizReq)).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @DeleteMapping("/quiz/{quizId}")
    public ResponseEntity<APIResponse> deleteQuiz(@PathVariable("quizId") Integer quizId){
        APIResponse<String> apiResponse  = APIResponse.<String>builder().status("Success").result(quizService.deleteQuiz(quizId)).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/activate/{quizId}")
    public ResponseEntity<APIResponse> activateQuiz(@PathVariable("quizId") Integer quizId){
        APIResponse<String> apiResponse  = APIResponse.<String>builder().status("Success").result(quizService.quizStatus(quizId,"Activate")).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/end/{quizId}")
    public ResponseEntity<APIResponse> endQuiz(@PathVariable("quizId") Integer quizId){
        APIResponse<String> apiResponse  = APIResponse.<String>builder().status("Success").result(quizService.quizStatus(quizId,"End")).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

}
