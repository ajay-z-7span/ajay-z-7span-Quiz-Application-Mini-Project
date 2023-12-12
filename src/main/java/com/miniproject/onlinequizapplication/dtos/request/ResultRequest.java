package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResultRequest(@NotNull(message = "QuizId should not be blank") @Min(value = 0,message = "Please give valid number") Integer quizId) {
}
