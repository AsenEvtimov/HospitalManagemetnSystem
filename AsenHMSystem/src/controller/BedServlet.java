package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Bed;
import model.Doctor;
import model.Room;
import model.Ward;

/**
 * Servlet implementation class BedServlet
 */
@WebServlet("/BedServlet")
public class BedServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private BedDAO  bedDao;

	public BedServlet() {
		super();
		bedDao = new BedDAO();
		System.out.println("Constructor Called");
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		if (action == null)
			action = "bedLayout";
		System.out.println("action: " + action);

		switch (action) {
		case "showBedsInWard":
			showBedsInWard(request, response);
			break;
		case "showRoomsInWard":
			showRoomsInWard(request, response);
			break;
		case "showRoomsInDepartment":
			showRoomsInDepartment(request, response);
			break;
			
		default:
			getAllWards(request, response);
			break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	protected void showBedsInWard(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String wardId = request.getParameter("wardId");
		Ward ward = bedDao.getWardById(Integer.parseInt(wardId));
		String wardName = ward.getName();
		request.setAttribute("wardName", wardName);
		List<Bed> listOfBeds = new ArrayList<>();
		listOfBeds = bedDao.getBedsInWarad(wardId);
		request.setAttribute("listOfBeds", listOfBeds);
		request.getRequestDispatcher("\\WEB-INF\\view\\BedsLayout.jsp")
		.forward(request, response);
	}

	protected void showRoomsInDepartment(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		request.getSession().setAttribute("isOutpatient", request.getParameter("isOutpatient"));
		
		request.getSession().setAttribute("admissionDate", request.getParameter("admissionDate"));
		System.out.println(request.getParameter("admissionDate"));
		request.getSession().setAttribute("dischargeDate", request.getParameter("dischargeDate"));		
		request.getSession().setAttribute("appointmentDate", request.getParameter("appointmentDate"));
		
		List<Room> listOfRooms = new ArrayList<>();
		
		String deptId = request.getParameter("deptId");
		System.out.println("show rooms for departmentId: " + deptId);
		
		listOfRooms = bedDao.getRoomsByDepartment(deptId);
		request.setAttribute("listOfRooms", listOfRooms);
		request.getRequestDispatcher("\\WEB-INF\\view\\RoomsLayOut.jsp")
		.forward(request, response);
	}
	
	protected void showRoomsInWard(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		List<Room> listOfRooms = new ArrayList<>();
		
		String wardId = request.getParameter("wardId");
		System.out.println("show rooms for wardId: " + wardId);
		
		listOfRooms = bedDao.getRooms(wardId);
		request.setAttribute("listOfRooms", listOfRooms);
		request.getRequestDispatcher("\\WEB-INF\\view\\RoomsLayOut.jsp")
		.forward(request, response);
	}
	
	protected void getAllWards(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Ward> listOfWards = new ArrayList<>();
		listOfWards = bedDao.getAllWards();
		request.setAttribute("listOfWards", listOfWards);
		request.getRequestDispatcher("\\WEB-INF\\view\\WardsLayout.jsp")
		.forward(request, response);
	}

}
