package controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import model.Bed;
import model.Department;
import model.Doctor;
import model.Room;
import model.Ward;

public class BedDAO {
	
	public Bed getBedById(int bedId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Bed bed = session.get(Bed.class, bedId);
		session.close();
		return bed;
	}
	
	
	public Bed getFreeBedByDept (int deptId, String roomType) {
		
		Bed bed = new Bed();
		Session session = HibernateUtil.getSessionFactory().openSession();
		Query<Bed> query = session.createQuery
			("FROM Bed b where b.room.ward.department.deptId ="+"'"+ deptId +"'"+"and vacant = '1' and b.room.roomCost.roomType ="+"'"+roomType+"'");
		bed = query.list().get(0);
		System.out.println("bed to assign: " + bed);
		return bed;
	}

	public Ward getWardById(int wardId) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Ward ward = session.get(Ward.class, wardId);
		session.close();
		return ward;
	}
	
	public List<Ward> getAllWards() {

		List<Ward> listOfWards = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<Ward> query = session.createQuery("FROM Ward");
			listOfWards = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfWards;
	}
	
	public List<Room> getRoomsByDepartment(String deptId) {

		List<Room> listOfRoom = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<Room> query = session.createQuery("FROM Room r where r.ward.department.deptId ="+"'"+ deptId +"'");;
			//query.setParameter("deptId" , deptId);
			listOfRoom = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfRoom;
	}
	
	public List<Room> getRooms(String wardId) {

		List<Room> listOfRoom = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<Room> query = session.createQuery("FROM Room where wardId = :wardId");
			query.setParameter("wardId" , wardId);
			listOfRoom = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfRoom;
	}

	public List<Bed> getAllBeds() {

		List<Bed> listOfBeds = new ArrayList<>();

		Session session = HibernateUtil.getSessionFactory().openSession();

		try {
			Query<Bed> query = session.createQuery("FROM Bed");
			listOfBeds = query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
		}
		return listOfBeds;
	}
	
	/*List<Object[]> personsAndPhones = session.createQuery(
		    "select pr.name, ph.number " +
		    "from Person pr " +
		    "left join pr.phones ph with ph.type = :phoneType " )
		.setParameter( "phoneType", PhoneType.LAND_LINE )
		.list();*/
	
	/*select b.bedId, b.roomId, r.wardId, b.vacant, r.roomTypeId from Bed B
	join Room R on
	B.roomId = R.roomId 
	where r.wardId = 1*/

	public List<Bed> getBedsInWarad(String wardId) {
		
		List<Bed> listOfBedsInWard = new ArrayList<>();
		Session session = HibernateUtil.getSessionFactory().openSession();
		
		Query<Bed> query = session.createQuery("FROM Bed b where b.room.ward.wardId ="+"'"+ wardId +"'");
		//query.setParameter("wardId" , wardId);
		listOfBedsInWard = query.list();
		
		session.close();
		return listOfBedsInWard;

	}

}
