package ru.gb.offerservice.exceptions.offerObject;

import lombok.Data;

@Data
public class CarExceptions extends RuntimeException{
    public CarExceptions(String message) {
        super(message);
    }
}
