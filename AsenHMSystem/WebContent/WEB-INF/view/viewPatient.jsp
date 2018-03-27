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
viewPatient JSP
<body>
	<h2>Patient</h2>

	<table border = "1">
		<tr>
			<th>Id</th>
			<th>First name</th>
			<th>Last name</th>
			<th>Date Of Birth</th>
			<th>Gender</th>
			<th>Address</th>
			<th>Phone</th>
			<th>Next Of Kin</th>
			<th>Delete</th>
			
			<!-- <th>Admission Date</th>					
			<th>Discharge Date</th>
			<th>Appointment Date</th>
			<th>Bed Id</th>
			<th>Department Id</th>
			<th>Doctor Id</th> -->
		</tr>
		<c:forEach var="patient" items = "${listOfPatient}" varStatus="status">
		<tr>
			<td>${patient.patientId}</td>
			<td>${patient.foreName}</td>
			<td>${patient.surName}</td>
			<td>${patient.dateOfBirth}</td>
			<td>${patient.gender}</td>
			<td>${patient.address}</td>
			<td>${patient.phoneNumber}</td>
			<td>${patient.nextOfKin}</td>
			<td><a href="PatientServlet?action=deletPatient&patientId=${patient.patientId}">Delete</a></td>
			
			<%-- <td>${patient.admissionDate}</td>			
			<td>${patient.dischargeDate}</td>
			<td>${patient.appointmentDate}</td>
			<td>${patient.bed.bedId}</td>
			<td>${patient.department.deptId}</td>
			<td>${patient.doctor.doctorId}</td> --%>
		</tr>
		</c:forEach>
	</table>
</body>
</html>