package ru.gb.webservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gb.webservice.dto.car.CarModelDto;

import java.util.List;


@FeignClient(name = "cars-models-service")
@Headers("Authorization: {token}")
public interface CarModelsAPI {

        @GetMapping("/car-model")
        List<CarModelDto> carModelList (@RequestHeader("Authorization") String bearerToken);

        @GetMapping("/car-model/{id}")
        CarModelDto carModel (@RequestHeader("Authorization") String bearerToken,@PathVariable String id);

        @DeleteMapping("/car-model/{id}")
        void carModelDelete (@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

        @PutMapping("/car-model")
        CarModelDto carModelUpdate (@RequestHeader("Authorization") String bearerToken, CarModelDto carModelDto);

        @PostMapping ("/car-model")
        CarModelDto carModelAdd (@RequestHeader("Authorization") String bearerToken, CarModelDto carModelDto);






}
