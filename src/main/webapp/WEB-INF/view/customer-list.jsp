<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Customer list</title>
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/style.css" />
</head>
<body>
	<div id="wrapper">
		<div id="header">
			<h2>
				CRM - Customer relationship manager <br> User :
				<security:authentication property="principal.username" />
				<br> Role :
				<security:authentication property="principal.authorities" />
			</h2>
		</div>
	</div>
	<br>
	<security:authorize access="hasRole('EMPLOYEE')">
		<div>
			<input type="button" value="Add Customer" class="button1"
				onClick="window.location.href='addCustomer'; return false;" />
		</div>
	</security:authorize>

	<br>

	<div id="content">
		<table class="display">
			<tr>
				<th>First name</th>
				<th>Last name</th>
				<th>Email</th>
				<security:authorize access="hasRole('EMPLOYEE')">
					<th>Action</th>
				</security:authorize>
			</tr>

			<c:forEach var="temp" items="${customer}">
				<security:authorize access="hasRole('EMPLOYEE')">
					<c:url var="updateLink" value="/customer/updateForm">
						<c:param name="customerId" value="${temp.id}" />
					</c:url>
				</security:authorize>
				<security:authorize access="hasRole('ADMIN')">
					<c:url var="deleteLink" value="/customer/delete">
						<c:param name="customerId" value="${temp.id}" />
					</c:url>

					<c:url var="addCustomerDetailLink"
						value="/customer/addCustomerDetails">
						<c:param name="customerId" value="${temp.id}" />
					</c:url>
				</security:authorize>
				<tr>
					<td>${temp.firstName}</td>
					<td>${temp.lastName}</td>
					<td>${temp.email}</td>
					<security:authorize access="hasRole('EMPLOYEE')">
						<td><a href="${updateLink}"> Update</a> <security:authorize
								access="hasRole('ADMIN')"> |
								<a href="${deleteLink}"
									onClick="if(!(confirm('Are you sure you want to delete?'))) return false">
									Delete</a> | <a href="${addCustomerDetailLink}"> Add/update
									Customer Details</a>
							</security:authorize></td>
					</security:authorize>
				</tr>
			</c:forEach>
		</table>
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