package com.olafenko.taskman.controllers;

import com.olafenko.taskman.models.dtos.users.RegistrationRequest;
import com.olafenko.taskman.servicies.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody @Valid RegistrationRequest request){
        userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body("User registered successfully!");
    }


}
