package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.OptionReq;
import com.miniproject.onlinequizapplication.entity.Options;
import com.miniproject.onlinequizapplication.entity.Question;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.OptionRepository;
import com.miniproject.onlinequizapplication.repository.QuestionRepository;
import com.miniproject.onlinequizapplication.service.OptionService;
import com.miniproject.onlinequizapplication.util.mapper.OptionMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class OptionServiceImpl implements OptionService {

    private final QuestionRepository questionRepository;

    private final OptionRepository optionRepository;

    public OptionServiceImpl(QuestionRepository questionRepository, OptionRepository optionRepository) {
        this.questionRepository = questionRepository;
        this.optionRepository = optionRepository;
    }

    @Override
    public String addOptionForQuetion(Integer id, OptionReq optionReq)  {
        Optional<Question> question = questionRepository.findById(id);
        if(question.isEmpty()){
            throw new RuntimeOperationException("No question found with this id :"+id, HttpStatus.NOT_FOUND);
        }
        Options options = OptionMapper.OPTION_MAPPER.dtoToModel(optionReq);
        question.get().getOptions().add(options);
//        options.setQuestion(question.get());
        questionRepository.save(question.get());
        return "Option added successfull";
    }
}
