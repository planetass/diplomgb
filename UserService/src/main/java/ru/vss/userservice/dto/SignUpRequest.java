package ru.vss.userservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;


@Data
@Builder
@Schema(description = "Запрос на регистрацию")
public class SignUpRequest {

    @Schema(description = "User Login", example = "mylogin")
    @Size(min = 2, max = 12, message = "Имя пользователя должно содержать от 5 до 50 символов")
    @NotBlank(message = "Имя пользователя не может быть пустыми")
    @Pattern(message = "Bad formed login: ${validatedValue}",
            regexp = "^[A-Za-z]+[a-zA-Z0-9]*$"
    )
    private String login;

    @Schema(description = "User email", example = "jondoe@gmail.com")
    @Size(min = 5, max = 255, message = "Адрес электронной почты должен содержать от 5 до 255 символов")
    @NotBlank(message = "Адрес электронной почты не может быть пустыми")
    @Email(message = "Email адрес должен быть в формате user@example.com")
    private String email;

    @Schema(description = "Пароль", example = "my_1secret1_password")
    @Pattern(message = "Bad formed password name: ${validatedValue}",
            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
    )
    private String password;

    @Column(name = "FNAME",nullable = false)
    @Pattern(message = "Bad formed person First name: ${validatedValue}",
            regexp = "^[A-Z]+[a-z]*$|^[А-Я]+[а-я]+$"
    )
    @Length(min = 2,max = 20)
    private String firstName;

    @Pattern(message = "Bad formed person Second name: ${validatedValue}",
            regexp = "^[A-Z]+[a-z]*$|^[А-Я]+[а-я]+$"
    )    @Column(name = "LNAME")
    @Length(min = 2,max = 20)
    private String lastName;

}

