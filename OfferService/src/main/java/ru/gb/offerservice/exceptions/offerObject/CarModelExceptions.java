package ru.gb.offerservice.exceptions.offerObject;

import lombok.Data;

@Data
public class CarModelExceptions extends RuntimeException{
    public CarModelExceptions(String message) {
        super(message);
    }
}
