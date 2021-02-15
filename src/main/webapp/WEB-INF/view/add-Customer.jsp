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
				CRM - Add Customer<br> User :
				<security:authentication property="principal.username" />
				<br> Role :
				<security:authentication property="principal.authorities" />
			</h2>
		</div>
	</div>
	<br>

	<br>
	<div id="container">

		<form:form action="saveCustomer" modelAttribute="customer"
			method="POST">
			<form:hidden path="id" />
			<table>
				<tbody>
					<tr>
						<td><label>First Name :</label></td>
						<td><form:input path="firstName" /></td>
					</tr>
					<tr>
						<td><label> Last Name :</label></td>
						<td><form:input path="lastName" /></td>
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
	<br>
	<p>NOTE: Email will be automatically Generated based on First name
		and Last name provided above</p>
	<br>
</body>
</html>