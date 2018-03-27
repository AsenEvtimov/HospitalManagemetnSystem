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

import model.Bed;
import model.Doctor;
import model.Employee;
import model.LoginUser;
import model.Patient;
import model.Visit;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LoginDAO loginDao;
	HomeDAO homeDao;
	
    public HomeServlet() {
        super();
        loginDao = new LoginDAO();
        homeDao = new HomeDAO();
        System.out.println("constructor called");
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if (action == null)
			action = "showHomePage";
		System.out.println("action: " + action);
		
		switch (action) {
		case "viewAppointmets":
			viewAppointmets(request, response);
			break;
		case "manageAppointmets":
			viewAppointmets(request, response);
			break;
		case "deleteVisit":
			deleteVisit(request, response);
			break;
		case "updateVisit":
			updateVisit(request, response);
			break;
		case "showVisitForUpdate":
			showVisitForUpdate(request, response);
			break;
		default:
			showHomePage(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void updateVisit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Patient patient = (Patient)request.getSession().getAttribute("patient");
		System.out.println("Patietn in updateVisit: " + patient);
		Doctor doctor = (Doctor)request.getSession().getAttribute("doctor");
		System.out.println("Doctor in updateVisit: " + doctor);
		Bed bed = (Bed)request.getSession().getAttribute("bed");
		System.out.println("Bed in updateVisit: " + bed);
		boolean isOutpatient = Boolean.parseBoolean(request.getParameter("isOutpatient"));
		System.out.println("isOupatient in updateVisit: " + isOutpatient);
		LocalDate admissionDate = LocalDate.parse(request.getParameter("admissionDate"));
		System.out.println("admissionDate in updateVisit: " + admissionDate);
		LocalDate dischargeDate = LocalDate.parse(request.getParameter("dischargeDate"));
		System.out.println("dischargeDate in updateVisit: "+ dischargeDate);
		LocalDate appointmentDate = LocalDate.parse(request.getParameter("appointmentDate"));
		System.out.println("appointmentDate in updateVisit: "+ appointmentDate);
		int visitId = Integer.parseInt(request.getSession().getAttribute("visitId").toString());
		System.out.println("visitId in updateVisit: " + visitId);
		Visit visit = new Visit(visitId,isOutpatient, admissionDate, dischargeDate, appointmentDate, bed, doctor, patient);
		
		System.out.println("Visit to update: " + visit);
		homeDao.updateVisit(visit);
		response.sendRedirect("HomeServlet?action=manageAppointmets&empId="+visit.getDoctor().getEmpId());
	}
	
	protected void showVisitForUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
					
		int visitId = Integer.parseInt(request.getParameter("visitId"));
		request.getSession().setAttribute("visitId", visitId);
		System.out.println("visitId to update: " + visitId);
			
		Visit visit = homeDao.getVisitById(visitId);
			
		List<Doctor> listOfDoctors = new ArrayList<>();
		listOfDoctors.add(visit.getDoctor());
			
		String action = request.getParameter("action");
		request.getSession().setAttribute("action", action);
		request.getSession().setAttribute("patient", visit.getPatient());
		System.out.println(visit.getPatient());
		request.setAttribute("listOfDoctors", listOfDoctors);
		request.getSession().setAttribute("doctor", visit.getDoctor());
		request.getSession().setAttribute("bed", visit.getBed());
		request.getSession().setAttribute("isOutpatient", visit.isIsOutpatient());
		System.out.println("isOutpatient in showVisitForUpdate: " + visit.isIsOutpatient());
		request.setAttribute("admissionDate", visit.getAdmissionDate());
		request.setAttribute("dischargeDate", visit.getDischargeDate());
		request.setAttribute("appointmentDate", visit.getAppointmentDate());
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\AdmissionForm.jsp");
		dispatcher.forward(request, response);
		//response.sendRedirect("PatientServlet?action=showAdmissionForm");
		
	}

	private void deleteVisit(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		int empId = Integer.parseInt(request.getParameter("empId"));
		
		int visitId = Integer.parseInt(request.getParameter("visitId"));
		System.out.println("visit to delete: " + visitId);
		homeDao.deleteVisit(visitId);
		/*String username =  request.getParameter("username");
		System.out.println(username);*/
		
		
		response.sendRedirect("HomeServlet?action=manageAppointmets&empId="+empId);
		/*RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\manageAppointments.jsp");
		dispatcher.forward(request, response);*/
	}
	
	protected void viewAppointmets(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		int empId = Integer.parseInt(request.getParameter("empId"));
		System.out.println("Appointmetns for emp Id: " + empId);
		
		List<Visit> listOfVisit = new ArrayList<>();
		listOfVisit = homeDao.getAppointments(empId);
		System.out.println(listOfVisit);
		String message = "No appointmetns";
		request.setAttribute("listOfVisit", listOfVisit);
		request.setAttribute("message", message);
		System.out.println("action in viewAppointmetns: " + request.getParameter("action"));
		if (request.getParameter("action").equals("manageAppointmets")) {
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("\\WEB-INF\\view\\manageAppointments.jsp");
			dispatcher.forward(request, response);
		}else {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\Home.jsp");
		dispatcher.forward(request, response);
		}
	}
	
	protected void showHomePage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		Object username = request.getSession().getAttribute("username");
		//String password = request.getParameter("password");
		//System.out.println("username: " +username + " password: " +password);
				
		LoginUser currentUser = loginDao.getCurrentUser((String)username);
				
		try {
			int empId = currentUser.getEmployee().getEmpId();
			request.setAttribute("empId", empId);
			String userType = currentUser.getUserType();
			//request.setAttribute("currentUser", currentUser);
			request.getSession().setAttribute("userType", userType);
			System.out.println("Current userType: " + userType + " empId: " + empId);
			RequestDispatcher dispatcher = 
					request.getRequestDispatcher("\\WEB-INF\\view\\Home.jsp");
			dispatcher.forward(request, response);
			
		} catch (Exception e) {
			response.sendRedirect("LoginServlet?action=showLoginForm");
		}
		
	}
}
