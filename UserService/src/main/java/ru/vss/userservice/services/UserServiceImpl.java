package ru.vss.userservice.services;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import ru.vss.userservice.dto.PasswordReset;
import ru.vss.userservice.exaptions.UserException;
import ru.vss.userservice.model.Role;
import ru.vss.userservice.model.User;
import ru.vss.userservice.repository.UserRepository;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    /**
     * Добавление пользователя.
     * При добавлении проводится валидация данных согласно модели User.
     *
     * @param user - Экземпляр для добавления
     * @return - Возвращает экземпляр добавленного пользователя или null если не удалось добавть
     */

    @Override
    public User create(User user) throws UserException {

        try {
            user.setUid(UUID.randomUUID());
            repository.save(user);
        } catch (ConstraintViolationException e) {
            throw new UserException(e.getMessage()+"Не валидные данные");
        } catch (DataIntegrityViolationException e) {
            throw new UserException(e.getMessage()+"Не все данные заполнены или дублируются");
        } catch (RuntimeException e) {
            throw new UserException(e.getMessage()+"Все очень плохо"+e.getClass());
        }
        return user;
    }

    /**
     * Получение пользователя по имени пользователя
     *
     * @return пользователь
     */
    @Override
    public User findByLogin(String login) {
        return repository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }

    /**
     * Возвращает список всех пользователей
     * @return - список пользователей
     */
    @Override
    public List<User> findUsers() {
        return repository.findAll();
    }

    /**
     * Удаляет пользователя по логину
     * @param login - логин пользователя которго нужно удалить
     */
    @Override
    public void deleteUserByLogin(String login) {
        repository.deleteUserByLogin(login);
    }

    @Override
    public User updateUserFromAdmin(User user) throws UserException{
        if (user == null || user.getLogin() == null) throw new UserException("Пользователь не найден");
        String login = user.getLogin();

        User userForUpdate = findByLogin(login);

        userForUpdate.setEmail(user.getEmail());
        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());
//        userForUpdate.setPassword(passwordEncoder.encode(user.getPassword()));

            return updateUser(userForUpdate);
    }

    @Override
    public User updateUserFromUser(User user)  throws UserException{

        if (user == null || user.getLogin() == null) throw new UserException("Пользователь не найден");
        String login = user.getLogin();

        User userForUpdate = getCurrentUser();

        userForUpdate.setFirstName(user.getFirstName());
        userForUpdate.setLastName(user.getLastName());

        return updateUser(userForUpdate);
    }

    @Override
    public User updatePassword(PasswordReset passwordReset) {

        try {
            User user = getCurrentUser();
            User userForUpdate = findByLogin(user.getLogin());
//            userForUpdate.setPassword(passwordEncoder.encode(passwordReset.getPassword1()));
            userForUpdate.setLastName(user.getLastName());
            return updateUser(userForUpdate);
        }catch (Exception e){
            throw new UserException("Не удалось изменить пароль");
        }
    }

    @Override
    public User findSelf() {
        System.out.println("service Find self");
        return getCurrentUser();
    }


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
        System.out.println("service Get current user");
        var login = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByLogin(login);
    }

    /**
     * Выдача прав администратора текущему пользователю
     * <p>
     * Нужен для демонстрации
     */

    public void getAdmin() {
        var user = getCurrentUser();
         user.setRole(Role.ROLE_ADMIN);
        System.out.println("----------------"+user.toString());
        repository.save(user);
    }

    private User updateUser(User user){
        try {
            repository.save(user);
        } catch (ConstraintViolationException e) {
            throw new UserException("Не валидные данные");
        } catch (DataIntegrityViolationException e) {
            throw new UserException("Не все данные заполнены или дублируются");
        } catch (TransactionSystemException e) {
            throw new UserException("Передаются невалидные данные");
        } catch (RuntimeException e) {
            throw new UserException("Все очень плохо");
        }
        return user;
    }

}
