package ru.gb.offerservice.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.offerservice.exceptions.offerObject.CarModelExceptions;
import ru.gb.offerservice.models.offer_object.car.CarModel;
import ru.gb.offerservice.repositoties.CarModelsRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarModelServices {

    private final CarModelsRepository carModelRepository;

    public CarModel createCarModel(CarModel carModel) {
        try {
            return carModelRepository.save(carModel);
        } catch (Exception e) {
            throw new CarModelExceptions(e.getMessage() + "\nНе удалось создать модель");
        }
    }

    public CarModel findCarModelById(Long id) {
        try {
            Optional<CarModel> carModel = carModelRepository.findById(id);
            if (carModel.isEmpty()) throw new CarModelExceptions("Нет такой записи о моделе автомобиля");
            return carModel.get();
        } catch (Exception e) {
            throw new CarModelExceptions(e.getMessage() + "\n Что то не так пр поиске модели");
        }
    }

    public List<CarModel> findAllCarModel() {

        try {
            return carModelRepository.findAll();
        } catch (Exception e) {
            throw new CarModelExceptions(e.getMessage() + "\n Не удалось найти записи");
        }
    }

    public void deleteCarModelById(Long id) {
        try {
            carModelRepository.deleteById(id);
        } catch (Exception e) {
            throw new CarModelExceptions(e.getMessage() + "\n Не удалось удалить запись");
        }
    }

    public CarModel updateCarModelById(CarModel carModel) {
        try {
            Optional<CarModel> carModelOptional = carModelRepository.findById(carModel.getId());
            if (carModelOptional.isEmpty()) throw new CarModelExceptions("Нет такой записи о модели автомобиля");
            CarModel carModelUpdate = carModelOptional.get();
            carModelUpdate.setCarModel(carModel.getCarModel());
            return carModelRepository.save(carModelUpdate);
        } catch (Exception e) {
            throw new CarModelExceptions(e.getMessage() + "\n Что то не так при обновлении");
        }
    }
}
