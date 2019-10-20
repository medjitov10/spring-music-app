package java.controller;

import com.ga.entity.UserRole;
import com.ga.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public UserRole addRole(@RequestBody UserRole userRole){
        return userRoleService.createRole(userRole);
    }
}
