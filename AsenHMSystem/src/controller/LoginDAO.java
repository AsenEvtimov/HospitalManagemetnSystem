package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.LoginUser;

public class LoginDAO {
	
	public void updateUser (LoginUser userToUpdate) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			session.update(userToUpdate);			
			tx.commit();
		} catch (HibernateException e) {
			if (tx !=null) {
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
		}
	}
	
	public LoginUser getUserById(int userId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		LoginUser user = session.get(LoginUser.class, userId);
		session.close();
		return user;
	}
	
	public void deleteUser(int userId) {	
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			LoginUser user = session.get(LoginUser.class, userId);
			session.delete(user);
			tx.commit();
		} catch (HibernateException e) {
			if (tx !=null) {
				tx.rollback();
				e.printStackTrace();
			}
		}
		finally {
			session.close();
			}
		}

	public void insertUser(LoginUser user) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			Integer id = (Integer) session.save(user);
			System.out.println("User added, id : " + id);
			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	
	public boolean validateUser(String username, String password) {
		Session session = HibernateUtil.getSessionFactory().openSession();

		Query<LoginUser> query = session
				.createQuery("FROM LoginUser WHERE " + "username = :username AND password = :password");
		query.setParameter("username", username);
		query.setParameter("password", password);
		List<LoginUser> result = (List<LoginUser>) query.list();
		session.close();
		return result.isEmpty() ? false : true;
	}
	
	public LoginUser getCurrentUser (String username) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<LoginUser> query = session
				.createQuery("FROM LoginUser WHERE username = :username");
		query.setParameter("username", username);
		//query.setParameter("password", password);
		LoginUser result = query.uniqueResult();
		System.out.println("CurrentLoginUser: "+ result);
		return result;
	}
	
	
	public List<LoginUser> getAllUsers() {

		List<LoginUser> listOfUsers = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<LoginUser> query = session.createQuery("FROM LoginUser");
			listOfUsers = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfUsers;
	}
}
