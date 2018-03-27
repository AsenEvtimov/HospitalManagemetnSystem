<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Beds Layout</title>
</head>
<body>
BedsLayout JSP
	<h2>Beds Layout in ${wardName} Ward</h2>
	
		<table border = "1">
		<tr>
			<th>BedId</th>
			<th>Ward</th>
			<th>Room Type</th>
			<th>Vacant</th>
			<th>Bed Price</th>
			<th>Assign Bed</th>
		</tr>
	
		<c:forEach var="bed" items = "${listOfBeds}" varStatus="status">
		<tr>
			<td>${bed.bedId}</td>
			<td>${bed.room.ward.name}</td>
			<td>${bed.room.roomCost.roomType}</td>			
			<td>${bed.vacant}</td>
			<td>${bed.room.roomCost.cost}</td>
			<td><a href="PatientServlet?action=showAdmissionForm&bedId=${bed.bedId}">Select</a></td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>