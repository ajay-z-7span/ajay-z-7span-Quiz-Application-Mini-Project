package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CategoryModifyRequest(@Min(value = 0,message = "Please enter valid number") @NotNull(message = "CategoryId should not be blank") Integer categoryId, @NotBlank(message = "Name should not be blank") String name) {
}
