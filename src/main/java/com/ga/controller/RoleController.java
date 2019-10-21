package com.ga.controller;

import com.ga.entity.UserRole;
import com.ga.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    UserRoleService userRoleService;

    @PostMapping
    public UserRole addRole(@RequestBody UserRole userRole) {
        try {
            return userRoleService.createRole(userRole);
        } catch (Exception e) {
            System.err.println(e);
            return null; // TODO: replace with response entity?
        }
    }
    @GetMapping("/list")
    public List<UserRole> listRoles(){
       return userRoleService.listRoles();
    }

}
