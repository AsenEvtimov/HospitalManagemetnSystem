<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home page</title>
</head>
Home JSP
<h2>Home Page</h2>

<body>
	
	<c:if test="${username != null}">Username:  ${username}. Usertype: ${userType}&nbsp;&nbsp;
		<a href="LoginServlet?action=logOut">Log Out</a>
	</c:if>
	
	<p><a href="">Generate Reports</a></p>
	
	<c:if test = "${userType=='doctor'}">
		<p><a href="">Test Results</a></p>
		<p><a href="">Write Prescription</a></p>
		<%-- <p><a href="HomeServlet?action=viewAppointmets&username=${username}">View Appointments</a></p> --%>		
	</c:if>
	
	<p><c:if test ="${userType=='administrator'}">
		<p><a href="DoctorServlet?action=viewAll">Doctor Details</a></p>
		<p><a href="PatientServlet?action=showRegisterPatientForm">Register Patient</a></p>
		<p><a href="">Generate Bills</a></p>
	</c:if>
	
	<p><a href="BedServlet?action=bedLayout">Beds Layout</a></p>
	
	<form action="HomeServlet?action=viewAppointmets&empId=${empId}" method="Post">
		<c:if test = "${userType=='doctor'}">		
		<p><input type="submit" value="View My Appointments"/></p>
		</c:if>
		<c:if test = "${not empty listOfVisit}">				
			<table border="1">
					<tr>
						<th>Visit Id</th>
						<th>Admission Date</th>
						<th>Appointment Date</th>
						<th>Discharge Date</th>
						<th>Is Outpatient</th>
						<th>Room No</th>
						<th>Bed No</th>
						<th>Doctor</th>
						<th>Patient</th>
					</tr>
					<c:forEach var="visit" items="${listOfVisit}" varStatus="status">
						<tr>
							<td>${visit.visitId}</td>
							<td>${visit.admissionDate}</td>
							<td>${visit.appointmentDate}</td>
							<td>${visit.dischargeDate}</td>
							<td>${visit.isOutpatient}</td>
							<td>${visit.bed.room.roomId}</td>
							<td>${visit.bed.bedId}</td>
							<td>Dr. ${visit.doctor.lastName}</td>
							<td>${visit.patient.foreName} ${visit.patient.surName}</td>
						</tr>
					</c:forEach>
				</table>
			</c:if>
			<c:if test = "${empty listOfVisit}">
			 ${message}	
			</c:if>
			
		</form>	
</body>
</html>