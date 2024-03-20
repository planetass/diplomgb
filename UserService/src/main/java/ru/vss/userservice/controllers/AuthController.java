package ru.vss.userservice.controllers;


import org.springframework.http.ResponseEntity;
import ru.vss.userservice.dto.JwtAuthenticationResponse;
import ru.vss.userservice.dto.SignInRequest;
import ru.vss.userservice.dto.SignUpRequest;

public interface AuthController {
    ResponseEntity<JwtAuthenticationResponse> signUp(SignUpRequest request);
    JwtAuthenticationResponse signIn(SignInRequest request);
}
