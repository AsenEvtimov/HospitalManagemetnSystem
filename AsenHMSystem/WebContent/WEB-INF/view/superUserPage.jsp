<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>SuperUser</title>

</head>
<body>

	<c:if test="${username != null}">Username:  ${username}. Usertype: ${userType}&nbsp;&nbsp;
		<a href="LoginServlet?action=logOut">Log Out</a>
	</c:if>
	<p>
	


	<h2>All User Login Details</h2>
	<table border="1">
		<tr>
			<th>Id</th>
			<th>UserName</th>
			<th>Password</th>
			<th>UserType</th>
			<th>Update</th>
			<th>Delete</th>
		</tr>
		<c:forEach var="user" items="${listOfUsers}" varStatus="status">
			<tr>
				<td>${user.id}</td>
				<td>${user.username}</td>
				<td>${user.password}</td>
				<td>${user.userType}</td>
				<td><a
					href="LoginServlet?action=showUpdateForm&userId=${user.id}">Update</a></td>
				<td><a href="LoginServlet?action=delete&userId=${user.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>

	<a href="LoginServlet?action=showCreateUserForm">Create New User</a>

</body>
</html>