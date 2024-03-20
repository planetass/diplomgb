package ru.gb.offerservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.gb.offerservice.config.securety.UserServiceImpl;
import ru.gb.offerservice.exceptions.offerObject.CarTypeExceptions;
import ru.gb.offerservice.models.offer_object.car.CarModel;

import ru.gb.offerservice.services.CarModelServices;

import java.util.List;

@RestController
@RequestMapping("/cars/car-model")
@RequiredArgsConstructor

public class CarsModelController {
    private final CarModelServices carModelServices;
    private final UserServiceImpl userService;

    @PostMapping
    ResponseEntity<CarModel> createCarType(@RequestBody CarModel carModel) {
        try {
            return ResponseEntity.ok(carModelServices.createCarModel(carModel));
        } catch (CarTypeExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("/{id}")
    ResponseEntity<CarModel> findCarModelById(@PathVariable Long id) {

        try {
            System.out.println(userService.getCurrentUser());
            return ResponseEntity.ok(carModelServices.findCarModelById(id));
        } catch (CarTypeExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @GetMapping("")
    ResponseEntity<List<CarModel>> findAllCarType() {
        try {
            return ResponseEntity.ok(carModelServices.findAllCarModel());
        } catch (CarTypeExceptions e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatusCode.valueOf(500)).build();
        }
    }

    @DeleteMapping("/{id}")
    void deleteCarModelById(@PathVariable Long id) {

        System.out.println("Deleting model - "+id);
        try {
            carModelServices.deleteCarModelById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @PutMapping()
    ResponseEntity<CarModel> updateCarModelById(@RequestBody CarModel carType) {
        try {
            return ResponseEntity.ok(carModelServices.updateCarModelById(carType));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
