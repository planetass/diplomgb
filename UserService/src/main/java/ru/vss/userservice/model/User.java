package ru.vss.userservice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.*;

/**
 * Модель пользователя.
 * Для валидации использован Hibernate Validator
 * https://docs.jboss.org/hibernate/stable/validator/reference/en-US/html_single/
 */
@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Логин. Только латинский буквы и цифры. Первая должна быть латинская буква.
     */
    @Column(name = "LOGIN",nullable = false, unique = true)
    @Pattern(message = "Bad formed login: ${validatedValue}",
            regexp = "^[A-Za-z]+[a-zA-Z0-9]*$"
    )
    @Length(min = 2,max = 12)
    private String login;

    @Column(name = "FNAME",nullable = false)
    @Pattern(message = "Bad formed person First name: ${validatedValue}",
            regexp = "^[A-Z]+[a-z]*$|^[А-Я]+[а-я]+$"
    )
    @Length(min = 2,max = 20)
    private String firstName;

    @Pattern(message = "Bad formed person Second name: ${validatedValue}",
            regexp = "^[A-Z]+[a-z]*$|^[А-Я]+[а-я]+$"
    )
    @Column(name = "LNAME")
    @Length(min = 2,max = 20)
    private String lastName;

    @Column(name = "email",nullable = false, unique = true)
    @Email (message = "Bad formed email: ${validatedValue}")
    private String email;

    @Column(name = "ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;

    @Column(name = "UID", nullable = false, unique = true)
    UUID uid;

//    @Pattern(message = "Bad formed password name: ${validatedValue}",
//            regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$"
//    )
    @Column(name = "PASSWORD", nullable = false)
    private String password;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return List.of(new SimpleGrantedAuthority(role.name()));

    }

    @Override
    public String getUsername() {
        return this.login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", uid=" + uid +
                ", password='" + password + '\'' +
                '}';
    }
}
