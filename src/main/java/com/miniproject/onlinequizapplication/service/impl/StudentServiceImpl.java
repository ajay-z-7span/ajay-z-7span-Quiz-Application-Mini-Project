package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.AnswerRequest;
import com.miniproject.onlinequizapplication.dtos.request.ResultRequest;
import com.miniproject.onlinequizapplication.dtos.response.AnswerResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizResponse;
import com.miniproject.onlinequizapplication.dtos.response.QuizWithQuestionResponse;
import com.miniproject.onlinequizapplication.dtos.response.ResultResponse;
import com.miniproject.onlinequizapplication.entity.*;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.repository.ResultRepository;
import com.miniproject.onlinequizapplication.service.StudentService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.UserSession;
import com.miniproject.onlinequizapplication.util.mapper.QuizMapper;
import com.miniproject.onlinequizapplication.util.mapper.StudentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {
    private final QuizRepository quizRepository;
    private final ResultRepository resultRepository;
    private final StudentMapper studentMapper;
    private final QuizMapper quizMapper;

    public StudentServiceImpl(QuizRepository quizRepository, ResultRepository resultRepository, StudentMapper studentMapper, QuizMapper quizMapper) {
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
        this.studentMapper = studentMapper;
        this.quizMapper = quizMapper;
    }

    @Override
    public List<QuizResponse> activeQuizzes() {
        List<QuizResponse> response;
        try {
            log.info("StudentServiceImpl:activeQuizzes execution started.");
            List<Quiz> quizRes = quizRepository.findByIsActiveTrue();
            if (quizRes.isEmpty()) {
                throw new RuntimeOperationException("No any quiz active yet", HttpStatus.NOT_FOUND);
            }
            response = quizMapper.listOfModelToDTO(quizRes);
            log.debug("StudentServiceImpl:activeQuizzes received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:activeQuizzes exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("StudentServiceImpl:activeQuizzes exception occurred while retrieving quiz from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while retrieving quiz from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("StudentServiceImpl:activeQuizzes execution ended.");
        return response;
    }

    @Override
    public QuizWithQuestionResponse takeQuiz(Integer id) {
        QuizWithQuestionResponse response;
        try {
            log.info("StudentServiceImpl:takeQuiz execution started.");
            Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND + id, HttpStatus.NOT_FOUND));
            response = quizMapper.modelWithQuestionToDTO(quiz);
            log.debug("StudentServiceImpl:takeQuiz received response from database {}.", response);
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:takeQuiz exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("StudentServiceImpl:takeQuiz exception occurred while retrieving quiz from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while retrieving quiz from database", HttpStatus.EXPECTATION_FAILED);
        }
        return response;
    }

    @Override
    public AnswerResponse answer(AnswerRequest request, User userSession) {
        AnswerResponse response;
        try {
            log.info("StudentServiceImpl:answer execution started.");
            Quiz quiz = quizRepository.findById(request.quizId()).orElseThrow(() -> new RuntimeOperationException("No quiz found with this id : " + request.quizId(), HttpStatus.NOT_FOUND));

            Question question = quiz.getQuestions().stream().filter(question1 -> question1.getId().equals(request.questionId())).findFirst().orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUESTION_FOUND + request.questionId(), HttpStatus.NOT_FOUND));

            Result result = resultRepository.findByQuizIdAndUserId(request.quizId(), userSession.getId());
            List<String> attemptQuestion = null;
            if (result == null) {
                result = new Result();
            }
            if (result.getQuestionAttempts() == null) {
                attemptQuestion = new ArrayList<>();
            } else {
                attemptQuestion = new ArrayList<>(List.of(result.getQuestionAttempts().split(",")));
            }

            if (attemptQuestion.size() > quiz.getQuestions().size()) {
                throw new RuntimeOperationException(Constant.ALREADY_ATTEMPT_QUIZ, HttpStatus.BAD_REQUEST);
            }

            if (!attemptQuestion.contains(request.questionId().toString())) {
                attemptQuestion.add(request.questionId().toString());
            } else {
                throw new RuntimeOperationException(Constant.ALREADY_ATTEMPT_QUESTION, HttpStatus.BAD_REQUEST);
            }

            result.setQuiz(quiz);
            result.setUser(userSession);
            Integer calculatedScrore = calculateScore(question.getOptions(), request.optionId());
            result.setScored(result.getScored() != null ? result.getScored() + calculatedScrore : calculatedScrore);
            result.setIsPassed(result.getScored() != null && result.getScored() >= quiz.getPassingMarks());
            result.setQuestionAttempts(String.join(",", attemptQuestion));
            resultRepository.save(result);
            response = new AnswerResponse(Constant.ANSWER_SUBMITTED);
            log.debug("StudentServiceImpl:answer received response from database {}.", response);

        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:answer exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("StudentServiceImpl:answer exception occurred while submit answer to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while submit answer to database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("StudentServiceImpl:takeQuiz execution ended.");
        return response;

    }

    @Override
    public List<QuizResponse> attemptedQuiz(User user) {

        List<QuizResponse> response;
        try {
            log.info("StudentServiceImpl:attemptedQuiz execution started.");
            List<Result> results = resultRepository.findByUserId(user.getId());
            if (results.isEmpty()) {
                throw new RuntimeOperationException(Constant.QUIZ_NOT_ATTEMPT, HttpStatus.NOT_FOUND);
            }

            List<Quiz> quizList = new ArrayList<>();
            results.forEach(result -> quizList.add(result.getQuiz()));
            response = quizMapper.listOfModelToDTO(quizList);
            log.debug("StudentServiceImpl:attemptedQuiz received response from database {}.", response);

        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:attemptedQuiz exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("StudentServiceImpl:attemptedQuiz exception occurred while retrieving quiz from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while retrieving quiz from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("StudentServiceImpl:attemptedQuiz execution ended.");
        return response;
    }

    @Override
    public ResultResponse result(ResultRequest request) {
        ResultResponse response;
        try {
            log.info("StudentServiceImpl:result execution started.");
            quizRepository.findById(request.quizId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUIZ_FOUND + request.quizId(), HttpStatus.NOT_FOUND));

            Result result = resultRepository.findByQuizIdAndUserId(request.quizId(), UserSession.getUserSession().getId());
            if (result != null) {
                response = studentMapper.modelToDTO(result);
            } else {
                throw new RuntimeOperationException(Constant.QUIZ_NOT_ATTEMPT, HttpStatus.BAD_REQUEST);
            }
        }
        catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:result exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("StudentServiceImpl:result exception occurred while retrieving result from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while retrieving result from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("StudentServiceImpl:result execution ended.");
        return response;
    }

    public Integer calculateScore(List<Options> options, List<Integer> answer) {
        log.info("StudentServiceImpl:calculateScore execution started.");
        int numOfOptionWrite = 0;
        List<Options> trueOptions = options.stream().filter(options1 -> options1.getIsCorrect() == true).collect(Collectors.toList());

        if (trueOptions.size() == answer.size()) {
            for (Integer i : answer) {
                options.stream().filter(options1 -> options1.getId().equals(i)).findFirst().orElseThrow(() -> new RuntimeOperationException(Constant.NO_OPTIONS_FOUND + i, HttpStatus.NOT_FOUND));
                Optional<Options> getOneOptionFromTrue = trueOptions.stream().filter(options1 -> options1.getId().equals(i)).findFirst();
                if (getOneOptionFromTrue.isPresent() && getOneOptionFromTrue.get().getIsCorrect()) numOfOptionWrite++;
            }
            log.info("StudentServiceImpl:calculateScore execution ended.");
            return (trueOptions.size() == numOfOptionWrite) ? 1 : 0;
        } else {
            log.info("StudentServiceImpl:calculateScore execution ended.");
            return 0;
        }

    }
}
