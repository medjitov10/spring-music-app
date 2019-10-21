package com.ga.dao;

import org.hibernate.PropertyValueException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.UserRole;

import java.util.List;

@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public UserRole createRole(UserRole newRole) {
        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            session.save(newRole);
            session.getTransaction().commit();
        } catch(Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }

        System.out.println(newRole.toString());
        return newRole;
    }

    @Override
    public UserRole getRole(String roleName) {
        UserRole userRole = null;

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();

            userRole = (UserRole) session.createQuery("FROM UserRole r WHERE r.name = '" +
                    roleName + "'").uniqueResult();
        } finally {
            session.close();
        }

        return userRole;
    }

    @Override
    public List<UserRole> listRoles() {
        List<UserRole> allRoles = null;

        Session session = sessionFactory.getCurrentSession();

        try {
            session.beginTransaction();
            allRoles = session.createQuery("FROM UserRole").getResultList();
        } finally {
            session.close();
        }
        return allRoles;
    }
}

