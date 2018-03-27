package controller;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import model.Doctor;
import model.LoginUser;
import model.Patient;
import model.Visit;

public class PatientDAO {
	
	public void deletePatient(int patientId) {	
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			Patient patient = session.get(Patient.class, patientId);
			session.delete(patient);
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
	
	public void updatePatient (Patient patientToUpdate) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;		
		try {
			tx = session.beginTransaction();
			session.update(patientToUpdate);			
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
	
	public void saveVisit(Visit visit) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer id = (Integer)session.save(visit);
			tx.commit();
			System.out.println("Visit added id: " + id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public void insertPatient(Patient patient) {

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			Integer id = (Integer)session.save(patient);
			tx.commit();
			System.out.println("Patient added id: " + id);
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}
	
	public Patient getPatientById(int patientId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Patient patient = session.get(Patient.class, patientId);
		session.close();
		return patient;
	}
	
	protected List<Patient> searchPatient(String searchType, String searchText) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		// Add on wildcard characters onto the searchText
		//String searchName = "%" + searchText + "%";
		String hql = "FROM Patient WHERE " + searchType + "=" + "'" + searchText + "'";
		Query<Patient> query = session.createQuery(hql);
		//query.setParameter("text", searchText);
	    List<Patient> listOfPatients = query.list();
	    System.out.println("SaercPatientList from the DAO: " + listOfPatients);
		return listOfPatients;
	}

	public List<Patient> getAllPatients (){
		
		List<Patient> listOfPatints = new ArrayList<>();
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Query<Patient> query = session.createQuery("FROM Patient");
		listOfPatints = query.list();
		
		session.close();
		
		return listOfPatints;
	}
}
