package com.miniproject.onlinequizapplication.controller;

import com.miniproject.onlinequizapplication.dto.RequestDTO.CategoryReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.service.CategoryService;
import com.miniproject.onlinequizapplication.util.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {


    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping("/category")
    public ResponseEntity<APIResponse> addCategory(@RequestBody CategoryReq categoryReq) {
        APIResponse<CategoryRes> apiResponse = APIResponse.<CategoryRes>builder().status("Success").result(categoryService.addCategory(categoryReq)).build();
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/categories")
    public ResponseEntity<APIResponse> getCategories(){
       APIResponse<List<CategoryRes>> apiResponse  = APIResponse.<List<CategoryRes>>builder().status("Success").result(categoryService.getCategories()).build();
    return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @PutMapping("/category/{categoryId}")
    public ResponseEntity<APIResponse> updateCategory(@PathVariable("categoryId") Integer categoryId,@RequestBody CategoryReq categoryReq){
        APIResponse<CategoryRes> apiResponse  = APIResponse.<CategoryRes>builder().status("Category Update Sucessful").result(categoryService.updateCategory(categoryId,categoryReq)).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }

    @DeleteMapping("/category/{categoryId}")
    public ResponseEntity<APIResponse> deleteCategory(@PathVariable("categoryId") Integer categoryId){
        APIResponse<String> apiResponse  = APIResponse.<String>builder().status("Success").result(categoryService.deleteCategory(categoryId)).build();
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);

    }


}
