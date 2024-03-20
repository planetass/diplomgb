package ru.vss.userservice.services;

import ru.vss.userservice.dto.PasswordReset;
import ru.vss.userservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(User user);
    User findByLogin(String login);
    List<User> findUsers();
    void deleteUserByLogin(String Login);
    User updateUserFromAdmin(User user);
    User updateUserFromUser(User user);
    User updatePassword(PasswordReset passwordReset);
    User findSelf();
    void getAdmin();



}
