package ru.vss.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Pattern;

import lombok.Data;

@Data
@Schema(description = "Изменение пароля")public class PasswordReset {

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Pattern(message = "Bad formed password name: ${validatedValue}",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    )
    private String password1;


    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Pattern(message = "Bad formed password name: ${validatedValue}",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    )
    private String password2;
}
