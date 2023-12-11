package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dto.RequestDTO.CategoryReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;

import java.util.List;


public interface CategoryService {
    CategoryRes addCategory(CategoryReq categoryReq);
    List<CategoryRes> getCategories();

    CategoryRes updateCategory(Integer categoryId, CategoryReq categoryReq);

    String deleteCategory(Integer categoryId);
}
