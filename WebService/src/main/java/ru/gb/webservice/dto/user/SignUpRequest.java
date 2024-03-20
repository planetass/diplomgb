package ru.gb.webservice.dto.user;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SignUpRequest {

    private String login;
    private String email;
    private String password;
    private String firstName;
    private String lastName;

}

