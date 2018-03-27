<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Doctor</title>
</head>
<body>
	<form action = "DoctorServlet?action=updateDoctor" method ="Post">
		<p>Doctor</p>
		<input type="hidden" name="doctorId" value="${doctor.empId}"/>
		<p>First Name</p>
		<p><input type="text" name="firstName" value="${doctor.firstName}"/></p>
		<p>Last Name</p>
		<p><input type="text" name="lastName" value="${doctor.lastName}"/></p>
		<p>	
		Gender	<br/>	
			<select name = "gender"> 
	  		<option value="false">Male</option>
	  		<option value="true">Female</option>
			</select> 
		</p>
		<p>Address</p>
		<p><input type="text" name="address" value="${doctor.address}"/></p>
		<p>Phone Number</p>
		<p><input type="text" name="phoneNumber" value="${doctor.phoneNumber}"/></p>
		<p>Qualifications</p>
		<p><input type="text" name="qualifications" value="${doctor.qualifications}"/></p>
		<p>Department</p>
		<p>				
		<select name = "departmentId">
  		<option value="1">Cardiology</option>
  		<option value="2">Physiotheraphy</option>
  		<option value="3">Rheumatology</option>
  		<option value="4">Opthhalmology</option>
		</select>
		</p>
		<p><input type="submit" value="Update"/></p>
	</form>
</body>
</html>