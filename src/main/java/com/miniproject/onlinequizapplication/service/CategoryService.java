package com.miniproject.onlinequizapplication.service;

import com.miniproject.onlinequizapplication.dtos.request.CategoryModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.CategoryRequest;
import com.miniproject.onlinequizapplication.dtos.response.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse addCategory(CategoryRequest request);
    List<CategoryResponse> getCategories();
    CategoryResponse updateCategory(CategoryModifyRequest request);
    String deleteCategory(Integer id);
}
