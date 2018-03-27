package controller;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactory;
	
	private HibernateUtil() {}
	
	static {
		Configuration config = new Configuration().configure();
		config.addAnnotatedClass(model.Employee.class);
		config.addAnnotatedClass(model.Administrator.class);
		config.addAnnotatedClass(model.Doctor.class);
		config.addAnnotatedClass(model.Department.class);
		config.addAnnotatedClass(model.Ward.class);
		config.addAnnotatedClass(model.Room.class);
		config.addAnnotatedClass(model.RoomCost.class);
		config.addAnnotatedClass(model.Bed.class);
		config.addAnnotatedClass(model.Patient.class);
		config.addAnnotatedClass(model.PatientNotes.class);
		config.addAnnotatedClass(model.Prescription.class);
		config.addAnnotatedClass(model.Billing.class);
		config.addAnnotatedClass(model.Visit.class);
		config.addAnnotatedClass(model.LoginUser.class);
		
		
		StandardServiceRegistryBuilder builder = 
			new StandardServiceRegistryBuilder().applySettings(config.getProperties());
		sessionFactory = config.buildSessionFactory(builder.build());
	}

	protected static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
}
