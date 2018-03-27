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
WardsLayout JSP
	<h2>Wards Layout</h2>
	
	<table border = "1">
		<tr>
			<th>DeptId</th>
			<th>Department</th>
			<th>WardId</th>
			<th>Ward</th>
		</tr>
	
		<c:forEach var="ward" items = "${listOfWards}" varStatus="status">
		<tr>
			<td>${ward.department.deptId}</td>
			<td>${ward.department.departmentName}</td>
			<td>${ward.wardId}</td>
			<td><a href="BedServlet?action=showRoomsInWard&wardId=${ward.wardId}">${ward.name}</a></td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>