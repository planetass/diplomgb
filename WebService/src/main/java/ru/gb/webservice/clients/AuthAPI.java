package ru.gb.webservice.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import ru.gb.webservice.dto.user.SignInRequest;
import ru.gb.webservice.dto.user.SignUpRequest;
import ru.gb.webservice.dto.user.TokenDTO;


@FeignClient(name = "auth-service")
public interface AuthAPI {

        @PostMapping("/sign-in")
        TokenDTO signIn (SignInRequest signInRequest);

        @PostMapping("/sign-up")
        TokenDTO signUp (SignUpRequest signInRequest);



}
