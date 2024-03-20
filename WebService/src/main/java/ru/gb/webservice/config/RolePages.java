package ru.gb.webservice.config;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.gb.webservice.enums.Role;
import ru.gb.webservice.enums.RolePage;

import java.util.HashMap;

@Component
@RequiredArgsConstructor
@Data
public class RolePages {


    private final HashMap<Role, RolePage> rolePages;

    public RolePages() {
        rolePages = new HashMap<>();
        this.rolePages.put(Role.ROLE_ADMIN, RolePage.ADMIN);
        this.rolePages.put(Role.ROLE_MODERATOR, RolePage.MODERATOR);
        this.rolePages.put(Role.ROLE_USER, RolePage.USER);
    }

    public String getStartPage(Role role) {
        return rolePages.get(role).getStartPage();
    }
}
