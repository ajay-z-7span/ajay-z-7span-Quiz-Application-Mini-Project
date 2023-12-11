package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.AnswerReq;
import com.miniproject.onlinequizapplication.dto.RequestDTO.ResultReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizWithQuestionRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.AnswerRes;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.ResultRes;
import com.miniproject.onlinequizapplication.entity.*;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.repository.ResultRepository;
import com.miniproject.onlinequizapplication.service.StudentService;
import com.miniproject.onlinequizapplication.util.UserSession;
import com.miniproject.onlinequizapplication.util.mapper.QuizMapper;
import com.miniproject.onlinequizapplication.util.mapper.StudentMapper;
import com.miniproject.onlinequizapplication.util.mapper.UserMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final QuizRepository quizRepository;

    private final ResultRepository resultRepository;

    public StudentServiceImpl(QuizRepository quizRepository, ResultRepository resultRepository) {
        this.quizRepository = quizRepository;
        this.resultRepository = resultRepository;
    }

    @Override
    public List<QuizRes> activeQuizzes() {
       List<Quiz>  quizRes = quizRepository.findByIsActiveTrue();
       if(quizRes.isEmpty()){
           throw new RuntimeOperationException("No any quiz active yet", HttpStatus.NOT_FOUND);
       }

        return QuizMapper.QUIZ_MAPPER.listOfModelToDTO(quizRes);
    }

    @Override
    public QuizWithQuestionRes takeQuiz(Integer id) {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(quiz.isEmpty()){
            throw new RuntimeOperationException("No quiz found with this id :"+id,HttpStatus.NOT_FOUND);
        }
        return QuizMapper.QUIZ_MAPPER.modelWithQuestionToDTO(quiz.get());
    }

    @Override
    public AnswerRes answer(AnswerReq answerReq, User userSession) {
        Optional<Quiz> quiz = quizRepository.findById(answerReq.quizId());
        if(quiz.isEmpty()) throw new RuntimeOperationException("No quiz found with this id : "+answerReq.quizId(),HttpStatus.NOT_FOUND);
        Optional<Question> question = quiz.get().getQuestions().stream().filter(question1 -> question1.getId()==answerReq.questionId()).findFirst();
        if(question.isEmpty()) throw new RuntimeOperationException("No question found with this id : "+ answerReq.questionId(),HttpStatus.NOT_FOUND);

        Result result = resultRepository.findByQuizIdAndUserId(answerReq.quizId(),userSession.getId());
        List<String> attemptQuestion = null;
        if(result==null){
            result = new Result();
        }
        if(result.getQuestionAttempts()==null){
            attemptQuestion = new ArrayList<>();
        }else {
            attemptQuestion =  new ArrayList<>(List.of(result.getQuestionAttempts().split(",")));
        }

        if(attemptQuestion.size()>quiz.get().getQuestions().size()){
            throw new RuntimeOperationException("You already attempt this quiz",HttpStatus.BAD_REQUEST);
        }
        if(!attemptQuestion.contains(answerReq.questionId()+"")){
            attemptQuestion.add(answerReq.questionId()+"");
        }else {
            throw new RuntimeOperationException("You already attempt this question",HttpStatus.BAD_REQUEST);
        }
        result.setQuiz(quiz.get());
        result.setUser(userSession);
        Integer calculatedScrore = calculateScore(question.get().getOptions(),answerReq.optionId());
        result.setScored(result.getScored()!=null?result.getScored()+calculatedScrore:calculatedScrore);
        result.setIsPassed(result.getScored() != null && result.getScored() >= quiz.get().getPassingMarks());
        result.setQuestionAttempts(String.join(",",attemptQuestion));
        Result result1 = resultRepository.save(result);
        if(result1!=null){
            return StudentMapper.STUDENT_MAPPER.msgToDTO("Answer submmited");
        }else {
            throw new RuntimeOperationException("Answer not submmited",HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public List<QuizRes> attemptedQuiz(User userSession) {

        List<Result> results = resultRepository.findByUserId(userSession.getId());
        if(results.isEmpty()) {
            throw new RuntimeOperationException("You not attempt any quiz yet",HttpStatus.NOT_FOUND);
        }
        List<Quiz> quizzes =  new ArrayList<>();
        for (Result result: results){
            quizzes.add(result.getQuiz());
        }
        return QuizMapper.QUIZ_MAPPER.listOfModelToDTO(quizzes);
    }

    @Override
    public ResultRes result(ResultReq resultReq) {
        Optional<Quiz> quiz = quizRepository.findById(resultReq.quizId());
        if(quiz.isEmpty()) throw new RuntimeOperationException("No quiz found with this id : "+resultReq.quizId(),HttpStatus.NOT_FOUND);

        Result result = resultRepository.findByQuizIdAndUserId(resultReq.quizId(), UserSession.getUserSession().getId());
        if(result!=null){
                return StudentMapper.STUDENT_MAPPER.modelToDTO(result);
        }else {
        throw new RuntimeOperationException("You not attempt quiz",HttpStatus.BAD_REQUEST);
        }

    }

    public Integer calculateScore(List<Options> options, List<Integer> answer){
        int numOfOptionWrite =0;
        List<Options> trueOptions = options.stream().filter(options1 -> options1.getIsCorrect()==true).collect(Collectors.toList());
        if(trueOptions.size()==answer.size()){
            for (Integer i:answer){

                Optional<Options> getOneOption = options.stream().filter(options1 -> options1.getId()==i).findFirst();

                if(getOneOption.isEmpty()) throw new RuntimeOperationException("No option found with this id : "+i,HttpStatus.NOT_FOUND);

                Optional<Options> getOneOptionFromTrue = trueOptions.stream().filter(options1 -> options1.getId()==i).findFirst();

                if(getOneOptionFromTrue.isPresent()&& getOneOptionFromTrue.get().getIsCorrect()) numOfOptionWrite++;
            }
            return (trueOptions.size()==numOfOptionWrite) ?1:0;
        }else {
            return 0;
        }
    }
}
