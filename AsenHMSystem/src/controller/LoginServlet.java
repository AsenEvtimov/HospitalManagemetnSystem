package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Employee;
import model.LoginUser;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	LoginDAO loginDao;

    public LoginServlet() {
        super();
        loginDao = new LoginDAO();
        System.out.println("Constructor Called");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null)
			action = "showLoginForm";
		System.out.println("action: " + action);
		
		switch(action) {
	
		case "showCreateUserForm":
			showNewUserForm(request, response);
			break;
		case "showLoginForm":
			showLoginForm(request, response);
			break;
		case "createUser":
			insertNewUser(request, response);
			break;
		case "login":
			login(request, response);
			break;
		case "delete":
			deleteUser(request, response);
			break;
		case "logOut":
			logOut(request, response);
			break;
		case "viewSuperUserPage":
			showSuperUserPage(request, response);
			break;
		case "showUpdateForm":
			showUpdateForm(request, response);
			break;
		case "updateUser":
			updateUser(request, response);
			break;
		/*case "showHomePage":
			showHomePage(request, response);
			break;*/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
/*	protected void showHomePage(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		
		Object username = request.getSession().getAttribute("username");
		//String password = request.getParameter("password");
		//System.out.println("username: " +username + " password: " +password);
		LoginUser currentUser = loginDao.getCurrentUser((String)username);
		String userType = currentUser.getUserType();
		//request.setAttribute("currentUser", currentUser);
		request.setAttribute("userType", userType);
		System.out.println("Current userType: " + userType);
		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\Home.jsp");
		dispatcher.forward(request, response);
	}*/
	
	private void showUpdateForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		LoginUser user = loginDao.getUserById(userId);
		request.setAttribute("user", user);

		RequestDispatcher dispatcher = 
				request.getRequestDispatcher("\\WEB-INF\\view\\UpdateUserForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void updateUser(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {

		int userId = Integer.parseInt(request.getParameter("userId"));
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType= request.getParameter("userType");
		//int empId = Integer.parseInt(request.getParameter("empId"));
		//Employee employee = new DoctorDAO().getDoctorById(empId);
	
		//LoginUser userToUpdate =  new LoginUser(userId,username, password, userType, employee);
		//loginDao.updateUser(userToUpdate);
		response.sendRedirect("LoginServlet?action=viewSuperUserPage");
	}
	
	private void logOut(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		request.getSession().invalidate();
		response.sendRedirect("HomeServlet?action=showHomePage");
	}
	
	protected void deleteUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int userId = Integer.parseInt(request.getParameter("userId"));
		loginDao.deleteUser(userId);
		response.sendRedirect("LoginServlet?action=viewSuperUserPage");
	}
	
	protected void showSuperUserPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		List<LoginUser> listOfUsers = new ArrayList<>();
		listOfUsers = loginDao.getAllUsers();
		Object username = request.getSession().getAttribute("username");
		LoginUser currentUser = loginDao.getCurrentUser((String)username);
		String userType = currentUser.getUserType();
		
		request.setAttribute("userType", userType);
		request.setAttribute("listOfUsers", listOfUsers);
		request.getRequestDispatcher("\\WEB-INF\\view\\superUserPage.jsp")
		.forward(request, response);
	}
	
	protected void showNewUserForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		/*HttpSession session = request.getSession();
		session.setAttribute("username", null);*/
		
		request.getRequestDispatcher("\\WEB-INF\\view\\newUserForm.jsp")
		.forward(request, response);	
	}
	

	protected void showLoginForm(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		System.out.println("username: "+ request.getParameter("username"));
		request.getRequestDispatcher("\\WEB-INF\\view\\LoginForm.jsp")
		.forward(request, response);
	}
	
	protected void insertNewUser(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String username = request.getParameter("newUserName");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		int empId = Integer.parseInt(request.getParameter("empId"));
		Employee employee = new DoctorDAO().getDoctorById(empId);
		LoginUser user = new LoginUser(username, password, userType, employee);
		System.out.println("new user to insert: ");
		
		loginDao.insertUser(user);
		response.sendRedirect("LoginServlet?action=viewSuperUserPage");
	}

	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		System.out.printf("Validating %s and %s\n", username, password);
		if (loginDao.validateUser(username, password)) {
			LoginUser currentuser = loginDao.getCurrentUser(username);
			String userType = currentuser.getUserType();
			request.setAttribute("userType", userType);
			System.out.println("Usertype: " + userType);
			request.getSession().setAttribute("username", username);
			System.out.println();
				if(userType.equals("superUser")) {
					response.sendRedirect("LoginServlet?action=viewSuperUserPage");
				}
				else {
					response.sendRedirect("HomeServlet?action=showHomePage");
				}
		} else {
			request.setAttribute("message", "Incorrect username and password combination, please try again.");
			request.getRequestDispatcher("WEB-INF/view/LoginForm.jsp").forward(request, response);
		}
	}
	
}
