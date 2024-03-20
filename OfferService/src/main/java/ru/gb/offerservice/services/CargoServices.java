package ru.gb.offerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.offerservice.config.securety.Role;
import ru.gb.offerservice.config.securety.User;
import ru.gb.offerservice.config.securety.UserServiceImpl;
import ru.gb.offerservice.dto.CarDto;
import ru.gb.offerservice.dto.CargoDto;
import ru.gb.offerservice.exceptions.offerObject.CarExceptions;
import ru.gb.offerservice.mapper.CargoMapper;
import ru.gb.offerservice.models.offer_object.car.Car;
import ru.gb.offerservice.models.offer_object.cargo.Cargo;
import ru.gb.offerservice.repositoties.CargoRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CargoServices {

    private final CargoRepository cargoRepository;
    private final CargoMapper cargoMapper;
    private final UserServiceImpl userService;

    public Cargo createCargo(CargoDto cargoDto) {
        try {
            Optional<Cargo> cargoOptional = cargoMapper.DtoToCar(cargoDto);
            if (cargoOptional.isEmpty()) throw new CarExceptions("Не удалось создать Cargo из DTO");

            User currentUser = userService.getCurrentUser();
            Cargo cargo = cargoOptional.get();
            if (currentUser == null) throw new CarExceptions("Не удалось определить пользователя");
            cargo.setUserUID(currentUser.getUid());
            return cargoRepository.save(cargo);
        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Не удалось создать Cargo");
        }
    }

    public Cargo findCargoById(Long id) {
        try {
            Optional<Cargo> cargo = cargoRepository.findById(id);
            if (cargo.isEmpty()) throw new CarExceptions("Нет такой записи об Cargo");
            if (isAdmin()) return cargo.get();
            if (!isCarCurrentUser(cargo.get())) throw new CarExceptions("Это чужой груз");
            return cargo.get();

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Что то не так c поиском груза");
        }
    }

    public List<Cargo> findAllCargo() {

        try {
            if (isAdmin()) return cargoRepository.findAll();
            return cargoRepository.findAll().stream()
                    .filter(this::isCarCurrentUser)
                    .toList();

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Не удалось найти записи");
        }
    }

    public void deleteCarModelById(Long id) {
        try {

            if (isAdmin()) {
                cargoRepository.deleteById(id);
                return;
            }
            cargoRepository.deleteById(findCargoById(id).getId());

        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Вы не можете удалить чужой автомобиль");
        }
    }

    public Cargo updateCarModelById(Cargo cargo) {
        try {
            Cargo cargoUpdate = findCargoById(cargo.getId());

            cargoUpdate.setName(cargo.getName());
            cargoUpdate.setDescription(cargo.getDescription());
            if (isAdmin()) {
                cargoUpdate.setWeight(cargo.getWeight());
                cargoUpdate.setLength(cargo.getLength());
                cargoUpdate.setWeight(cargo.getWeight());
                cargoUpdate.setLength(cargo.getLength());
            }

            return cargoRepository.save(cargoUpdate);
        } catch (Exception e) {
            throw new CarExceptions(e.getMessage() + "\n Что то не так при обновлении");
        }
    }

    private boolean isCarCurrentUser(Cargo cargo) {
        if (userService.getCurrentUser().getUid().equals(cargo.getUserUID())) return true;
        return false;
    }

    private boolean isAdmin() {
        User currentUser = userService.getCurrentUser();
        return (currentUser.getRole() == Role.ROLE_ADMIN || currentUser.getRole() == Role.ROLE_MODERATOR);
    }
}
