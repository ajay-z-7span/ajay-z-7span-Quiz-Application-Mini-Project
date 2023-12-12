package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Username should not be blank")String userName,@NotBlank(message = "Password should not be blank") String password) {
}
