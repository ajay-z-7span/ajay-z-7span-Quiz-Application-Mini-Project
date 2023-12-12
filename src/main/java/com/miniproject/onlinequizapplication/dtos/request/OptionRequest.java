package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record OptionRequest(@Min(value = 0,message ="Please enter valid number")@NotNull(message = "Question Id should not be blank") Integer questionId, @NotBlank(message = "Content should not be blank")String content, @NotNull(message = "isCorrect should not be blank")Boolean isCorrect) {
}
