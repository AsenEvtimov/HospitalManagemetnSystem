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
RoomLayOut JSP
	<h2>Rooms Layout</h2>
	
	<table border = "1">
		<tr>
			<th>RoomId</th>
			<th>Ward</th>
			<th>Room Type</th>
			<th>Room Price</th>
		</tr>
	
		<c:forEach var="room" items = "${listOfRooms}" varStatus="status">
		<tr>
			<td>${room.roomId}</td>
			<td><a href="BedServlet?action=showBedsInWard&wardId=${room.ward.wardId}">${room.ward.name}</a></td>
			<td>${room.roomCost.roomType}</td>
			<td>${room.roomCost.cost}</td>
		</tr>
		</c:forEach>
	</table>
	
</body>
</html>