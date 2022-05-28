<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<c:url value="/node_modules/bootstrap/dist/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />

<c:url value="/css/login_register.css"
	var="css" />
<link href="${css}" rel="stylesheet" />

<c:url value="/js/index.js" var="js" />
<script src="${js}"></script>

<meta charset="ISO-8859-1">
<title>Insert title here</title>

<link>
</head>
<body>
	hello
	<button class="btn btn-primary">click me</button>
</body>
</html>