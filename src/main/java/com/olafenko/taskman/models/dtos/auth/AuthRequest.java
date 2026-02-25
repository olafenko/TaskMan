package com.olafenko.taskman.models.dtos.auth;

import jakarta.validation.constraints.NotBlank;

public record AuthRequest (

        @NotBlank(message = "Username cannot be empty!")
        String username,

        @NotBlank(message = "Username cannot be empty!")
        String password){
}
