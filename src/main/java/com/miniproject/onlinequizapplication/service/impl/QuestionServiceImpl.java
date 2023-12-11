package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.QuestionReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.QuestionRes;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.entity.Question;
import com.miniproject.onlinequizapplication.entity.Quiz;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.QuestionRepository;
import com.miniproject.onlinequizapplication.repository.QuizRepository;
import com.miniproject.onlinequizapplication.service.QuestionService;
import com.miniproject.onlinequizapplication.util.mapper.CategoryMapper;
import com.miniproject.onlinequizapplication.util.mapper.QuestionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    private QuestionRepository questionRepository;

    private QuizRepository quizRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, QuizRepository quizRepository) {
        this.questionRepository = questionRepository;
        this.quizRepository = quizRepository;
    }

    @Override
    public String addQuestionForQuiz(Integer id, QuestionReq questionReq) throws RuntimeOperationException {
        Optional<Quiz> quiz = quizRepository.findById(id);
        if(quiz.isEmpty()){
            throw new RuntimeOperationException("No quiz found with this id :"+id, HttpStatus.NOT_FOUND);
        }
        Question question= QuestionMapper.QUESTION_MAPPER.dtoToModel(questionReq);
        quiz.get().getQuestions().add(question);
        quizRepository.save(quiz.get());
        return "Question added successfull";
    }

    @Override
    public List<QuestionRes> getQuestions() {
        List<Question> questions = questionRepository.findAll();
        if(questions.isEmpty()){
            throw new RuntimeOperationException("No Recored Found", HttpStatus.NO_CONTENT);
        }
        return QuestionMapper.QUESTION_MAPPER.listOfModelToDTO(questions);
    }
}
