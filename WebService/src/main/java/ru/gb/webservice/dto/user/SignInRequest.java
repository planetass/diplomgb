package ru.gb.webservice.dto.user;


import lombok.Data;

@Data
public class SignInRequest {

    private String login;
    private String password;
}
