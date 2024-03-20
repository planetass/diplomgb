package ru.gb.offerservice.config.securety;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ru.gb.offerservice.clients.UserAPI;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl {

    private final UserAPI userAPI;
    /**
     * Получение пользователя по имени пользователя
     * <p>
     * Нужен для Spring Security
     *
     * @return пользователь
     */
    public UserDetailsService userDetailsService() {
        return this::findByLogin;
    }

    /**
     * Получение текущего пользователя
     *
     * @return текущий пользователь
     */
    public User getCurrentUser() {
        // Получение имени пользователя из контекста Spring Security
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByLogin(login);
    }
    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    public User findByLogin(String login) {

        Authentication authenticationToken = SecurityContextHolder.getContext().getAuthentication();

        try {
            User newUser = (User) authenticationToken.getPrincipal();
            return newUser;
        }catch (Exception e){
            return null;
        }
    }

    public User UserSelf(String token) throws JsonProcessingException {
        UserDTO usr= userAPI.self(token);

        System.out.println(usr);
        return User.builder()
                .id(usr.getId())
                .login(usr.getLogin())
                .email(usr.getEmail())
                .firstName(usr.getFirstName())
                .lastName(usr.getLastName())
                .password(usr.getPassword())
                .role(usr.getRole())
                .uid(usr.getUid())
                .build();
    }


}
