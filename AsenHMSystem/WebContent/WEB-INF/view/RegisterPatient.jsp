<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register Patient</title>
</head>
RegisterPatient JSP
<h2>Register Patient</h2>

<body>
	
	<!--<p>Type of Patient</p>
	<select name="TypeOfPatient">
		<option value="inpatient">Inpatient</option>
		<option value="outpatient">Outpatient</option>
	</select>

	<p>
		<a href="">New Patient</a>
	</p> -->
	
	<p>Search Patient</p>
	
	<form action="PatientServlet?action=searchPatient" method="Post">
		<p>
			<select name="searchType">
				<option value="id">Search by id</option>
				<option value="surName">Search by name</option>
			</select> <input type="text" name="searchPatient" required /> <input
				type="submit" value="Search" />
		</p>
	</form>

	<c:if test="${action == 'searchPatient'}">
		<c:choose>
			<c:when test="${empty listOfPatients}">
				<a href="PatientServlet?action=showNewPatientForm">Register New Patient</a>
			</c:when>
			<c:otherwise>
				<table border="1">
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
					</tr>

					<c:forEach var="patient" items="${listOfPatients}"
						varStatus="status">
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
							<td><a href="PatientServlet?action=showAdmissionForm&patientId=${patient.patientId}">Register</a></td>
						</tr>
					</c:forEach>
				</table>
			</c:otherwise>
		</c:choose>
	</c:if>

	<p>
		<a href="PatientServlet?action=viewAllPatient">View All Patients</a>
	</p>
</body>

</html>