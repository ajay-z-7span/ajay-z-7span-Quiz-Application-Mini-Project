package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dto.RequestDTO.CategoryReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.CategoryRepository;
import com.miniproject.onlinequizapplication.service.CategoryService;
import com.miniproject.onlinequizapplication.util.mapper.CategoryMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryRes addCategory(CategoryReq categoryReq) {
        Category category = CategoryMapper.CATEGORY_MAPPER.dtoToModel(categoryReq);
        return CategoryMapper.CATEGORY_MAPPER.modelToDTO(categoryRepository.save(category));
    }

    @Override
    public List<CategoryRes> getCategories(){
        List<Category> categoryRes = categoryRepository.findAll();
        if(categoryRes.isEmpty()){
            throw new RuntimeOperationException("No Recored Found", HttpStatus.NO_CONTENT);
        }
        return CategoryMapper.CATEGORY_MAPPER.listOfModelToDTO(categoryRes);
    }

    @Override
    public CategoryRes updateCategory(Integer categoryId, CategoryReq categoryReq) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
         category.get().setName(categoryReq.name());
         Category category1  = categoryRepository.save(category.get());
         return CategoryMapper.CATEGORY_MAPPER.modelToDTO(category1);
        }else {
            throw new RuntimeOperationException("No category found with this id : "+categoryId,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if(category.isPresent()){
            categoryRepository.deleteById(categoryId);
            return "category deleted successful";
        }else {
            throw new RuntimeOperationException("No category found with this id : "+categoryId,HttpStatus.BAD_REQUEST);
        }
    }
}
