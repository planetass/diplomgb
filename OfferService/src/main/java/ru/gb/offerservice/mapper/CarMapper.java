package ru.gb.offerservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.offerservice.dto.CarDto;
import ru.gb.offerservice.models.offer_object.ObjectType;
import ru.gb.offerservice.models.offer_object.car.Car;
import ru.gb.offerservice.services.CarModelServices;
import ru.gb.offerservice.services.CarTypeServices;


import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CarMapper {

    private final CarModelServices carModelServices;
    private final CarTypeServices carTypeServices;

    public CarDto CarToDto(Car car){
        return CarDto.builder()
                .id(car.getId())
                .name(car.getName())
                .description(car.getDescription())
                .userUID(car.getUserUID())
                .objectType(car.getObjectType())
                .objectStatus(car.getObjectStatus())
                .idCarModel(car.getModel().getId())
                .iDcarType(car.getCarType().getId())
                .build();
    }
    public Optional<Car> DtoToCar(CarDto carDto){
        Car car = new Car();


        try {

            car.setId(carDto.getId());
            car.setName(carDto.getName());
            car.setDescription(carDto.getDescription());
            car.setUserUID(carDto.getUserUID());
            car.setObjectStatus(carDto.getObjectStatus());
            car.setObjectType(ObjectType.CAR);
            car.setCarType(carTypeServices.findCarTypeById(carDto.getIDcarType()));
            car.setModel(carModelServices.findCarModelById(carDto.getIdCarModel()));

            return Optional.of(car);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Optional.empty();
        }

    }


}

