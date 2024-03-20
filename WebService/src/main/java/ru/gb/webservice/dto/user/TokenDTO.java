package ru.gb.webservice.dto.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class TokenDTO {
    private static final String BEARER_PREFIX = "Bearer ";
    String token;

    public String toBearer(){
        return BEARER_PREFIX + token;
    }

}
