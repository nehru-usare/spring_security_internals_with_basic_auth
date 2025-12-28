package com.smart.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smart.auth.domain.User;
import com.smart.auth.payload.CreateUserRequest;
import com.smart.auth.payload.CreateUserResponse;
import com.smart.auth.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // USER REGISTRATION (PUBLIC)
    @PostMapping("/register")
    public ResponseEntity<CreateUserResponse> register(
            @RequestBody CreateUserRequest request) {
    	User user = authService.registerUser(request);
        return ResponseEntity.ok(new CreateUserResponse(user.getId(), user.getUsername()));
    }
}
