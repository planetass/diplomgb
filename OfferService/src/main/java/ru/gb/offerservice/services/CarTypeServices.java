package ru.gb.offerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.offerservice.exceptions.offerObject.CarTypeExceptions;
import ru.gb.offerservice.models.offer_object.car.CarType;
import ru.gb.offerservice.repositoties.CarTypeRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarTypeServices {

    private final CarTypeRepository carTypeRepository;

    public CarType createCarType(CarType carType) {

        try {
            return carTypeRepository.save(carType);
        } catch (Exception e) {
            throw new CarTypeExceptions(e.getMessage() + "\nНе удалось создать тип автомобиля");
        }
    }

    public CarType findCarTypeById(Long id) {
        try {
            Optional<CarType> carType = carTypeRepository.findById(id);
            if (carType.isEmpty()) throw new CarTypeExceptions("Нет такой записи о типе автомобиля");
            return carType.get();
        } catch (Exception e) {
            throw new CarTypeExceptions(e.getMessage() + "\n Что то не так пр поиске типа автомобиля");
        }
    }

    public List<CarType> findAllCarType() {

        try {
            return carTypeRepository.findAll();
        } catch (Exception e) {
            throw new CarTypeExceptions(e.getMessage() + "\n Не удалось найти записи");
        }
    }

    public void deleteCarTypeById(Long id) {
        try {
            carTypeRepository.deleteById(id);
        } catch (Exception e) {
            throw new CarTypeExceptions(e.getMessage() + "\n Не удалось удалить запись");
        }
    }

    public CarType updateCarTypeById(CarType carType) {
        try {
            Optional<CarType> carTypeOptional = carTypeRepository.findById(carType.getId());
            if (carTypeOptional.isEmpty()) throw new CarTypeExceptions("Нет такой записи о типе автомобиля");
            CarType carTypeUpdate = carTypeOptional.get();
            carTypeUpdate.setCarType(carType.getCarType());
            return carTypeRepository.save(carTypeUpdate);
        } catch (Exception e) {
            throw new CarTypeExceptions(e.getMessage() + "\n Что то не так при обновлении");
        }
    }
}
