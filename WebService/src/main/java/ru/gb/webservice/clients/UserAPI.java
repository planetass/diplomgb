package ru.gb.webservice.clients;

import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import ru.gb.webservice.dto.user.UserDTO;


@FeignClient(name = "user-service")
@Headers("Authorization: {token}")
public interface UserAPI {

        @GetMapping("/self")
        UserDTO self (@RequestHeader("Authorization") String bearerToken);

        @GetMapping("/get-admin")
        void getAdmin (@RequestHeader("Authorization") String bearerToken);


}
