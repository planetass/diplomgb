package ru.gb.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.gb.webservice.clients.CarModelsAPI;
import ru.gb.webservice.dto.car.CarModelDto;
import ru.gb.webservice.dto.user.TokenDTO;

import java.util.Comparator;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class WebCarModelController {

    private final CarModelsAPI carModelsAPI;

    @GetMapping("/carmodelservice/{token}")
    public String carModelService(@PathVariable String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            List<CarModelDto> carModelsDto = carModelsAPI
                    .carModelList(tokenDTO.toBearer())
                    .stream()
                    .sorted(Comparator.comparing(CarModelDto::getId))
                    .toList();

            model.addAttribute("token", token);
            model.addAttribute("carmodels", carModelsDto);

            return "carmodelsmoderate";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "signin";
        }

    }

    @GetMapping("/carmodeledit/{id}/{token}")
    public String carModelEdit(@PathVariable String token, @PathVariable String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            CarModelDto carModelDto = carModelsAPI.carModel(tokenDTO.toBearer(), id);

            model.addAttribute("token", token);
            model.addAttribute("carmodel", carModelDto);

            return "carmodelupdete";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carModelService(token, model);
        }
    }

    @GetMapping("/carmodeldelete/{id}/{token}")
    public String carModelDelete(@PathVariable(name = "token") String token, @PathVariable(name = "id") String id, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carModelsAPI.carModelDelete(tokenDTO.toBearer(), id);

            return carModelService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carModelService(token, model);
        }


    }

    @PostMapping("/carmodeladd")
    public String carModelAdd(CarModelDto carModelDto, String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carModelsAPI.carModelAdd(tokenDTO.toBearer(), carModelDto);
            return carModelService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carModelService(token, model);
        }

    }

    @PostMapping("/carmodelupdate")
    public String carModelUpdate(CarModelDto carModelDto, String token, Model model) {

        try {
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);

            carModelDto = carModelsAPI.carModelUpdate(tokenDTO.toBearer(), carModelDto);
            return carModelService(token, model);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return carModelService(token, model);
        }
    }
}
