package com.ga.service;

import com.ga.dao.UserRoleDaoImpl;
import com.ga.entity.UserRole;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleServiceTest {
    @InjectMocks
    UserRoleServiceImpl userRoleService;

    @InjectMocks
    UserRole userRole;

    @Mock
    UserRoleDaoImpl userRoleDao;

    @Before
    public void init() {
        userRole = new UserRole();
        userRole.setName("ROLE_QA");

    }

    @Test
    public void createRole_UserRole_Success(){
        UserRole newRole = new UserRole();
        newRole.setName("ROLE_QA");
        when(userRoleDao.createRole(any())).thenReturn(userRole);

        UserRole createdRole = userRoleService.createRole(newRole);

        assertNotNull(newRole);
        assertEquals(newRole.getName(), createdRole.getName());
    }
    @Test
    public void createRole_UserRole_Error(){
        UserRole newRole = new UserRole();
        newRole.setName(null);
        when(userRoleDao.createRole(any())).thenReturn(null);

        UserRole createdRole = userRoleService.createRole(newRole);

//        assertNotNull(newRole);
        assertEquals(null, createdRole);
    }
    @Test
    public void getRole_UserRole_Success(){
        when(userRoleDao.getRole(anyString())).thenReturn(userRole);

        UserRole foundRole = userRoleService.getRole("ROLE_QA");

        assertNotNull(foundRole);
        assertEquals(userRole.getName(),foundRole.getName());
        assertEquals(userRole.getRoleId(),foundRole.getRoleId());
    }
    @Test
    public void getRole_UserRole_Error(){
        when(userRoleDao.getRole(anyString())).thenReturn(null);

        UserRole foundRole = userRoleService.getRole("ROLE_QA");

        assertNull(foundRole);
        assertEquals(null,foundRole);
    }
    @Test
    public void listRoles_UserRoles_Success(){
       List<UserRole> userList = new ArrayList();
       UserRole role1 = new UserRole();
       role1.setName("ROLE_CHECKER");
       userList.add(role1);
       when(userRoleDao.listRoles()).thenReturn(userList);

       List<?> allRoles = userRoleService.listRoles();

       assertEquals(userList, allRoles);
    }

}
