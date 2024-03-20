package ru.gb.webservice.dto.user;

import lombok.Data;
import ru.gb.webservice.enums.Role;

import java.util.UUID;

@Data
public class UserDTO {
    private Long id;
    private String login;
    private String firstName;
    private String lastName;
    private String email;
    private Role role;
    private UUID uid;
    private String password;
}
