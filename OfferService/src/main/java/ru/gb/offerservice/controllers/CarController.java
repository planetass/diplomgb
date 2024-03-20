package ru.gb.offerservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.offerservice.config.securety.UserServiceImpl;
import ru.gb.offerservice.dto.CarDto;
import ru.gb.offerservice.exceptions.offerObject.CarExceptions;
import ru.gb.offerservice.models.offer_object.car.Car;
import ru.gb.offerservice.services.CarServices;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor

public class CarController {
    private final CarServices carServices;
    private final UserServiceImpl userService;

    @PostMapping
    ResponseEntity<Car> createCar(@RequestBody CarDto carDto) {
        try {
            return ResponseEntity.ok(carServices.createCar(carDto));
        } catch (CarExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Car> findCarById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(carServices.findCarById(id));
        } catch (CarExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("")
    ResponseEntity<List<Car>> findAllCar() {
        try {
            return ResponseEntity.ok(carServices.findAllCar());
        } catch (CarExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @DeleteMapping("/{id}")
    void deleteCarById(@PathVariable Long id) {
        try {
            carServices.deleteCarById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping()
    ResponseEntity<Car> updateCarById(@RequestBody Car car) {


        try {
            return ResponseEntity.ok(carServices.updateCarById(car));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
