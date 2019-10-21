package com.ga.service;

import com.ga.entity.UserRole;

import java.util.List;

public interface UserRoleService {

    public UserRole createRole(UserRole newRole) throws Exception;

    public UserRole getRole(String roleName);

    public List<UserRole> listRoles();

}
