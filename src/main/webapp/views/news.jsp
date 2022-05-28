<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>News</title>
</head>
<body>
	<h2>News page</h2>
	<p>hello ${userInfo.user_name}</p>
	<a href="/hexa/logout"><input type="button"
		value="Logout"></a>
</body>
</html>