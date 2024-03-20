package ru.vss.userservice.services;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import ru.vss.userservice.model.User;
import ru.vss.userservice.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImplOld {

//
//    /**
//     * Обновляет пользователя в БД.
//     * Обновляет данные пользователя в БД.
//     * Возможны для обновления:
//     * - Роль
//     * - Адрес электронной почты
//     * - Имя
//     * - Фамилия
//     *
//     * @param user Обновляемый пользователь сновыми данными
//     * @return - Экземпляр обновленного объекта в случае успеха, или null если данные обновить не удалось.
//     */
//    @Override
//    public User updateUser(User user) {
//
//        if (user == null || user.getId() == null) return null;
//        Long id = user.getId();
//
//        Optional<User> optionalUser = repository.findById(id);
//
//        if (optionalUser.isPresent()) {
//            User userForUpdate = optionalUser.get();
//
//            userForUpdate.setRole(user.getRole());
//            userForUpdate.setEmail(user.getEmail());
//            userForUpdate.setFirstName(user.getFirstName());
//            userForUpdate.setLastName(user.getLastName());
//
//            //TODO Перделать sout на коды ошибок.
//            try {
//                repository.save(userForUpdate);
//            } catch (ConstraintViolationException e) {
//                System.out.println("Не валидные данные");
//                return null;
//            } catch (DataIntegrityViolationException e) {
//                System.out.println("Не все данные заполнены или дублируются");
//                return null;
//            } catch (TransactionSystemException e) {
//                System.out.println("Передаются невалидные данные");
//                return null;
//            } catch (RuntimeException e) {
//                System.out.println("Все очень плохо");
//                System.out.println(e.getClass());
//                return null;
//            }
//            return userForUpdate;
//
//        } else {
//            return null;
//        }
//    }
//
//    /**
//     * Поиск пользователя по id
//     *
//     * @param id - Запрашиваемого пользователя
//     * @return - Optional с User если они найден, иначе пустой объект
//     */
//    @Override
//    public Optional<User> findUser(Long id) {
//        if (id == null) {
//            return Optional.empty();
//        }
//        return repository.findById(id);
//    }
//
//    /**
//     * Удаляет пользователя по id
//     * @param id - id удаляемого пользователя
//     */
//    @Override
//    public void deleteUserById(Long id) {
//        repository.deleteById(id);
//    }
}