package ru.gb.offerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.offerservice.config.securety.Role;
import ru.gb.offerservice.config.securety.User;
import ru.gb.offerservice.config.securety.UserServiceImpl;
import ru.gb.offerservice.dto.CarDto;
import ru.gb.offerservice.exceptions.offerObject.CarExceptions;
import ru.gb.offerservice.mapper.CarMapper;
import ru.gb.offerservice.models.offer_object.car.Car;
import ru.gb.offerservice.models.offer_object.car.CarModel;
import ru.gb.offerservice.repositoties.CarModelsRepository;
import ru.gb.offerservice.repositoties.CarRepository;
import ru.gb.offerservice.repositoties.CarTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CarServices {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final UserServiceImpl userService;

    public Car createCar(CarDto carDto) {
        try {
            Optional<Car> carOptional = carMapper.DtoToCar(carDto);
            if (carOptional.isEmpty()) throw new CarExceptions("Не удалось создать Car из DTO");

            User currentUser = userService.getCurrentUser();
            Car car = carOptional.get();
            if (currentUser == null) throw new CarExceptions("Не удалось определить пользователя");
            car.setUserUID(currentUser.getUid());
            return carRepository.save(car);
        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Не удалось создать модель");
        }
    }

    public Car findCarById(Long id) {
        try {
            Optional<Car> car = carRepository.findById(id);
            if (car.isEmpty()) throw new CarExceptions("Нет такой записи об автомобиля");
            if (isAdmin()) return car.get();
            if (!isCarCurrentUser(car.get())) throw new CarExceptions("Это чужой автомобиль");
            return car.get();

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Что то не так c поиском авто");
        }
    }

    public List<Car> findAllCar() {

        try {
            if (isAdmin()) return carRepository.findAll();
            return carRepository.findAll().stream()
                    .filter(this::isCarCurrentUser)
                    .toList();

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Не удалось найти записи");
        }
    }

    public void deleteCarById(Long id) {
        try {

            if (isAdmin()) {
                carRepository.deleteById(id);
                return;
            }
            carRepository.deleteById(findCarById(id).getId());

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Вы не можете удалить чужой автомобиль");
        }
    }

    public Car updateCarById(Car car) {
        try {

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            System.out.println(car.toString());
            System.out.println("isAdmin = "+isAdmin());

            System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
            Car carUpdate = findCarById(car.getId());

            carUpdate.setName(car.getName());
            carUpdate.setDescription(car.getDescription());
            if (isAdmin()) {
                carUpdate.setCarType(car.getCarType());
                carUpdate.setModel(car.getModel());
            }

            return carRepository.save(carUpdate);
        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Что то не так при обновлении");
        }
    }

    private boolean isCarCurrentUser(Car car) {
        if (userService.getCurrentUser().getUid().equals(car.getUserUID())) return true;
        return false;
    }

    private boolean isAdmin() {
        User currentUser = userService.getCurrentUser();
        return (currentUser.getRole() == Role.ROLE_ADMIN || currentUser.getRole() == Role.ROLE_MODERATOR);
    }
}
