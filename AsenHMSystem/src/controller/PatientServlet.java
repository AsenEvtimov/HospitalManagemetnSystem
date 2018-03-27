package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Bed;
import model.Doctor;
import model.Patient;
import model.Visit;

@WebServlet("/PatientServlet")
public class PatientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private PatientDAO patientDao;
	private DoctorDAO doctorDao;
	private BedDAO bedDao;
	//private HttpSession session;
	
	public PatientServlet() {
		super();
		patientDao = new PatientDAO();
		doctorDao = new DoctorDAO();
		bedDao = new BedDAO();
		System.out.println("Constructor Called");
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {
			action = "showRegisterPatientForm";
		}
		System.out.println("action: " + action);

		switch (action) {
		
		case "addVisit":
			saveVisit(request, response);
			break;
		case "registerNewVisit":
			registerNewVisit(request, response);
			break;
		case "registerNewPatient":
			registerNewPatient(request, response);
			break;
		case "assignBed":
			assignBed(request, response);
			break;
		case "searchDoctor":
			searchDoctor(request, response);
			break;
		case "deletPatient":
			deletePatient(request, response);
			break;
		case "insertNewPatient":
			insertNewPatient(request, response);
			break;
		case "showNewPatientForm":
			showNewPatientForm(request, response);
			break;
		case "updatePatient":
			updatePatient(request, response);
			break;
		case "showAdmissionForm":
			showAdmissionFrom(request, response);
			break;
		case "searchPatient":
			searchForPatient(request, response);
			break;
		case "viewAllPatient":
			viewAllPatient(request, response);
			break;
		default:
			showRegisterPatientForm(request, response);
			break;
		}
	} 
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void saveVisit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getSession().setAttribute("isOutpatient", request.getParameter("isOutpatient"));
		boolean isOutpatient = Boolean.parseBoolean(request.getParameter("isOutpatient"));
		
		request.getSession().setAttribute("admissionDate", request.getParameter("admissionDate"));
		LocalDate admissionDate = LocalDate.parse(request.getParameter("admissionDate"));
		
		request.getSession().setAttribute("dischargeDate", request.getParameter("dischargeDate"));
		LocalDate dischargeDate = LocalDate.parse(request.getParameter("dischargeDate"));
		
		request.getSession().setAttribute("appointmentDate", request.getParameter("appointmentDate"));
		LocalDate appointmentDate = LocalDate.parse(request.getParameter("appointmentDate"));
		
		Bed bed  = (Bed)request.getSession().getAttribute("bed");
		
		System.out.println(request.getParameter("doctorId"));
		Doctor doctor = doctorDao.getDoctorById(Integer.parseInt(request.getParameter("doctorId")));
		//Doctor doctor = (Doctor)request.getSession().getAttribute("doctor");
		Patient patient = (Patient)request.getSession().getAttribute("patient");
		
		Visit visit = new Visit(isOutpatient, admissionDate, dischargeDate, appointmentDate, bed, doctor, patient);
		System.out.println("new visit" + visit);
		
		patientDao.saveVisit(visit);
		
		response.sendRedirect("PatientServlet?action=showAdmissionForm");
	}

	protected void registerNewPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().invalidate();
		response.sendRedirect("PatientServlet?action=showRegisterPatientForm");
	}
	
	protected void registerNewVisit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getSession().removeAttribute("listOfDoctors");
		request.getSession().removeAttribute("bed");
		request.getSession().removeAttribute("admissionDate");
		request.getSession().removeAttribute("dischargeDate");
		request.getSession().removeAttribute("appointmentDate");
		request.getSession().removeAttribute("isOutpatient");
		
		response.sendRedirect("PatientServlet?action=showAdmissionForm");
	}

	
	protected void assignBed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int deptId = Integer.parseInt(request.getParameter("deptId"));
		String roomType = request.getParameter("roomType");
		System.out.println("registering bed for deptId: " + deptId + " and roomType: " + roomType);
		
		request.getSession().setAttribute("admissionDate", request.getParameter("admissionDate"));//String
		System.out.println("admissiondate: " + request.getSession().getAttribute("admissionDate"));//Object

		request.getSession().setAttribute("dischargeDate", request.getParameter("dischargeDate"));
		System.out.println("dischargeDate: " + request.getSession().getAttribute("dischargeDate"));
		
		request.getSession().setAttribute("appointmentDate", request.getParameter("appointmentDate"));
		System.out.println("appointmentDate: " + request.getSession().getAttribute("appointmentDate"));
		
		request.getSession().setAttribute("isOutpatient", request.getParameter("isOutpatient"));
		System.out.println("isOutpatient: " + request.getSession().getAttribute("isOutpatient"));
		
		Bed bed = bedDao.getFreeBedByDept(deptId, roomType);
		request.getSession().setAttribute("bed", bed);
		System.out.println("BedId to assign: " + bed.getBedId());
		
		response.sendRedirect("PatientServlet?action=showAdmissionForm");
	}
	
	protected void searchDoctor(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		request.setAttribute("action", action);

		/*int patientId = Integer.parseInt(request.getParameter("patientId"));
		Patient patient = patientDao.getPatientById(patientId);
		System.out.println("Search Doctor for patientId: " + patientId);
		request.setAttribute("patient", patient);*/

		String searchText = request.getParameter("searchDoctor"); // text field
		String searchType = request.getParameter("searchType"); // id/name
		System.out.println("searchtype: "+ searchType + " serachText: "+ searchText);

		if (searchType.equals("id")) {
			try {
				Integer.parseInt(searchText);
				if(doctorDao.searchDoctor(searchType, searchText).isEmpty()) {
					request.setAttribute("message1", "Not Found");
				}
				List<Doctor> listOfDoctors = doctorDao.searchDoctor(searchType, searchText);
				request.getSession().setAttribute("listOfDoctors", listOfDoctors);
				response.sendRedirect("PatientServlet?action=showAdmissionForm");
			} catch (Exception e) {
				request.setAttribute("message", "Type a number");
				response.sendRedirect("PatientServlet?action=showAdmissionForm");
			}
		}else {
			System.out.println("in else");
			List<Doctor> listOfDoctors = doctorDao.searchDoctor(searchType, searchText);
			request.getSession().setAttribute("listOfDoctors", listOfDoctors);

			response.sendRedirect("PatientServlet?action=showAdmissionForm");
		}

		request.getSession().removeAttribute("bed");
	}

	protected void deletePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));
		System.out.println("patietnId to delete: "+ patientId);
		patientDao.deletePatient(patientId);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\RegisterPatient.jsp");
		dispatcher.forward(request, response);
	}

	protected void updatePatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String foreName = request.getParameter("firstName");
		String surName = request.getParameter("lastName");
		boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
		LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dob"));
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phone");
		String nextOfKin = request.getParameter("kin");

		Patient patient = new Patient(patientId,foreName, surName, dateOfBirth, gender, address, phoneNumber, nextOfKin);
		request.setAttribute("patient", patient);
		patientDao.updatePatient(patient);
		request.setAttribute("message", "Patient updated");
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\AdmissionForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void insertNewPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String foreName = request.getParameter("firstName");
		String surName = request.getParameter("lastName");
		boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
		LocalDate dateOfBirth = LocalDate.parse(request.getParameter("dob"));
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phone");
		String nextOfKin = request.getParameter("kin");

		Patient patient = new Patient(foreName, surName, dateOfBirth, gender, address, phoneNumber, nextOfKin);
		request.setAttribute("patient", patient);
		patientDao.insertPatient(patient);
		response.sendRedirect("PatientServlet?action=showAdmissionForm");
	}

	protected void showNewPatientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\NewPatientForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void showAdmissionFrom(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//this attribute is in case if you select bed form BedsLayout JSP
		String stringBedId = request.getParameter("bedId");
		System.out.println("Bed Id in showAddmissionForm: "+ stringBedId);
		if (stringBedId != null) {
			int bedId = Integer.parseInt(stringBedId);
			//request.setAttribute("doctorId", doctorId);
			Bed bed = bedDao.getBedById(bedId);
			
			request.getSession().setAttribute("bed", bed);
		}
			
		//this attribute is in case if you select doctor form view all doctors JSP
		String stringDoctorId = request.getParameter("doctorId");
		System.out.println("Doctor Id in showAddmissionForm: "+ stringDoctorId);
		if (stringDoctorId != null) {
			int doctorId = Integer.parseInt(stringDoctorId);
			//request.setAttribute("doctorId", doctorId);
			Doctor doctor = doctorDao.getDoctorById(doctorId);
			List<Doctor> listOfDoctor = new ArrayList<>();
			listOfDoctor.add(doctor);
			request.getSession().setAttribute("doctor", doctor);
			request.getSession().setAttribute("listOfDoctors", listOfDoctor);
		}
				
		try {
					
			int patientId = Integer.parseInt(request.getParameter("patientId"));
			Patient patient = patientDao.getPatientById(patientId);
			System.out.println("Registering patient: " + patient);
			request.getSession().setAttribute("patient", patient);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("\\WEB-INF\\view\\AdmissionForm.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("\\WEB-INF\\view\\AdmissionForm.jsp");
			dispatcher.forward(request, response);
		}
		
		request.getSession().setAttribute("admissionDate", request.getParameter("admissionDate"));//String
		System.out.println("admissiondate: " + request.getSession().getAttribute("admissionDate"));//Object

		request.getSession().setAttribute("dischargeDate", request.getParameter("dischargeDate"));
		System.out.println("dischargeDate: " + request.getSession().getAttribute("dischargeDate"));
		
		request.getSession().setAttribute("appointmentDate", request.getParameter("appointmentDate"));
		System.out.println("appointmentDate: " + request.getSession().getAttribute("appointmentDate"));
		
		request.getSession().setAttribute("isOutpatient", request.getParameter("isOutpatient"));
		System.out.println("isOutpatient: " + request.getSession().getAttribute("isOutpatient"));

	}

	private void searchForPatient(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String action = request.getParameter("action");
		request.setAttribute("action", action);
		System.out.println("action in searchForPatient method: " + action);

		String searchText = request.getParameter("searchPatient"); // text field
		String searchType = request.getParameter("searchType"); // product/make/model
		System.out.println("searchtype: "+ searchType + " serachText: "+ searchText);

		/*if(doctorDao.searchDoctor(searchType, searchText).isEmpty()) {
			request.setAttribute("message1", "Not Found");
			request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp")
			.forward(request, response);
			System.out.println("listOfDocttors isEmtpy: "
			+doctorDao.searchDoctor(searchType, searchText).isEmpty());
		}*/
		if (searchType.equals("id")) {
			try {
				Integer.parseInt(searchText);
				if(patientDao.searchPatient(searchType, searchText).isEmpty()) {
					request.setAttribute("message1", "Not Found");
				}
				List<Patient> listOfPatients = patientDao.searchPatient(searchType, searchText);
				request.setAttribute("listOfPatients", listOfPatients);
				request.getRequestDispatcher("\\WEB-INF\\view\\RegisterPatient.jsp")
				.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("message", "Type a number");
				request.getRequestDispatcher("\\WEB-INF\\view\\RegisterPatient.jsp")
				.forward(request, response);
			}
		}else {
			System.out.println("in else");
			List<Patient> listOfPatients = patientDao.searchPatient(searchType, searchText);
			request.setAttribute("listOfPatients", listOfPatients);

			request.getRequestDispatcher("\\WEB-INF\\view\\RegisterPatient.jsp")
			.forward(request, response);
		}


	}

	protected void viewAllPatient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Patient> listOfPatient = new ArrayList<>();
		listOfPatient = patientDao.getAllPatients();
		request.setAttribute("listOfPatient", listOfPatient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("\\WEB-INF\\view\\viewPatient.jsp");
		dispatcher.forward(request, response);
	}

	protected void showRegisterPatientForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\RegisterPatient.jsp");
		dispatcher.forward(request, response);			
	}
}
