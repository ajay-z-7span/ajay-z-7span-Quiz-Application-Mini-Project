package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuizReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuizRes;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.entity.Quiz;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.CategoryRepository;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.service.QuizService;
import com.miniproject.onlinequizapplication.util.mapper.CategoryMapper;
import com.miniproject.onlinequizapplication.util.mapper.QuizMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuizServiceImpl implements QuizService {


    private final QuizRepository quizRepository;

    private final CategoryRepository categoryRepository;

    public QuizServiceImpl(QuizRepository quizRepository, CategoryRepository categoryRepository) {
        this.quizRepository = quizRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public QuizRes createQuizByCategory(int id, QuizReq quizReq) throws RuntimeOperationException {

        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw  new RuntimeOperationException("No category found with this id :"+id, HttpStatus.NOT_FOUND);
        }
        Quiz quiz = QuizMapper.QUIZ_MAPPER.dtoToModel(quizReq);
        quiz.setCategory(category.get());
        return  QuizMapper.QUIZ_MAPPER.modelToDTO(quizRepository.save(quiz));
    }

    @Override
    public List<QuizRes> getQuizzes() {
        List<Quiz> quizRes = quizRepository.findAll();
        if(quizRes.isEmpty()){
            throw new RuntimeOperationException("No Recored Found", HttpStatus.NO_CONTENT);
        }
        return QuizMapper.QUIZ_MAPPER.listOfModelToDTO(quizRes);
    }

    @Override
    public QuizRes modifyQuiz(Integer quizId, QuizReq quizReq) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if(quiz.isPresent()){

            quiz.get().setName(quizReq.name());
            quiz.get().setIsActive(quizReq.isActive());
            quiz.get().setPassingMarks(quizReq.passingMarks());

            Quiz updatedQuiz = quizRepository.save(quiz.get());
            if(updatedQuiz != null){
               return QuizMapper.QUIZ_MAPPER.modelToDTO(updatedQuiz);
            }else {
                throw  new RuntimeOperationException("Quiz not updated please try after some time :"+quizId, HttpStatus.NOT_FOUND);
            }
        }else {
            throw  new RuntimeOperationException("No quiz found with this id :"+quizId, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public String deleteQuiz(Integer quizId) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if(quiz.isPresent()){
           quizRepository.deleteById(quizId);
            return "Quiz deleted successful";
        }else {
            throw  new RuntimeOperationException("No quiz found with this id :"+quizId, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public String quizStatus(Integer quizId,String status) {
        Optional<Quiz> quiz = quizRepository.findById(quizId);
        if(quiz.isPresent()){
            if(status.equals("Activate")){
                quiz.get().setIsActive(true);
            } else{
                quiz.get().setIsActive(false);
            }


            Quiz updatedQuiz = quizRepository.save(quiz.get());
            if(updatedQuiz != null){
                return status.equals("Activate") ? "Quiz activated" : "Quiz ended";
            }else {
                throw  new RuntimeOperationException("Quiz not updated please try after some time :"+quizId, HttpStatus.NOT_FOUND);
            }
        }else {
            throw  new RuntimeOperationException("No quiz found with this id :"+quizId, HttpStatus.NOT_FOUND);
        }
    }
}
