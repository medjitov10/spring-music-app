package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserRoleDao;
import com.ga.entity.UserRole;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Autowired
    UserRoleDao userRoleDao;

    @Override
    public UserRole createRole(UserRole newRole) {
        try {
            return userRoleDao.createRole(newRole);
        } catch (Exception e){
            return null;
        }
    }

    @Override
    public UserRole getRole(String roleName) {
        try {
            return userRoleDao.getRole(roleName);
        } catch(Exception e){
            System.out.println("service layer" + e);
            return null;
        }
    }

    @Override
    public List<UserRole> listRoles(){
        return userRoleDao.listRoles();
    }
}
