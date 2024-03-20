package ru.gb.webservice.servises;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.gb.webservice.clients.CarAPI;
import ru.gb.webservice.dto.car.CarDto;
import ru.gb.webservice.dto.car.CarModelDto;
import ru.gb.webservice.dto.user.TokenDTO;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarAPI carAPI;
    public List<CarDto> carList(String token){
        TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);

         return carAPI
                .carModelList(tokenDTO.toBearer())
                .stream()
                .sorted(Comparator.comparing(CarDto::getId))
                .toList();
    }





}
