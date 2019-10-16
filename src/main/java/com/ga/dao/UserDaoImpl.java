package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<User> listUsers() {
		List<User> allUsers = null;
		
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			
			allUsers = session.createQuery("FROM User").getResultList();
			
		} finally {
			session.close();
		}
		return allUsers;
	}

	@Override
	public User signup(User user) {
		Session session = sessionFactory.getCurrentSession();
		
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			
		} finally {
			session.close();
		}
		return user;
	}

	@Override
	public User login(User user) {
		User foundUser = null;
		
		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			// String Concat - likely faster, but less readable
//			foundUser = (User) session.createQuery("FROM User u WHERE u.username='" + 
//					user.getUsername() + "' AND u.password='" + 
//					user.getPassword()+ "'").getSingleResult();
			// StringFormat -- slower, but more readable format
			String queryString = String.format(
					"FROM User u WHERE u.username='%s' AND u.password='%s'", 
					user.getUsername(), 
					user.getPassword()
					);
			foundUser = (User) session.createQuery(queryString).getSingleResult();
		} finally {
			session.close();
		}
		
		return foundUser;
	}

	@Override
	public User updateUser(User user, Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User deleteUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
