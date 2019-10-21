package com.ga.dao;

import com.ga.entity.UserRole;

import java.util.List;

public interface UserRoleDao {

    public UserRole createRole(UserRole newRole);

    public UserRole getRole(String roleName);

    public List<UserRole> listRoles();

}
