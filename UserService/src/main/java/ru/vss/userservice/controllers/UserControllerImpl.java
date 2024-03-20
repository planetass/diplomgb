package ru.vss.userservice.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;
import ru.vss.userservice.dto.PasswordReset;
import ru.vss.userservice.dto.SignUpRequest;
import ru.vss.userservice.exaptions.UserException;
import ru.vss.userservice.mappers.UserDtoConverter;
import ru.vss.userservice.model.User;
import ru.vss.userservice.services.UserService;


import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserControllerImpl implements UserController{
    private final UserService userService;
    private final UserDtoConverter userConverter;



    @GetMapping("/get-admin")
    @Operation(summary = "Получить роль ADMIN (для демонстрации)")
    public void getAdmin() {
        System.out.println("Get Admin");
        userService.getAdmin();
    }

    @Override
    @PostMapping("/admin")
    @Operation(summary = "Создание пользователя вручную")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
        public ResponseEntity<User> create (@RequestBody User user) {

        try {
            //Пересобираем пользователя из запроса согласно правилам регистрации
            return ResponseEntity.ok(userConverter.dtoToUser(userConverter.userToDto(user, user.getPassword())));

        } catch (UserException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }


    /**
     * Возвращает пользователя по логину
     * @param login
     * @return
     */
    @Override
    @GetMapping("/admin/{login}")
    @Operation(summary = "поиск пользователя по логину")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<User> findByLogin(@PathVariable String login) {

       try{
           return ResponseEntity.ok(userService.findByLogin(login));
       }catch (UserException e){
           return ResponseEntity.badRequest().build();
       }
    }

    /**
     * Возвращает список всех пользователей
     * @return
     */
    @Override
    @GetMapping("/admin")
    @Operation(summary = "Возвращает всех пользователей")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public ResponseEntity<List<User>> findUsers() {
        try{
            return ResponseEntity.ok(userService.findUsers());
        }catch (UserException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Удаление пользователя по логину
     * @param login
     */
    @Override
    @DeleteMapping("/admin/{login}")
    @Operation(summary = "Возвращает всех пользователей")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    public void deleteUserByLogin(@PathVariable String login) {
        userService.deleteUserByLogin(login);
    }

    /**
     * Обновление полей пользователя ограниченное
     * @param userDto
     * @return
     */
    @Override
    @PutMapping("")
    @Operation(summary = "Обновляет пользователя c по ограниченным полям")
    public ResponseEntity<User> updateUserFromUser(@RequestBody SignUpRequest userDto) {

        try{
            User user = userConverter.dtoToUser(userDto);
            return ResponseEntity.ok(userService.updateUserFromUser(user));
        }catch (UserException e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Обновление полей пользователя полное
     * @param userDto
     * @return
     */
    @Override
    @PutMapping("/admin")
    @PreAuthorize("hasAnyRole('ADMIN','MODERATOR')")
    @Operation(summary = "Обновляет пользователя c по ограниченным полям")
    public ResponseEntity<User> updateUserFromAdmin(@RequestBody SignUpRequest userDto) {


        try{
            User user = userConverter.dtoToUser(userDto);
            return ResponseEntity.ok(userService.updateUserFromAdmin(user));
        }catch (UserException e){
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Обновляет пароль
     * @param passwordReset
     * @return
     */
    @Override
    @PostMapping("/password-reset")
    @Operation(summary = "Обновляет пользователя c по ограниченным полям")
    public ResponseEntity<User> updatePassword(@RequestBody PasswordReset passwordReset) {
        try{
            return ResponseEntity.ok(userService.updatePassword(passwordReset));
        }catch (UserException e){
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Возвращает пользователя по логину
     * @param
     * @return
     */

    @GetMapping("/self")
    @Operation(summary = "Возвращает самого себя")
    public ResponseEntity<User> findSelf() {
        System.out.println("====");


        System.out.println("====");

        try{
            System.out.println("ответ");
            User usr=userService.findSelf();
            System.out.println("****");
            System.out.println(usr);
            System.out.println("****");
            return ResponseEntity.ok(usr);
        }catch (UserException e){
            System.out.println("хрень");
            return ResponseEntity.badRequest().build();

        }
    }

}
