package com.olafenko.taskman.models.dtos.users;

import jakarta.validation.constraints.*;

public record RegistrationRequest(

        @NotBlank
        @Size(min = 6,max=15,message = "Username length must be between 6 and 15 chars.")
        @Pattern(regexp = "^[A-Za-z]\\w+$", message = "Invalid username format.")
        String username,

        @NotBlank
        @Size(min = 8,max=30,message = "Password length must be between 8 and 30.")
        @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])$", message = "Invalid password format.")
        String password,

        @NotBlank
        @Email(message = "Invalid email format.")
        String email) {

}
