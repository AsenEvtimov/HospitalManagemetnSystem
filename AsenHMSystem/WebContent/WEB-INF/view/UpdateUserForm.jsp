<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Super User Update Accounts</title>
</head>
<body>
<h2>Updating ${user.username}</h2>
<form action="LoginServlet?action=updateUser" method="post">
<input type="hidden" name="userId"  value="${user.id}">
<p>UserName</p>
<input type="text" name="username" value="${user.username}" size="49">
<p>Password</p>
<input type="text" name="password" value="${user.password}" size="49">
<p>Retype Password</p>
<input type="text" name="retype" size="49">
<p>UserType</p>
<input type="text" name="userType" value="${user.userType}" size="49">
<p><input type="submit" value="Submit"/></p> 
</form>
</body>
</html>