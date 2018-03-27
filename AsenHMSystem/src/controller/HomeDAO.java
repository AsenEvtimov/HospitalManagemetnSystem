package controller;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Department;
import model.Doctor;
import model.Employee;
import model.Visit;

public class HomeDAO {

	public void updateVisit (Visit visit) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		
		try {
			tx = session.beginTransaction();
			session.update(visit);
			tx.commit();
		} catch (HibernateException e) {
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public Visit getVisitById(int visitId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Visit visit = session.get(Visit.class, visitId);
		session.close();
		return visit;
	}
	
	public Employee getEmployeeIdForUser (String username) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM Employee WHERE firstName"  + "=" + "'" + username + "'";
		Query<Employee> query = session.createQuery(hql);
		Employee employee = query.getSingleResult();
		//int doctorId = doctor.getDoctorId();
		return employee;
	}
	
	public List<Visit> getAppointments (int empId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		String hql = "FROM Visit WHERE doctorId"  + "=" + "'" + empId + "'";
		Query<Visit> query = session.createQuery(hql);
		List<Visit> listOfVisit = query.list();
	
		return listOfVisit;
	}
	
	public void deleteVisit(int visitId) {		

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			Visit visit = session.get(Visit.class, visitId);
			session.delete(visit);
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
}
