package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.OptionReq;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.OptionService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/option")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @PostMapping("/option/{questionId}")
    public ResponseEntity<APIResponse> addOptionForQuetion(@PathVariable("questionId") Integer id, OptionReq optionReq) {
          APIResponse<String> response = APIResponse.<String>builder().result(optionService.addOptionForQuetion(id,optionReq)).status("Success").build();
          return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
