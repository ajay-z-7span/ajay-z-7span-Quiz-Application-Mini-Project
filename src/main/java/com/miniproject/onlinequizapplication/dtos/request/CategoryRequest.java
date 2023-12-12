package com.miniproject.onlinequizapplication.dtos.request;

import jakarta.validation.constraints.NotBlank;

public record CategoryRequest(@NotBlank(message = "Name should not be blank") String name) {
}
