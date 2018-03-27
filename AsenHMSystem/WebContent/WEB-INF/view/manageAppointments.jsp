<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Manage Appointments</title>
</head>
<body>
manageAppointments JSP<br><br>

	<c:if test="${username != null}">Username:  ${username}. Usertype: ${userType}&nbsp;&nbsp;
		<a href="LoginServlet?action=logOut">Log Out</a>
	</c:if>

<p><a href="HomeServlet?action=showHomePage">Home</a></p>

<h2>Manage Appointmetns</h2>
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
			<th>Update</th>
			<th>Cancel</th>
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
			<td><a href="HomeServlet?action=showVisitForUpdate&visitId=${visit.visitId}">Update Visit</a></td>
			<td><a href="HomeServlet?action=deleteVisit&visitId=${visit.visitId}&empId=${visit.doctor.empId}">Cancel Visit</a></td>
	</tr>
	</c:forEach>
	</table>
	
</body>
</html>