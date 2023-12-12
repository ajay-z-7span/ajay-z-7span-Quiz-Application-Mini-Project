package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dtos.request.OptionRequest;

public interface OptionService {
    String addOptionForQuestion(OptionRequest request);
}
