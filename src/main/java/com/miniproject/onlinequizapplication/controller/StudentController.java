package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.AnswerReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.ResultReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.AnswerRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizWithQuestionRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.ResultRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.StudentService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.UserSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/activeQuizzes")
    public ResponseEntity<APIResponse> activeQuizzes() {

        if (UserSession.getUserSession() != null) {
            APIResponse<List<QuizRes>> response = APIResponse.<List<QuizRes>>builder().result(studentService.activeQuizzes()).status("Success").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new RuntimeOperationException("Please do login first", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/takeQuiz/{quizId}")
    public ResponseEntity<APIResponse> takeQuiz(@PathVariable("quizId") Integer id) {

        if (UserSession.getUserSession() != null) {
            APIResponse<QuizWithQuestionRes> response = APIResponse.<QuizWithQuestionRes>builder().result(studentService.takeQuiz(id)).status("Success").build();
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            throw new RuntimeOperationException("Please do login first", HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/answer")
    public ResponseEntity<APIResponse> answer(@RequestBody AnswerReq answerReq) {
        if (UserSession.getUserSession() != null) {
          APIResponse<AnswerRes> apiResponse = APIResponse.<AnswerRes>builder().result( studentService.answer(answerReq,UserSession.getUserSession())).status("Success").build();
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        } else {
            throw new RuntimeOperationException("Please do login first", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/attemptedQuiz")
    public ResponseEntity<APIResponse> attemptedQuiz() {
        if (UserSession.getUserSession() != null) {
            APIResponse< List<QuizRes>> apiResponse = APIResponse.< List<QuizRes>>builder().result( studentService.attemptedQuiz(UserSession.getUserSession())).status("Success").build();
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        } else {
            throw new RuntimeOperationException("Please do login first", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/result")
    public ResponseEntity<APIResponse> result(@RequestBody ResultReq resultReq){
        if (UserSession.getUserSession() != null) {
            APIResponse<ResultRes> apiResponse = APIResponse.<ResultRes>builder().result( studentService.result(resultReq)).status("Success").build();
            return new ResponseEntity<>(apiResponse,HttpStatus.OK);
        } else {
            throw new RuntimeOperationException("Please do login first", HttpStatus.BAD_REQUEST);
        }

    }
}
