package controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Department;
import model.Doctor;

@WebServlet("/DoctorServlet")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DoctorDAO  doctorDao;

	public DoctorServlet() {
		super();
		doctorDao = new DoctorDAO();
		System.out.println("Constructor Called");
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null)
			action = "viewAll";
		System.out.println("action: " + action);

		switch (action) {
		case "searchDoctor":
			searchForDoctor(request, response);
			break;
		case "insertNewtDoctor":
			insertNewDoctor(request, response);
			break;
		case "showInsertDoctorForm":
			showInsertDoctorForm(request, response);
			break;
		case "showUpdateDoctorForm":
			showUpdateDoctorForm(request, response);
			break;
		case "updateDoctor":
			updateDoctor(request, response);
			break;
		case "delete":
			delete(request, response);
			break;
		case "Cardiology":
			getDoctorByDepartment(request, response);
			break;
		case "Physiotheraphy":
			getDoctorByDepartment(request, response);
			break;
		case "Rheumatology":
			getDoctorByDepartment(request, response);
			break;
		case "Opthhalmology":
			getDoctorByDepartment(request, response);
			break;
		default:
			getAllDoctors(request, response);		
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		doctorDao.deleteDoctor(doctorId);
		response.sendRedirect("DoctorServlet?action=viewAll");
	}

	private void searchForDoctor(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String searchText = request.getParameter("searchDoctor"); // text field
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
				if(doctorDao.searchDoctor(searchType, searchText).isEmpty()) {
					request.setAttribute("message1", "Not Found");
				}
				List<Doctor> listOfDoctors = doctorDao.searchDoctor(searchType, searchText);
				request.setAttribute("listOfDoctors", listOfDoctors);
				request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp")
				.forward(request, response);
			} catch (Exception e) {
				request.setAttribute("message", "Type a number");
				request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp")
				.forward(request, response);
			}
		}else {
			System.out.println("in else");
			List<Doctor> listOfDoctors = doctorDao.searchDoctor(searchType, searchText);
			request.setAttribute("listOfDoctors", listOfDoctors);

			request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp")
			.forward(request, response);
		}
		
		
	}

	private void insertNewDoctor(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
		String address = request.getParameter("address");
		String phoneNumber= request.getParameter("phoneNumber");
		String qualifications = request.getParameter("qualifications");
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		Department dept = doctorDao.getDepartmetnById(departmentId);

		Doctor doctor = new Doctor(firstName, lastName, gender, address, phoneNumber, qualifications, dept);
		System.out.println("New Doctor added: " + doctor);
		doctorDao.insertDoctor(doctor);
		response.sendRedirect("DoctorServlet?action=viewAll");
	}

	private void showInsertDoctorForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\newDoctorForm.jsp");
		dispatcher.forward(request, response);
	}

	private void showUpdateDoctorForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));

		Doctor doctor = doctorDao.getDoctorById(doctorId);
		System.out.println("Showing update form for: " + doctor);
		request.setAttribute("doctor", doctor);
		RequestDispatcher dispatcher = request.getRequestDispatcher(
				"\\WEB-INF\\view\\updateDoctorForm.jsp");
		dispatcher.forward(request, response);
	}

	protected void updateDoctor(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		int doctorId = Integer.parseInt(request.getParameter("doctorId"));
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		boolean gender = Boolean.parseBoolean(request.getParameter("gender"));
		String address = request.getParameter("address");
		String phoneNumber = request.getParameter("phoneNumber");
		String qualifications = request.getParameter("qualifications");
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		Department dept = doctorDao.getDepartmetnById(departmentId);

		Doctor doctorToUpdate = new Doctor(doctorId, firstName, lastName, gender, address, phoneNumber, qualifications,dept);
		doctorDao.updateDoctor(doctorToUpdate);
		response.sendRedirect("DoctorServlet?action=viewAll");
	}

	protected void getAllDoctors(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		List<Doctor> listOfDoctors = new ArrayList<>();
		listOfDoctors = doctorDao.getAllDoctors();
		request.setAttribute("listOfDoctors", listOfDoctors);
		
		//in case if choose new doctor from AdmissionForm JSP
		request.getSession().removeAttribute("bed");
		
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void getDoctorByDepartment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		int departmentId = Integer.parseInt(request.getParameter("departmentId"));
		List<Doctor> listOfDoctors = new ArrayList<>();
		listOfDoctors = doctorDao.getDoctorByDepartment(departmentId);
		request.setAttribute("listOfDoctors", listOfDoctors);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\viewDoctor.jsp");
		dispatcher.forward(request, response);
	}

}
