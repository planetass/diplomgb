package ru.gb.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ru.gb.webservice.clients.CarTypesAPI;
import ru.gb.webservice.dto.car.CarTypeDto;
import ru.gb.webservice.dto.user.TokenDTO;

import java.util.Comparator;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class WebCarTypeController {

    private final CarTypesAPI carTypesAPI;

    @GetMapping("/cartypeservice/{token}")
    public String carTypeService(@PathVariable String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            List<CarTypeDto> carModelsDto = carTypesAPI
                    .carTypeList(tokenDTO.toBearer())
                    .stream()
                    .sorted(Comparator.comparing(CarTypeDto::getId))
                    .toList();

            model.addAttribute("token", token);
            model.addAttribute("cartypes", carModelsDto);

            return "cartypesmoderate";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "signin";
        }

    }

    @GetMapping("/cartypeedit/{id}/{token}")
    public String carTypeEdit(@PathVariable String token, @PathVariable String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            CarTypeDto carModelDto = carTypesAPI.carType(tokenDTO.toBearer(), id);

            model.addAttribute("token", token);
            model.addAttribute("cartype", carModelDto);

            return "cartypeupdete";//TODO поменять
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carTypeService(token, model);//TODO поменять
        }
    }

    @GetMapping("/cartypedelete/{id}/{token}")
    public String carTypeDelete(@PathVariable(name = "token") String token, @PathVariable(name = "id") String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carTypesAPI.carTypeDelete(tokenDTO.toBearer(), id);

            return carTypeService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carTypeService(token, model);
        }


    }

    @PostMapping("/cartypeadd")
    public String carTypeAdd(CarTypeDto carTypeDto, String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            System.out.println("///".repeat(50));
            System.out.println(carTypeDto);
            System.out.println("///".repeat(50));

            carTypesAPI.carTypeAdd(tokenDTO.toBearer(), carTypeDto);
            return carTypeService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carTypeService(token, model);
        }

    }

    @PostMapping("/cartypeupdate")
    public String carTypeUpdate(CarTypeDto carModelDto, String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carModelDto = carTypesAPI.carTypeUpdate(tokenDTO.toBearer(), carModelDto);
            return carTypeService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carTypeService(token, model);
        }
    }
}
