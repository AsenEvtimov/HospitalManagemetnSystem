<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert New Doctor</title>
</head>
<body>
	<form action = "DoctorServlet?action=insertNewtDoctor" method ="Post">
		<p>Doctor</p>
		<p>First Name</p>
		<p><input type="text" name="firstName" placeholder="First Name" required/></p>
		<p>Last Name</p>
		<p><input type="text" name="lastName" placeholder="Last Name" required/></p>
		<p>Gender</p>
		<p>				
		<select name = "gender">
  		<option value="false">Male</option>
  		<option value="true">Female</option>
		</select>
		</p>
		<p>Address</p>
		<p><input type="text" name="address" placeholder="Address" required/></p>
		<p>Phone Number</p>
		<p><input type="text" name="phoneNumber" placeholder="Phone Number"/></p>
		<p>Qualifications</p>
		<p><input type="text" name="qualifications" placeholder="Qualification"/></p>
		<p>Department</p>
		<p>				
		<select name = "departmentId">
  		<option value="1">Cardiology</option>
  		<option value="2">Physiotheraphy</option>
  		<option value="3">Rheumatology</option>
  		<option value="4">Opthhalmology</option>
		</select>
		</p>
		<p><input type="submit" value="Insert"/></p>
	</form>
</body>
</html>