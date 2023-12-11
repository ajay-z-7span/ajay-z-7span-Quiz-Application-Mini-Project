package com.miniproject.onlinequizapplication.util.mapper;

import com.miniproject.onlinequizapplication.dto.RequestDTO.CategoryReq;
import com.miniproject.onlinequizapplication.dto.ResponseDTO.CategoryRes;
import com.miniproject.onlinequizapplication.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface CategoryMapper {

    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category dtoToModel(CategoryReq categoryReq);

    CategoryRes modelToDTO(Category category);

    List<CategoryRes> listOfModelToDTO(List<Category> categories);


}
