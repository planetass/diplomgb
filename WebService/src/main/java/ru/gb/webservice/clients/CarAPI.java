package ru.gb.webservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gb.webservice.dto.car.CarDto;
import ru.gb.webservice.dto.car.CarModelDto;

import java.util.List;


@FeignClient(name = "cars-service")
@Headers("Authorization: {token}")
public interface CarAPI {

        @GetMapping("")
        List<CarDto> carModelList (@RequestHeader("Authorization") String bearerToken);

        @GetMapping("/{id}")
        CarDto carModel (@RequestHeader("Authorization") String bearerToken,@PathVariable String id);

        @DeleteMapping("/{id}")
        void carModelDelete (@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

        @PutMapping("")
        CarDto carModelUpdate (@RequestHeader("Authorization") String bearerToken, CarDto carDto);

        @PostMapping ("")
        CarDto carModelAdd (@RequestHeader("Authorization") String bearerToken, CarDto carDto);






}
