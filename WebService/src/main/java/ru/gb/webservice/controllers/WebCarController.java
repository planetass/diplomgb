package ru.gb.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.webservice.clients.CarAPI;
import ru.gb.webservice.clients.CarModelsAPI;
import ru.gb.webservice.clients.CarTypesAPI;
import ru.gb.webservice.dto.ObjectStatus;
import ru.gb.webservice.dto.ObjectType;
import ru.gb.webservice.dto.car.CarDto;
import ru.gb.webservice.dto.car.CarModelDto;
import ru.gb.webservice.dto.car.CarTypeDto;
import ru.gb.webservice.dto.user.TokenDTO;
import ru.gb.webservice.servises.CarService;

import java.util.Comparator;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class WebCarController {

    private final CarService service;
    private final CarModelsAPI carModelsAPI;
    private final CarTypesAPI carTypesAPI;
    private final CarAPI carAPI;


    @GetMapping("/carservice/{token}")
    public String carService(@PathVariable String token, Model model) {

        try {
            service.carList(token);

            TokenDTO tokenDTO =new TokenDTO();
            tokenDTO.setToken(token);



            model.addAttribute("token", token);
            model.addAttribute("cars", service.carList(token));
            model.addAttribute("carmodels", carModelsAPI.carModelList(tokenDTO.toBearer()));
            model.addAttribute("cartypes", carTypesAPI.carTypeList(tokenDTO.toBearer()));


            return "carmoderate";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "signin";
        }

    }

    @GetMapping("/caredit/{id}/{token}")
    public String carEdit(@PathVariable String token, @PathVariable String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            CarDto carDto = carAPI.carModel(tokenDTO.toBearer(), id);

            model.addAttribute("token", token);
            model.addAttribute("car", carDto);
            model.addAttribute("carmodels", carModelsAPI.carModelList(tokenDTO.toBearer()));
            model.addAttribute("cartypes", carTypesAPI.carTypeList(tokenDTO.toBearer()));


            return "carupdete";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carService(token, model);
        }
    }

    @GetMapping("/cardelete/{id}/{token}")
    public String carDelete(@PathVariable(name = "token") String token, @PathVariable(name = "id") String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carAPI.carModelDelete(tokenDTO.toBearer(), id);
            return carService(token, model);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carService(token, model);
        }


    }

    @PostMapping("/caradd")
    public String carAdd(CarDto carDto, String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carDto.setObjectStatus(ObjectStatus.BUSY);
            carDto.setObjectType(ObjectType.CAR);

            carAPI.carModelAdd(tokenDTO.toBearer(), carDto);
            return carService(token, model);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carService(token, model);
        }
    }

    @PostMapping("/carupdate")
    public String carUpdate(CarDto carDto, String token, Model model) {



        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);


            carDto.setCarType(carTypesAPI.carType(tokenDTO.toBearer(),carDto.getIDcarType()));
            carDto.setModel(carModelsAPI.carModel(tokenDTO.toBearer(),carDto.getIDcarType()));




            carDto = carAPI.carModelUpdate(tokenDTO.toBearer(), carDto);
            return carService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carService(token, model);
        }
    }
}
