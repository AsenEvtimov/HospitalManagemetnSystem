<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

</head>
<body>
LoginForm JSP
	<form action="LoginServlet?action=login" method="Post">
	<h2>Login</h2>
	<p>
	User Name<br></br>
	<input type="text" name="username" placeholder="Type username" required/>
	<br></br>Password<br></br>
	<input type="text" name="password" placeholder="Type password" required/>
	<!-- <br></br>
	<a href="LoginServlet?action=newUserForm">Create New User</a> -->
	<br></br>	
	<input type="submit" value="Login"/>	
	</p>
	${message}
	</form>	
</body>
</html>