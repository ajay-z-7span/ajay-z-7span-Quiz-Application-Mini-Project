package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.OptionRequest;
import com.miniproject.onlinequizapplication.entity.Options;
import com.miniproject.onlinequizapplication.entity.Question;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.QuestionRepository;
import com.miniproject.onlinequizapplication.service.OptionService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.mapper.OptionMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OptionServiceImpl implements OptionService {

    private final QuestionRepository questionRepository;
    private final OptionMapper optionMapper;

    public OptionServiceImpl(QuestionRepository questionRepository, OptionMapper optionMapper) {
        this.questionRepository = questionRepository;
        this.optionMapper = optionMapper;
    }


    @Override
    public String addOptionForQuestion(OptionRequest request) {
        String response;
        try {
            log.info("OptionServiceImpl:addOptionForQuestion execution started.");
            Question question = questionRepository.findById(request.questionId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_QUESTION_FOUND, HttpStatus.NOT_FOUND));
            log.debug("OptionServiceImpl:addOptionForQuestion request parameter {}.", request);
            Options options = optionMapper.dtoToModel(request);
            question.getOptions().add(options);
            questionRepository.save(question);
            response = Constant.OPTION_ADDED_SUCCESS;
            log.info("OptionServiceImpl:addOptionForQuestion request option added successful.");
        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        }catch (Exception e) {
            log.error("OptionServiceImpl:addOptionForQuestion exception occurred while persisting to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while adding option to database", HttpStatus.EXPECTATION_FAILED);
        }
        return response;
    }
}
