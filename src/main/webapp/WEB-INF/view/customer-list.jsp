<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer list</title>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style.css"/>
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>CRM - Customer relationship manager</h2>
		</div>
	</div>

	<div id="container">
		<div id="content">
			<table>
				<tr>
					<th>First name</th>
					<th>Last name</th>
					<th>Email</th>
				</tr>
				<c:forEach var="temp" items="${customer}">
				<tr>
					<td>${temp.firstName}</td>
					<td>${temp.lastName}</td>
					<td>${temp.email}</td>
				</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>