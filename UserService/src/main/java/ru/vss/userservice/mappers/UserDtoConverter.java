package ru.vss.userservice.mappers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.vss.userservice.dto.SignUpRequest;
import ru.vss.userservice.model.Role;
import ru.vss.userservice.model.User;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserDtoConverter {
    private final PasswordEncoder passwordEncoder;
    public  SignUpRequest userToDto(User user, String password){
        return SignUpRequest.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .email(user.getEmail())
                .password(password)
                .build();
    }
    public  User dtoToUser(SignUpRequest dto){
        return User.builder()
                .login(dto.getLogin())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(passwordEncoder.encode(dto.getPassword()))
                .role(Role.ROLE_USER)
                .uid(UUID.randomUUID())
                .build();
    }

}
