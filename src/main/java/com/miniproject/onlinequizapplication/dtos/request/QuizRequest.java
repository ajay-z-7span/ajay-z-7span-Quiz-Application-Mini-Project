package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuizRequest(@Min(value = 0,message = "Please enter valid number")@NotNull(message = "CategoryId should not be blank") Integer categoryId, @NotBlank(message = "Content should not be blank")String name, @NotNull(message = "isActive should not be blank")Boolean isActive, @NotNull(message = "Content should not be blank") @Min(value = 0,message = "Please insert valid number") Integer passingMarks) {
}
