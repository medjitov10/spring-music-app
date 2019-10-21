package com.ga.dao;

import com.ga.entity.User;
import com.ga.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserRoleDaoTest {
    static List<UserRole> rolesList;

    @InjectMocks
    private UserRole userRole;

    @InjectMocks
    private UserRoleDaoImpl userRoleDao;

    @Mock
    private SessionFactory sessionFactory;

    @Mock
    Session session;

    @Mock
    Transaction transaction;

    @Mock
    Query<UserRole> query;

    @Before
    public void init() {
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
    }

    @Before
    public void initializeDummyUserRole() {
        userRole.setRoleId(1);
        userRole.setName("ROLE_CHECKER");
    }

    @Test
    public void createRole_UserRole_Success() {
        UserRole savedUserRole = userRoleDao.createRole(userRole);
        assertEquals(userRole, savedUserRole);
    }
    @Test
    public void getRole_UserRole_Success() {
        when(session.createQuery(anyString())).thenReturn(query);
        when(query.uniqueResult()).thenReturn(userRole);
        UserRole foundUserRole = userRoleDao.getRole(userRole.getName());
        assertEquals(userRole, foundUserRole);
    }
    @Test
    public void listRoles_UserRole_Success() {
        List<UserRole> rolesList = new ArrayList<>();
        rolesList.add(userRole);

        when(session.createQuery(anyString())).thenReturn(query);
        when(query.getResultList()).thenReturn(rolesList);
        List<UserRole> allRolesFound = userRoleDao.listRoles();
        System.out.println(rolesList);
        System.out.println(allRolesFound);
        assertEquals(rolesList, allRolesFound);

    }

}
