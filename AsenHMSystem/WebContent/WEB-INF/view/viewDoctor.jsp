<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View All Doctors</title>
</head>
<body>
viewDoctor JSP<br><br>
	<c:if test="${username != null}">Username:  ${username}. Usertype: ${userType}&nbsp;&nbsp;
		<a href="LoginServlet?action=logOut">Log Out</a>
	</c:if>
	<h2>Doctors</h2>
	
	<a href="DoctorServlet?action=Cardiology&departmentId=1">Cardiology</a> <br/>
	<a href="DoctorServlet?action=Physiotheraphy&departmentId=2">Physiotheraphy</a> <br/>
	<a href="DoctorServlet?action=Rheumatology&departmentId=3">Rheumatology</a> <br/>
	<a href="DoctorServlet?action=Opthhalmology&departmentId=4">Opthhalmology</a> <br/>
	<a href ="DoctorServlet?action=viewAll">All Departments</a> 
	<table border = "1">
		<tr>
			<th>Id</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Phone Number</th>
			<th>Qualifications</th>
			<th>Department</th>
			<th>Update</th>
			<th>Delete</th>
			<th>Assign a Visit</th>
			<th>Manage appointments</th>
		</tr>
	
		<c:forEach var="doctor" items = "${listOfDoctors}" varStatus="status">
			<tr>
				<td>${doctor.empId}</td>
				<td>${doctor.firstName}</td>
				<td>${doctor.lastName}</td>	
				<td>
				<c:choose>										
				<c:when test="${doctor.gender==true}">Female
				</c:when>
				<c:otherwise>Male</c:otherwise>									
				</c:choose>	
				</td>				
				<td>${doctor.address}</td>
				<td>${doctor.phoneNumber}</td>
				<td>${doctor.qualifications}</td>
				<td>${doctor.department.departmentName}</td>
				<td><a href="DoctorServlet?action=showUpdateDoctorForm&doctorId=${doctor.empId}">Update</a></td>
				<td><a href="DoctorServlet?action=delete&doctorId=${doctor.empId}">Delete</a></td>
				<td><a href="PatientServlet?action=showAdmissionForm&doctorId=${doctor.empId}">Select</a></td>
				<td><a href="HomeServlet?action=manageAppointmets&empId=${doctor.empId}">Appointments</a></td>
			</tr>
	</c:forEach>
	</table>
	
	<p>		
	<a href="DoctorServlet?action=showInsertDoctorForm">Insert New Doctor</a> <br>
	<p>Search Doctor</p>
	<p>${message}</p>
	<p>${message1}</p>
	<form action="DoctorServlet?action=searchDoctor" method="Post">
		<p>
		<select name = "searchType">		
  		<option value="id">Search by id</option>
  		<option value="lastName">Search by name</option>
		</select>
		<input type="text" name="searchDoctor" required/>
		<input type="submit" value="Search"/>
		</p>
	</form>
</body>
</html>