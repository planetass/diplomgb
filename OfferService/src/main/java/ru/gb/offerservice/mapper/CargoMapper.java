package ru.gb.offerservice.mapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.offerservice.dto.CargoDto;
import ru.gb.offerservice.models.offer_object.ObjectType;
import ru.gb.offerservice.models.offer_object.cargo.Cargo;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class CargoMapper {

    public CargoDto CarToDto(Cargo cargo) {
        return CargoDto.builder()
                .id(cargo.getId())
                .name(cargo.getName())
                .description(cargo.getDescription())
                .userUID(cargo.getUserUID())
                .objectType(cargo.getObjectType())
                .objectStatus(cargo.getObjectStatus())
                .width(cargo.getWidth())
                .length(cargo.getLength())
                .height(cargo.getHeight())
                .weight(cargo.getWeight())
                .build();
    }

    public Optional<Cargo> DtoToCar(CargoDto cargoDto) {
        Cargo cargo = new Cargo();

        try {
            cargo.setId(cargoDto.getId());
            cargo.setName(cargoDto.getName());
            cargo.setDescription(cargoDto.getDescription());
            cargo.setUserUID(cargoDto.getUserUID());
            cargo.setObjectStatus(cargoDto.getObjectStatus());
            cargo.setObjectType(ObjectType.CARGO);
            cargo.setWidth(cargoDto.getWidth());
            cargo.setLength(cargoDto.getLength());
            cargo.setHeight(cargoDto.getHeight());
            cargo.setWeight(cargoDto.getWeight());
            return Optional.of(cargo);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }
}

