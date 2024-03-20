package ru.vss.userservice.controllers;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.vss.userservice.dto.JwtAuthenticationResponse;
import ru.vss.userservice.dto.PasswordReset;
import ru.vss.userservice.dto.SignUpRequest;
import ru.vss.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserController {
    ResponseEntity<User> create(User user);

    ResponseEntity<User> findByLogin(String login);

    ResponseEntity<List<User>> findUsers();

    void deleteUserByLogin(String Login);

    ResponseEntity<User> updateUserFromAdmin(SignUpRequest userDto);

    ResponseEntity<User> updateUserFromUser(SignUpRequest userDto);

    ResponseEntity<User> updatePassword(PasswordReset passwordReset);
}