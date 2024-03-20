package ru.gb.offerservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.offerservice.exceptions.offerObject.CarTypeExceptions;
import ru.gb.offerservice.models.offer_object.car.CarType;
import ru.gb.offerservice.services.CarTypeServices;

import java.util.List;

@RestController
@RequestMapping("/cars/car-type")
@RequiredArgsConstructor

public class CarsTypeController {
    private final CarTypeServices carTypeServices;

    @PostMapping
    ResponseEntity<CarType> createCarType(@RequestBody CarType carType) {
        try {
            System.out.println("---"+carType);

            return ResponseEntity.ok(carTypeServices.createCarType(carType));
        } catch (CarTypeExceptions e) {
            System.out.println("Что то не так");
            System.out.println(e.getMessage());
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<CarType> findCarTypeById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(carTypeServices.findCarTypeById(id));
        } catch (CarTypeExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("")
    ResponseEntity<List<CarType>> findAllCarType() {
        try {
            return ResponseEntity.ok(carTypeServices.findAllCarType());
        } catch (CarTypeExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @DeleteMapping("/{id}")
    void deleteCarTypeById(@PathVariable Long id) {
        try {
            carTypeServices.deleteCarTypeById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping()
    ResponseEntity<CarType> updateCarTypeById(@RequestBody CarType carType) {
        try {
            return ResponseEntity.ok(carTypeServices.updateCarTypeById(carType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
