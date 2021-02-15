<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Customer</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style.css" />
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/add-customer-style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>
				CRM - Add Customer <br> User :
				<security:authentication property="principal.username" />
				<br> Role :
				<security:authentication property="principal.authorities" />
			</h2>
		</div>
	</div>

	<div id="container">

		<form:form action="saveCustomerDetail" modelAttribute="customerDetail"
			method="POST">
			<form:hidden path="id" />
			<form:hidden path="customer.id" />
			<form:hidden path="customer.firstName" />
			<form:hidden path="customer.lastName" />
			<form:hidden path="customer.email" />
			<table>
				<tbody>
					<tr>
						<td><label> Address :</label></td>
						<td><form:textarea path="address" /></td>
					</tr>
					<tr>
						<td><label> DOB :</label></td>
						<td><form:input path="dob" /></td>
					</tr>
					<tr>
						<td><label> <input type="submit" value="Save">
						</label></td>
					</tr>
				</tbody>
			</table>
		</form:form>
		<a href="${pageContext.request.contextPath}/customer/list">List
			all Customers</a>
	</div>
	<br>
	<div>
		<form:form action="${pageContext.request.contextPath}/logout"
			method="POST">
			<input type="submit" value="Logout" class="button2">
		</form:form>
	</div>
</body>
</html>