<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>New Patient</title>
</head>
<body>
	NewPatientForm JSP
	<h2>Insert New Patient Details</h2>
	
		<form action = "PatientServlet?action=insertNewPatient" method ="Post">
		<h4>Patient</h4>

		<p>First Name</p>
		<p><input type="text" name="firstName"/></p>
		<p>Last Name</p>
		<p><input type="text" name="lastName"/></p>
		<p>	
		<p>Date Of Birth :</p>
			<p><input type="date" name="dob"></p>
			<p>Gender :</p>
			<p><input type="text" name="gender"></p>
			<p>Address :</p>
			<p><input type="text" name="address"></p>
			<p>Phone Number :</p>
			<p><input type="text" name="phone"></p>
			<p>Next Of Kin :</p>
			<p><input type="text" name="kin"></p>
			
			<p><input type="submit" value="Register"/></p>

	</form>
	
</body>
</html>