package ru.gb.webservice.dto.car;


import lombok.Builder;
import lombok.Data;
import ru.gb.webservice.dto.ObjectStatus;
import ru.gb.webservice.dto.ObjectType;


import java.util.UUID;

@Builder
@Data
public class CarDto {

    private Long id;
    private String name;
    private String description;
    private UUID userUID;
    private ObjectStatus objectStatus;
    private ObjectType objectType;
    private CarTypeDto carType;
    private CarModelDto model;

    private String idCarModel;
    private String iDcarType;

}
