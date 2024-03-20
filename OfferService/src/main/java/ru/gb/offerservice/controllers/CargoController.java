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
@RequestMapping("/cargo")
@RequiredArgsConstructor

public class CargoController {
    private final CarServices carServices;
    private final UserServiceImpl userService;

    @PostMapping
    ResponseEntity<Car> createCarType(@RequestBody CarDto carDto) {
        try {
            return ResponseEntity.ok(carServices.createCar(carDto));
        } catch (CarExceptions e) {
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<Car> findCarModelById(@PathVariable Long id) {

        try {
            return ResponseEntity.ok(carServices.findCarById(id));
        } catch (CarExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("")
    ResponseEntity<List<Car>> findAllCarType() {
        try {
            return ResponseEntity.ok(carServices.findAllCar());
        } catch (CarExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @DeleteMapping("/{id}")
    void deleteCarModelById(@PathVariable Long id) {
        try {
            carServices.deleteCarById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping()
    ResponseEntity<Car> updateCarModelById(@RequestBody Car car) {
        try {
            return ResponseEntity.ok(carServices.updateCarById(car));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
