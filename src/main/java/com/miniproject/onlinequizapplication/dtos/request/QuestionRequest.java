package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record QuestionRequest(@Min(value = 0,message = "Please enter valid number")@NotNull(message = "QuizId should not be blank")Integer quizId , @NotBlank(message = "Content should not be blank")String content) {
}
