package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.OptionRequest;
import com.miniproject.onlinequizapplication.service.OptionService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.Constant;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/option")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<String>> add(@Valid @RequestBody OptionRequest request) {
        log.info("OptionService:add execution started.");
        APIResponse<String> response = new APIResponse<>(Constant.SUCCESS,optionService.addOptionForQuestion(request));
        log.info("OptionService:add execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
