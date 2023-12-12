package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record AnswerRequest(@NotNull(message = "QuizId should not be blank") Integer quizId, @NotNull(message = "QuestionId should not be blank") Integer questionId, @NotEmpty(message = "OptionId list should not be blank") List<Integer> optionId) {
}
