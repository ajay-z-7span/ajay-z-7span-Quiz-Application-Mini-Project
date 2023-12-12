package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dtos.request.CategoryModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.CategoryRequest;
import com.miniproject.onlinequizapplication.dtos.response.CategoryResponse;
import com.miniproject.onlinequizapplication.service.CategoryService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import com.miniproject.onlinequizapplication.util.Constant;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/category")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<APIResponse<CategoryResponse>> add( @Valid @RequestBody CategoryRequest request) {
        log.info("CategoryController:add execution started.");
        APIResponse<CategoryResponse> response = new APIResponse<>("Success", categoryService.addCategory(request));
        log.info("CategoryController:add execution ended.");
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<CategoryResponse>>> get() {
        log.info("CategoryController:get execution started.");
        APIResponse<List<CategoryResponse>> response =  new APIResponse<>(Constant.SUCCESS,categoryService.getCategories());
        log.info("CategoryController:get execution ended.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<APIResponse<CategoryResponse>> update(@Valid @RequestBody CategoryModifyRequest request) {
        log.info("CategoryController:update execution started.");
        APIResponse<CategoryResponse> response = new APIResponse<>(Constant.CATEGORY_UPDATED,categoryService.updateCategory(request));
        log.info("CategoryController:update execution ended.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<String>> delete(@PathVariable("id") Integer categoryId) {
        log.info("CategoryController:delete execution started.");
        APIResponse<String> response = new APIResponse<>(Constant.SUCCESS,categoryService.deleteCategory(categoryId));
        log.info("CategoryController:delete execution ended.");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
