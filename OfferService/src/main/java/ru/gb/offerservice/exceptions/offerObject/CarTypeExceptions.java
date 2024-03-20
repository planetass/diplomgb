package ru.gb.offerservice.exceptions.offerObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class CarTypeExceptions extends RuntimeException{
    public CarTypeExceptions(String message) {
        super(message);
    }
}
