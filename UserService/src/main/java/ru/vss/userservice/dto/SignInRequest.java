package ru.vss.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(description = "Запрос на аутентификацию")
public class SignInRequest {
    @Schema(description = "User Login", example = "mylogin")
    @Size(min = 2, max = 12, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    @Pattern(message = "Bad formed login: ${validatedValue}",
            regexp = "^[A-Za-z]+[a-zA-Z0-9]*$"
    )
    private String login;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Pattern(message = "Bad formed password name: ${validatedValue}",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    )
    private String password;
}
