package ru.gb.offerservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.gb.offerservice.config.securety.User;
import ru.gb.offerservice.config.securety.UserDTO;

@FeignClient(name = "user-service")
@Headers("Authorization: {token}")
public interface UserAPI {

        @GetMapping("/self")
        UserDTO self (@RequestHeader("Authorization") String bearerToken);

}
