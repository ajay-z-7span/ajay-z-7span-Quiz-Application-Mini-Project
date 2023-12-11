package com.miniproject.onlinequizapplication.service;


import com.miniproject.onlinequizapplication.dto.RequestDTO.OptionReq;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;

public interface OptionService {
    String addOptionForQuetion(Integer id, OptionReq optionReq);
}
