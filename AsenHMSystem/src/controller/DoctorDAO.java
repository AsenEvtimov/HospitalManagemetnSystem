package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Department;
import model.Doctor;

public class DoctorDAO {
	
	public Department getDepartmetnById(int deptId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Department department = session.get(Department.class, deptId);
		session.close();
		return department;
	}
	
	protected List<Doctor> searchDoctor(String searchType, String searchText) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Add on wildcard characters onto the searchText
		//String searchName = "%" + searchText + "%";
		String hql = "FROM Doctor WHERE " + searchType + "=" + "'" + searchText + "'";
		Query<Doctor> query = session.createQuery(hql);
		//query.setParameter("text", searchText);
	    List<Doctor> listOfDoctors = query.list();
	    System.out.println("SaercDoctorList from the DAO: " + listOfDoctors);
		return listOfDoctors;
	}
	
	public List<Doctor> getAllDoctors() {

		List<Doctor> listOfDoctors = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<Doctor> query = session.createQuery("FROM Doctor");
			listOfDoctors = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfDoctors;
	}
	
	public Doctor getDoctorById(int doctorId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Doctor doctor = session.get(Doctor.class, doctorId);
		session.close();
		return doctor;
	}
	
	public void deleteDoctor(int doctorId) {	
	Session session = HibernateUtil.getSessionFactory().openSession();

	Transaction tx = null;		
	try {
		tx = session.beginTransaction();
		Doctor doctor = session.get(Doctor.class, doctorId);
		session.delete(doctor);
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
	
	public List<Doctor>getDoctorByDepartment(int deptId) {
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		List <Doctor> doctorsByDepList = new ArrayList<>();

		try {
			Query<Doctor> query = session.createQuery("FROM Doctor where deptId = :deptId");
			query.setParameter("deptId", deptId);
			doctorsByDepList = query.list();
			System.out.println("In try block " + doctorsByDepList );
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return doctorsByDepList;
	}
	
	public void updateDoctor(Doctor doctorToUpdate) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();
			session.update(doctorToUpdate);
			tx.commit();
		}catch(HibernateException e) {
			if (tx!= null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			session.close();
		}
	}
	
	public void insertDoctor(Doctor doctor) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer id = (Integer)session.save(doctor);
			tx.commit();
			System.out.println("Doctor added id: " + id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
}
