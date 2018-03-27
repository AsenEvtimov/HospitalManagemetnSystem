package controller;

import java.math.BigDecimal;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import model.Bed;
import model.Billing;
import model.Department;
import model.Doctor;
import model.LoginUser;
import model.Patient;
import model.PatientNotes;
import model.Prescription;
import model.Room;
import model.RoomCost;
import model.Ward;

public class InsertIntoDb {

	public static void main(String[] args) {

		//insertDepartment();
		//insertRoomsAndBeds();
		
		LoginUser superUser = new LoginUser("super", "super", "superUser", null);
		
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			session.save(superUser);
			System.out.println("Record saved");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public static void insertDepartment() {

		RoomCost roomCostSemiPrivate = new RoomCost("Semi-private", new BigDecimal("500"));
		RoomCost roomCostPrivate = new RoomCost("Private", new BigDecimal("500"));
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//Integer id = (Integer)session.save(depratment);
			//Integer id = (Integer)session.save(doctor);

			session.save(roomCostSemiPrivate);
			session.save(roomCostPrivate);

			System.out.println("Record saved");
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		}
		finally {
			session.close();
		}
	}

	public static void insertRoomsAndBeds() {

		Department depratment1 = new Department("Cardiology");
		Department depratment2 = new Department("Physiotheraphy");
		Department depratment3 = new Department("Rheumatology");
		Department depratment4 = new Department("Opthhalmology");

		RoomCost roomCostPublic = new RoomCost("Public", new BigDecimal("500"));
		RoomCost roomCostSemiPrivate = new RoomCost("Semi-private", new BigDecimal("700"));
		RoomCost roomCostPrivate = new RoomCost("Private", new BigDecimal("900"));
		
		//wards in Cardiology Department
		Ward wardBelle = new Ward("Belle", depratment1);
		Ward wardSarabi = new Ward("Sarabi", depratment1);
		//wards in Physiotheraphy Department
		Ward wardPocahontas = new Ward("Pocahontas", depratment2);
		Ward wardAladdin = new Ward("Aladdin", depratment2);
		//wards in Rheumatology Department
		Ward wardDemetrius = new Ward("Demetrius", depratment3);
		Ward wardRapunzel = new Ward("Rapunzel", depratment3);
		//wards in Opthhalmology Department
		Ward wardHercules = new Ward("Hercules", depratment4);
		Ward wardAurora = new Ward("Aurora", depratment4);

		//Rooms in Belle Ward
		Room roomBelle1 = new Room(wardBelle, roomCostPublic);//dont save, it is saved already
		Room roomBelle2 = new Room(wardBelle, roomCostSemiPrivate);
		Room roomBelle3 = new Room(wardBelle, roomCostPrivate);
		Room roomBelle4 = new Room(wardBelle, roomCostPrivate);
		Room roomBelle5 = new Room(wardBelle, roomCostPrivate);
		Room roomBelle6 = new Room(wardBelle, roomCostPrivate);

		//Rooms in Sarabi Ward
		Room roomSarabi1 = new Room(wardSarabi, roomCostPublic);
		Room roomSarabi2 = new Room(wardSarabi, roomCostSemiPrivate);
		Room roomSarabi3 = new Room(wardSarabi, roomCostPrivate);
		Room roomSarabi4 = new Room(wardSarabi, roomCostPrivate);
		Room roomSarabi5 = new Room(wardSarabi, roomCostPrivate);
		Room roomSarabi6 = new Room(wardSarabi, roomCostPrivate);

		//Rooms in Pocahontas Ward
		Room roomPocahontas1 = new Room(wardPocahontas , roomCostPublic);
		Room roomPocahontas2 = new Room(wardPocahontas , roomCostSemiPrivate);
		Room roomPocahontas3 = new Room(wardPocahontas , roomCostPrivate);
		Room roomPocahontas4 = new Room(wardPocahontas , roomCostPrivate);
		Room roomPocahontas5 = new Room(wardPocahontas , roomCostPrivate);
		Room roomPocahontas6 = new Room(wardPocahontas , roomCostPrivate);

		//Rooms in Aladdin Ward
		Room roomAladdin1 = new Room(wardAladdin, roomCostPublic);
		Room roomAladdin2 = new Room(wardAladdin, roomCostSemiPrivate);
		Room roomAladdin3 = new Room(wardAladdin, roomCostPrivate);
		Room roomAladdin4 = new Room(wardAladdin, roomCostPrivate);
		Room roomAladdin5 = new Room(wardAladdin, roomCostPrivate);
		Room roomAladdin6 = new Room(wardAladdin, roomCostPrivate);

		//Rooms in Demetrius Ward
		Room roomDemetrius1 = new Room(wardDemetrius, roomCostPublic);
		Room roomDemetrius2 = new Room(wardDemetrius, roomCostSemiPrivate);
		Room roomDemetrius3 = new Room(wardDemetrius, roomCostPrivate);
		Room roomDemetrius4 = new Room(wardDemetrius, roomCostPrivate);
		Room roomDemetrius5 = new Room(wardDemetrius, roomCostPrivate);
		Room roomDemetrius6 = new Room(wardDemetrius, roomCostPrivate);

		//Rooms in Rapunzel Ward
		Room roomRapunzel1 = new Room(wardRapunzel, roomCostPublic);
		Room roomRapunzel2 = new Room(wardRapunzel, roomCostSemiPrivate);
		Room roomRapunzel3 = new Room(wardRapunzel, roomCostPrivate);
		Room roomRapunzel4 = new Room(wardRapunzel, roomCostPrivate);
		Room roomRapunzel5 = new Room(wardRapunzel, roomCostPrivate);
		Room roomRapunzel6 = new Room(wardRapunzel, roomCostPrivate);

		//Rooms in Hercules Ward
		Room roomHercules1 = new Room(wardHercules, roomCostPublic);
		Room roomHercules2 = new Room(wardHercules, roomCostSemiPrivate);
		Room roomHercules3 = new Room(wardHercules, roomCostPrivate);
		Room roomHercules4 = new Room(wardHercules, roomCostPrivate);
		Room roomHercules5 = new Room(wardHercules, roomCostPrivate);
		Room roomHercules6 = new Room(wardHercules, roomCostPrivate);

		//Rooms in Aurora Ward
		Room roomAurora1 = new Room(wardAurora, roomCostPublic);
		Room roomAurora2 = new Room(wardAurora, roomCostSemiPrivate);
		Room roomAurora3 = new Room(wardAurora, roomCostPrivate);
		Room roomAurora4 = new Room(wardAurora, roomCostPrivate);
		Room roomAurora5 = new Room(wardAurora, roomCostPrivate);
		Room roomAurora6 = new Room(wardAurora, roomCostPrivate);

		//Beds in Belle Ward
		//Beds in public room
		Bed BedRoomBele1 = new Bed(true, roomBelle1);//Do not save, its saved already
		Bed BedRoomBele2 = new Bed(true, roomBelle1);
		Bed BedRoomBele3 = new Bed(true, roomBelle1);
		Bed BedRoomBele4 = new Bed(true, roomBelle1);
		Bed BedRoomBele5 = new Bed(true, roomBelle1);
		Bed BedRoomBele6 = new Bed(true, roomBelle1);
		//Beds in Semi-private room
		Bed BedRoomBele7 = new Bed(true, roomBelle2);
		Bed BedRoomBele8 = new Bed(true, roomBelle2);
		Bed BedRoomBele9 = new Bed(true, roomBelle2);
		Bed BedRoomBele10 = new Bed(true, roomBelle2);
		//Beds in Private room
		Bed BedRoomBele11 = new Bed(true, roomBelle3);
		Bed BedRoomBele12 = new Bed(true, roomBelle4);
		Bed BedRoomBele13 = new Bed(true, roomBelle5);
		Bed BedRoomBele14 = new Bed(true, roomBelle6);
		//Beds in Sarabi Ward
		//Public
		Bed BedRoomSarabi1 = new Bed(true, roomSarabi1);
		Bed BedRoomSarabi2 = new Bed(true, roomSarabi1);
		Bed BedRoomSarabi3 = new Bed(true, roomSarabi1);
		Bed BedRoomSarabi4 = new Bed(true, roomSarabi1);
		Bed BedRoomSarabi5 = new Bed(true, roomSarabi1);
		Bed BedRoomSarabi6 = new Bed(true, roomSarabi1);
		//Semi-private
		Bed BedRoomSarabi7 = new Bed(true, roomSarabi2);
		Bed BedRoomSarabi8 = new Bed(true, roomSarabi2);
		Bed BedRoomSarabi9 = new Bed(true, roomSarabi2);
		Bed BedRoomSarabi10 = new Bed(true, roomSarabi2);
		//Private
		Bed BedRoomSarabi11 = new Bed(true, roomSarabi3);
		Bed BedRoomSarabi12 = new Bed(true, roomSarabi4);
		Bed BedRoomSarabi13 = new Bed(true, roomSarabi5);
		Bed BedRoomSarabi14 = new Bed(true, roomSarabi6);

		//Beds in Picahontas Ward
		//Public
		Bed BedRoomPocahontas1 = new Bed(true, roomPocahontas1);
		Bed BedRoomPocahontas2 = new Bed(true, roomPocahontas1);
		Bed BedRoomPocahontas3 = new Bed(true, roomPocahontas1);
		Bed BedRoomPocahontas4 = new Bed(true, roomPocahontas1);
		Bed BedRoomPocahontas5 = new Bed(true,roomPocahontas1);
		Bed BedRoomPocahontas6 = new Bed(true, roomPocahontas1);
		//Semi-private
		Bed BedRoomPocahontas7 = new Bed(true, roomPocahontas2);
		Bed BedRoomPocahontas8 = new Bed(true, roomPocahontas2);
		Bed BedRoomPocahontas9 = new Bed(true, roomPocahontas2);
		Bed BedRoomPocahontas10 = new Bed(true, roomPocahontas2);
		//Private
		Bed BedRoomPocahontas11 = new Bed(true, roomPocahontas3);
		Bed BedRoomPocahontas12 = new Bed(true, roomPocahontas4);
		Bed BedRoomPocahontas13 = new Bed(true, roomPocahontas5);
		Bed BedRoomPocahontas14 = new Bed(true, roomPocahontas6);

		//Beds in Aladin Ward
		//Public
		Bed BedRoomAladdin1 = new Bed(true, roomPocahontas1);
		Bed BedRoomAladdin2 = new Bed(true, roomPocahontas1);
		Bed BedRoomAladdin3 = new Bed(true, roomPocahontas1);
		Bed BedRoomAladdin4 = new Bed(true, roomPocahontas1);
		Bed BedRoomAladdin5 = new Bed(true,roomPocahontas1);
		Bed BedRoomAladdin6 = new Bed(true, roomPocahontas1);
		//Semi-private
		Bed BedRoomAladdin7 = new Bed(true, roomPocahontas2);
		Bed BedRoomAladdin8 = new Bed(true, roomPocahontas2);
		Bed BedRoomAladdin9 = new Bed(true, roomPocahontas2);
		Bed BedRoomAladdin10 = new Bed(true, roomPocahontas2);
		//Private
		Bed BedRoomAladdin11 = new Bed(true, roomPocahontas3);
		Bed BedRoomAladdin12 = new Bed(true, roomPocahontas4);
		Bed BedRoomAladdin13 = new Bed(true, roomPocahontas5);
		Bed BedRoomAladdin14 = new Bed(true, roomPocahontas6);

		//Beds in Demetrius Ward
		//Public
		Bed BedRoomDemetrius1 = new Bed(true, roomDemetrius1);
		Bed BedRoomDemetrius2 = new Bed(true, roomDemetrius1);
		Bed BedRoomDemetrius3 = new Bed(true, roomDemetrius1);
		Bed BedRoomDemetrius4 = new Bed(true, roomDemetrius1);
		Bed BedRoomDemetrius5 = new Bed(true, roomDemetrius1);
		Bed BedRoomDemetrius6 = new Bed(true, roomDemetrius1);
		//Semi-private
		Bed BedRoomDemetrius7 = new Bed(true, roomDemetrius2);
		Bed BedRoomDemetrius8 = new Bed(true, roomDemetrius2);
		Bed BedRoomDemetrius9 = new Bed(true, roomDemetrius2);
		Bed BedRoomDemetrius10 = new Bed(true, roomDemetrius2);
		//Private
		Bed BedRoomDemetrius11 = new Bed(true, roomDemetrius3);
		Bed BedRoomDemetrius12 = new Bed(true, roomDemetrius4);
		Bed BedRoomDemetrius13 = new Bed(true, roomDemetrius5);
		Bed BedRoomDemetrius14 = new Bed(true, roomDemetrius6);

		//Beds in Rapuncel Ward
		Bed BedRoomRapunzel1 = new Bed(true, roomRapunzel1);
		Bed BedRoomRapunzel2 = new Bed(true, roomRapunzel1);
		Bed BedRoomRapunzel3 = new Bed(true, roomRapunzel1);
		Bed BedRoomRapunzel4 = new Bed(true, roomRapunzel1);
		Bed BedRoomRapunzel5 = new Bed(true, roomRapunzel1);
		Bed BedRoomRapunzel6 = new Bed(true, roomRapunzel1);
		//Semi-private
		Bed BedRoomRapunzel7 = new Bed(true, roomRapunzel2);
		Bed BedRoomRapunzel8 = new Bed(true, roomRapunzel2);
		Bed BedRoomRapunzel9 = new Bed(true, roomRapunzel2);
		Bed BedRoomRapunzel10 = new Bed(true, roomRapunzel2);
		//Private
		Bed BedRoomRapunzel11 = new Bed(true, roomRapunzel3);
		Bed BedRoomRapunzel12 = new Bed(true, roomRapunzel4);
		Bed BedRoomRapunzel13 = new Bed(true, roomRapunzel5);
		Bed BedRoomRapunzel14 = new Bed(true, roomRapunzel6);

		//Beds in Hercules Ward
		//Public
		Bed BedRoomHercules1 = new Bed(true, roomHercules1);
		Bed BedRoomHercules2 = new Bed(true, roomHercules1);
		Bed BedRoomHercules3 = new Bed(true, roomHercules1);
		Bed BedRoomHercules4 = new Bed(true, roomHercules1);
		Bed BedRoomHercules5 = new Bed(true, roomHercules1);
		Bed BedRoomHercules6 = new Bed(true, roomHercules1);
		//Semi-private
		Bed BedRoomHercules7 = new Bed(true, roomHercules2);
		Bed BedRoomHercules8 = new Bed(true, roomHercules2);
		Bed BedRoomHercules9 = new Bed(true, roomHercules2);
		Bed BedRoomHercules10 = new Bed(true, roomHercules2);
		//Private
		Bed BedRoomHercules11 = new Bed(true, roomHercules3);
		Bed BedRoomHercules12 = new Bed(true, roomHercules4);
		Bed BedRoomHercules13 = new Bed(true, roomHercules5);
		Bed BedRoomHercules14 = new Bed(true, roomHercules6);

		//Beds in Aurora Ward
		//Public
		Bed BedRoomAurora1 = new Bed(true, roomAurora1);
		Bed BedRoomAurora2 = new Bed(true, roomAurora1);
		Bed BedRoomAurora3 = new Bed(true, roomAurora1);
		Bed BedRoomAurora4 = new Bed(true, roomAurora1);
		Bed BedRoomAurora5 = new Bed(true, roomAurora1);
		Bed BedRoomAurora6 = new Bed(true, roomAurora1);
		//Semi-private
		Bed BedRoomAurora7 = new Bed(true, roomAurora2);
		Bed BedRoomAurora8 = new Bed(true, roomAurora2);
		Bed BedRoomAurora9 = new Bed(true, roomAurora2);
		Bed BedRoomAurora10 = new Bed(true,roomAurora2);
		//Private
		Bed BedRoomAurora11 = new Bed(true, roomAurora3);
		Bed BedRoomAurora12 = new Bed(true, roomAurora4);
		Bed BedRoomAurora13 = new Bed(true, roomAurora5);
		Bed BedRoomAurora14 = new Bed(true, roomAurora6);

		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			//Integer id = (Integer)session.save(depratment);
			//Integer id = (Integer)session.save(doctor);
			
			//RoombBelle1 already saved
			session.save(roomBelle2);
			session.save(roomBelle3);
			session.save(roomBelle4);
			session.save(roomBelle5);
			session.save(roomBelle6);
			
			session.save(roomSarabi1);
			session.save(roomSarabi2);
			session.save(roomSarabi3);
			session.save(roomSarabi4);
			session.save(roomSarabi5);
			session.save(roomSarabi6);
			
			session.save(roomPocahontas1);
			session.save(roomPocahontas2);
			session.save(roomPocahontas3);
			session.save(roomPocahontas4);
			session.save(roomPocahontas5);
			session.save(roomPocahontas6);
			
			session.save(roomAladdin1);
			session.save(roomAladdin2);
			session.save(roomAladdin3);
			session.save(roomAladdin4);
			session.save(roomAladdin5);
			session.save(roomAladdin6);
			
			session.save(roomDemetrius1);
			session.save(roomDemetrius2);
			session.save(roomDemetrius3);
			session.save(roomDemetrius4);
			session.save(roomDemetrius5);
			session.save(roomDemetrius6);
			
			session.save(roomRapunzel1);
			session.save(roomRapunzel2);
			session.save(roomRapunzel3);
			session.save(roomRapunzel4);
			session.save(roomRapunzel5);
			session.save(roomRapunzel6);
			
			session.save(roomHercules1);
			session.save(roomHercules2);
			session.save(roomHercules3);
			session.save(roomHercules4);
			session.save(roomHercules5);
			session.save(roomHercules6);
			
			session.save(roomAurora1);
			session.save(roomAurora2);
			session.save(roomAurora3);
			session.save(roomAurora4);
			session.save(roomAurora5);
			session.save(roomAurora6);
			
			//Insert beds for all rooms in all departments
			//BedRoomBele1 already saved
			session.save(BedRoomBele2);
			session.save(BedRoomBele3);
			session.save(BedRoomBele4);
			session.save(BedRoomBele5);
			session.save(BedRoomBele6);
			session.save(BedRoomBele7);
			session.save(BedRoomBele8);
			session.save(BedRoomBele9);
			session.save(BedRoomBele10);
			session.save(BedRoomBele11);
			session.save(BedRoomBele12);
			session.save(BedRoomBele13);
			session.save(BedRoomBele14);
			
			session.save(BedRoomSarabi1);
			session.save(BedRoomSarabi2);
			session.save(BedRoomSarabi3);
			session.save(BedRoomSarabi4);
			session.save(BedRoomSarabi5);
			session.save(BedRoomSarabi6);
			session.save(BedRoomSarabi7);
			session.save(BedRoomSarabi8);
			session.save(BedRoomSarabi9);
			session.save(BedRoomSarabi10);
			session.save(BedRoomSarabi11);
			session.save(BedRoomSarabi12);
			session.save(BedRoomSarabi13);
			session.save(BedRoomSarabi14);
			
			session.save(BedRoomPocahontas1);
			session.save(BedRoomPocahontas2);
			session.save(BedRoomPocahontas3);
			session.save(BedRoomPocahontas4);
			session.save(BedRoomPocahontas5);
			session.save(BedRoomPocahontas6);
			session.save(BedRoomPocahontas7);
			session.save(BedRoomPocahontas8);
			session.save(BedRoomPocahontas9);
			session.save(BedRoomPocahontas10);
			session.save(BedRoomPocahontas11);
			session.save(BedRoomPocahontas12);
			session.save(BedRoomPocahontas13);
			session.save(BedRoomPocahontas14);
			
			session.save(BedRoomAladdin1);
			session.save(BedRoomAladdin2);
			session.save(BedRoomAladdin3);
			session.save(BedRoomAladdin4);
			session.save(BedRoomAladdin5);
			session.save(BedRoomAladdin6);
			session.save(BedRoomAladdin7);
			session.save(BedRoomAladdin8);
			session.save(BedRoomAladdin9);
			session.save(BedRoomAladdin10);
			session.save(BedRoomAladdin11);
			session.save(BedRoomAladdin12);
			session.save(BedRoomAladdin13);
			session.save(BedRoomAladdin14);
			
			session.save(BedRoomDemetrius1);
			session.save(BedRoomDemetrius2);
			session.save(BedRoomDemetrius3);
			session.save(BedRoomDemetrius4);
			session.save(BedRoomDemetrius5);
			session.save(BedRoomDemetrius6);
			session.save(BedRoomDemetrius7);
			session.save(BedRoomDemetrius8);
			session.save(BedRoomDemetrius9);
			session.save(BedRoomDemetrius10);
			session.save(BedRoomDemetrius11);
			session.save(BedRoomDemetrius12);
			session.save(BedRoomDemetrius13);
			session.save(BedRoomDemetrius14);
			
			session.save(BedRoomRapunzel1);
			session.save(BedRoomRapunzel2);
			session.save(BedRoomRapunzel3);
			session.save(BedRoomRapunzel4);
			session.save(BedRoomRapunzel5);
			session.save(BedRoomRapunzel6);
			session.save(BedRoomRapunzel7);
			session.save(BedRoomRapunzel8);
			session.save(BedRoomRapunzel9);
			session.save(BedRoomRapunzel10);
			session.save(BedRoomRapunzel11);
			session.save(BedRoomRapunzel12);
			session.save(BedRoomRapunzel13);
			session.save(BedRoomRapunzel14);

			session.save(BedRoomHercules1);
			session.save(BedRoomHercules2);
			session.save(BedRoomHercules3);
			session.save(BedRoomHercules4);
			session.save(BedRoomHercules5);
			session.save(BedRoomHercules6);
			session.save(BedRoomHercules7);
			session.save(BedRoomHercules8);
			session.save(BedRoomHercules9);
			session.save(BedRoomHercules10);
			session.save(BedRoomHercules11);
			session.save(BedRoomHercules12);
			session.save(BedRoomHercules13);
			session.save(BedRoomHercules14);
			
			session.save(BedRoomAurora1);
			session.save(BedRoomAurora2);
			session.save(BedRoomAurora3);
			session.save(BedRoomAurora4);
			session.save(BedRoomAurora5);
			session.save(BedRoomAurora6);
			session.save(BedRoomAurora7);
			session.save(BedRoomAurora8);
			session.save(BedRoomAurora9);
			session.save(BedRoomAurora10);
			session.save(BedRoomAurora11);
			session.save(BedRoomAurora12);
			session.save(BedRoomAurora13);
			session.save(BedRoomAurora14);
			

			System.out.println("Record saved");
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
