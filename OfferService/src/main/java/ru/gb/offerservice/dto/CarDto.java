package ru.gb.offerservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import ru.gb.offerservice.models.offer_object.ObjectStatus;
import ru.gb.offerservice.models.offer_object.ObjectType;
import ru.gb.offerservice.models.offer_object.car.CarType;

import java.rmi.server.UID;
import java.util.UUID;

@Builder
@Data
@Schema(description = "Автомобиль")
public class CarDto {

    @Schema(description = "ID", example = "12456")
    private Long id;

    @Schema(description = "name", example = "Моя машина")
    @Size(min = 2, max = 30, message = "Название должно быть от 2 до 5000 символов")
    @NotBlank(message = "Название не может быть пустым")
    protected String name;

    @Schema(description = "description", example = "Самая быстрая машина")
    @Size(min = 2, max = 500, message = "Название должно быть от 2 до 500 символов")
    @NotBlank(message = "Описание не может быть пустым")
    protected String description;

    @Schema(description = "UUID", example = "6a05a2a6-0180-4df2-8ae7-3a822f61b5c2")
    @Size(min = 2, max = 500, message = "Название должно быть от 2 до 500 символов")
    protected UUID userUID;

    @Schema(description = "Тип объекта", example = "CAR")
    @Enumerated(value = EnumType.STRING)
    protected ObjectType objectType;

    @Schema(description = "Статус объекта", example = "ACTIVE")
    @NotBlank(message = "Статус объекта пользователя не может быть пустым")
    @Enumerated(value = EnumType.STRING)
    protected ObjectStatus objectStatus;

    @Schema(description = "idCarModel", example = "12456")
    @NotBlank(message = "Модель автомобиля не может быть пустой")
    private Long idCarModel;

    @Schema(description = "iDcarType", example = "12456")
    @NotBlank(message = "Тип автомобиля не может быть пустым")
    private Long iDcarType;
}
