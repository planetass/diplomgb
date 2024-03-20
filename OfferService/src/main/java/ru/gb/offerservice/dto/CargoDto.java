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

import java.util.UUID;

@Builder
@Data
@Schema(description = "Автомобиль")
public class CargoDto {

    @Schema(description = "ID", example = "12456")
    private Long id;

    @Schema(description = "name", example = "Лыжи")
    @Size(min = 2, max = 30, message = "Комплект горных лыж")
    @NotBlank(message = "Название не может быть пустым")
    protected String name;

    @Schema(description = "description", example = "Лучшие лыжи")
    @Size(min = 2, max = 500, message = "Модель Super Sport Extreme")
    @NotBlank(message = "Описание не может быть пустым")
    protected String description;

    @Schema(description = "UUID", example = "6a05a2a6-0180-4df2-8ae7-3a822f61b5c2")
    @Size(min = 2, max = 500, message = "Название должно быть от 2 до 500 символов")
    protected UUID userUID;
    @Schema(description = "Статус объекта", example = "ACTIVE")
    @NotBlank(message = "Статус объекта пользователя не может быть пустым")
    @Enumerated(value = EnumType.STRING)
    protected ObjectStatus objectStatus;

    @Schema(description = "Тип объекта", example = "CAR")
    @Enumerated(value = EnumType.STRING)
    protected ObjectType objectType;


    @Schema(description = "weight", example = "30")
    @NotBlank(message = "Вес в кг")
    Integer weight;

    @Schema(description = "length", example = "185")
    @NotBlank(message = "Длинна см")
    Integer length;

    @Schema(description = "width", example = "20")
    @NotBlank(message = "Ширина см")
    Integer width;

    @Schema(description = "height", example = "20")
    @NotBlank(message = "Высота см")
    Integer height;

}
