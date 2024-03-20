package ru.gb.webservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import ru.gb.webservice.dto.car.CarTypeDto;

import java.util.List;


@FeignClient(name = "cars-types-service")
@Headers("Authorization: {token}")
public interface CarTypesAPI {

        @GetMapping("/car-type")
        List<CarTypeDto> carTypeList (@RequestHeader("Authorization") String bearerToken);

        @GetMapping("/car-type/{id}")
        CarTypeDto carType (@RequestHeader("Authorization") String bearerToken,@PathVariable String id);

        @DeleteMapping("/car-type/{id}")
        void carTypeDelete (@RequestHeader("Authorization") String bearerToken, @PathVariable String id);

        @PutMapping("/car-type")
        CarTypeDto carTypeUpdate (@RequestHeader("Authorization") String bearerToken, CarTypeDto carTypeDto);

        @PostMapping ("/car-type")
        CarTypeDto carTypeAdd (@RequestHeader("Authorization") String bearerToken, CarTypeDto carTypeDto);






}
