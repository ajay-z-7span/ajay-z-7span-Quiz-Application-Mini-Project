package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record RegisterRequest(@NotBlank(message = "FirstName should not be blank")String firstName, @NotBlank(message = "UserName should not be blank")String userName, @NotBlank(message = "Password should not be blank")String password) {
}
