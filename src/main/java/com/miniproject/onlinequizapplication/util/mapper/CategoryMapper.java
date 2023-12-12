package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dtos.request.CategoryRequest;
import com.miniproject.onlinequizapplication.dtos.response.CategoryResponse;
import com.miniproject.onlinequizapplication.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    Category dtoToModel(CategoryRequest request);
    CategoryResponse modelToDTO(Category category);
    List<CategoryResponse> listOfModelToDTO(List<Category> categoryList);

}
