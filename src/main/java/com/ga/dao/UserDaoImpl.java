package com.ga.dao;

import java.util.List;

import com.ga.entity.UserRole;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;
import com.ga.entity.UserRole;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	UserRoleDao userRoleDao;

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
		String roleName = user.getUserRole().getName();
		UserRole userRole = userRoleDao.getRole(roleName);
		user.setUserRole(userRole);

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
			// TODO: catch statement return existingUser placeholder parse exception
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
			foundUser = (User) session.createQuery("FROM User u WHERE u.username='" +
					user.getUsername() + "'").getSingleResult();
//					user.getUsername() + "' AND u.password='" +
//					user.getPassword()+ "'").getSingleResult();
					

			// StringFormat -- slower, but more readable format
//			String queryString = String.format(
//					"FROM User u WHERE u.username='%s'",
//					user.getUsername()
//					);
//			foundUser = (User) session.createQuery(queryString).getSingleResult();
		} finally {
			session.close();
		}

		return foundUser;
	}

	@Override
	public User updateUser(User user, Long userId) {
		User savedUser = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			savedUser = session.get(User.class, userId);
			savedUser.setPassword(user.getPassword());

			session.update(savedUser);

			session.getTransaction().commit();
		} finally {
			session.close();
		}

		return savedUser;
	}

	@Override
	public User deleteUser(Long userId) {
		User savedUser = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			savedUser = session.get(User.class, userId);
			session.delete(savedUser);
			session.getTransaction().commit();
		} finally {
			session.close();
		}
		return savedUser;
	}

	@Override
	public User getUserByUsername(String username) {
		User user = null;

		Session session = sessionFactory.getCurrentSession();
		String queryString = String.format(
				"From User u WHERE u.username = '%s'", username
				);

		try {
			session.beginTransaction();
//			user = (User)session.createQuery(queryString).uniqueResult();
			user = (User)session.createQuery("From User u WHERE u.username = '" + username+ "'").uniqueResult();

		} finally {
			session.close();
		}
		return user;
	}

	public User addSong(String username, int songId) {
		// TODO: Implement PUT Song on User
		return null;
	}

}
