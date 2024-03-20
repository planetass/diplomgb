package ru.vss.userservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vss.userservice.dto.JwtAuthenticationResponse;
import ru.vss.userservice.dto.SignInRequest;
import ru.vss.userservice.dto.SignUpRequest;
import ru.vss.userservice.exaptions.UserException;
import ru.vss.userservice.services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@Tag(name = "Аутентификация")
public class AuthControllerImpl implements AuthController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/sign-up")
    @Override
    public ResponseEntity<JwtAuthenticationResponse> signUp(@RequestBody @Valid SignUpRequest request) {
        try {
            return ResponseEntity.ok(authenticationService.signUp(request));
        }catch (UserException e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/sign-in")
    @Override
    public JwtAuthenticationResponse signIn(@RequestBody @Valid SignInRequest request) {
//    public JwtAuthenticationResponse signIn(@RequestBody SignInRequest request) {
        System.out.println(request);
        String msg = authenticationService.signIn(request).toString();
        System.out.println(msg);


        return authenticationService.signIn(request);
    }
}