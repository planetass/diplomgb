package ru.gb.webservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.webservice.clients.AuthAPI;
import ru.gb.webservice.clients.UserAPI;
import ru.gb.webservice.config.RolePages;
import ru.gb.webservice.dto.user.SignInRequest;
import ru.gb.webservice.dto.user.SignUpRequest;
import ru.gb.webservice.dto.user.TokenDTO;
import ru.gb.webservice.dto.user.UserDTO;


@Controller
@RequiredArgsConstructor
public class WebUserController {
    private final AuthAPI authAPI;
    private final UserAPI userAPI;
    private final RolePages rolePages;


    @PostMapping("/signin")
    public String signIn (SignInRequest sign, Model model){

        try{
            TokenDTO token = authAPI.signIn(sign);
            UserDTO user = userAPI.self(token.toBearer());
            model.addAttribute("token",token.getToken());
            return rolePages.getStartPage(user.getRole());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "signin";
        }
    }

    @PostMapping("/signup")
    public String signIn (SignUpRequest signUP, Model model){

        try{
            TokenDTO token = authAPI.signUp(signUP);
            UserDTO user = userAPI.self(token.toBearer());
            model.addAttribute("token",token.getToken());
            return rolePages.getStartPage(user.getRole());
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "signup";
        }
    }

    @GetMapping("/getadmin/{token}")
    public String getAdmin (@PathVariable String token){

        try{
            TokenDTO tokenDTO = new TokenDTO();
            tokenDTO.setToken(token);
            userAPI.getAdmin(tokenDTO.toBearer());

            return "signin";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "signin";
        }
    }

}
