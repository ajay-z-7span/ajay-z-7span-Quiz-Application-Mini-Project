package com.miniproject.onlinequizapplication.service.impl;

import com.miniproject.onlinequizapplication.dtos.request.CategoryModifyRequest;
import com.miniproject.onlinequizapplication.dtos.request.CategoryRequest;
import com.miniproject.onlinequizapplication.dtos.response.CategoryResponse;
import com.miniproject.onlinequizapplication.entity.Category;
import com.miniproject.onlinequizapplication.exception.RuntimeOperationException;
import com.miniproject.onlinequizapplication.repository.CategoryRepository;
import com.miniproject.onlinequizapplication.service.CategoryService;
import com.miniproject.onlinequizapplication.util.Constant;
import com.miniproject.onlinequizapplication.util.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryResponse addCategory(CategoryRequest request) {
        CategoryResponse response;
        try {
            log.info("CategoryServiceImpl:addCategory execution started.");
            Category category = categoryMapper.dtoToModel(request);
            log.debug("CategoryServiceImpl:addCategory request parameter {}.", request);
            response = categoryMapper.modelToDTO(categoryRepository.save(category));
            log.debug("CategoryServiceImpl:addCategory received response from database {}.", response);

        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while adding category to database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("CategoryServiceImpl:addCategory execution ended.");
        return response;
    }

    @Override
    public List<CategoryResponse> getCategories() {
        List<CategoryResponse> response;
        try {
            log.info("CategoryServiceImpl:addCategory execution started.");
            List<Category> categoryList = categoryRepository.findAll();
            if (categoryList.isEmpty()) {
                throw new RuntimeOperationException(Constant.NO_RECORD_FOUND, HttpStatus.NO_CONTENT);
            }
            response = categoryMapper.listOfModelToDTO(categoryList);
            log.debug("CategoryServiceImpl:getCategories received response from database {}.", response);

        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        }catch (Exception e) {
            log.error("CategoryServiceImpl:getCategories exception occurred while fetching from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while fetching category from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("CategoryServiceImpl:getCategories execution ended.");
        return response;
    }

    @Override
    public CategoryResponse updateCategory(CategoryModifyRequest request) {
        CategoryResponse response;
        try {
            log.info("CategoryServiceImpl:updateCategory execution ended.");
            Category category = categoryRepository.findById(request.categoryId()).orElseThrow(() -> new RuntimeOperationException(Constant.NO_CATEGORY_FOUND + request.categoryId(), HttpStatus.BAD_REQUEST));
            log.debug("CategoryServiceImpl:updateCategory request parameter {}.", request);
            category.setName(request.name());
            Category saved = categoryRepository.save(category);
            response = categoryMapper.modelToDTO(saved);
            log.debug("CategoryServiceImpl:updateCategory received response from database {}.", response);

        }catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        } catch (Exception e) {
            log.error("CategoryServiceImpl:updateCategory exception occurred while updating category to database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while updating category to database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("CategoryServiceImpl:updateCategory execution ended.");
        return response;
    }

    @Override
    public String deleteCategory(Integer id) {
        String response;
        try {
            log.info("CategoryServiceImpl:deleteCategory execution ended.");
            categoryRepository.findById(id).orElseThrow(() -> new RuntimeOperationException(Constant.NO_CATEGORY_FOUND + id, HttpStatus.BAD_REQUEST));
            categoryRepository.deleteById(id);
            log.debug("CategoryServiceImpl:deleteCategory deleted successful from database.");
            response = Constant.CATEGORY_ADDED_SUCCESS;

        } catch (RuntimeOperationException ex) {
            log.error("CategoryServiceImpl:addCategory exception occurred while persisting to database, Exception message {}", ex.getMessage());
            throw new RuntimeOperationException(ex.getMessage(), ex.getHttpStatus());
        }catch (Exception e) {
            log.error("CategoryServiceImpl:deleteCategory exception occurred while deleting category from database, Exception message {}", e.getMessage());
            throw new RuntimeOperationException("Exception occurred while deleting category from database", HttpStatus.EXPECTATION_FAILED);
        }
        log.info("CategoryServiceImpl:deleteCategory execution ended.");
        return response;
    }
}
