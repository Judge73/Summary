<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="AdminFlightView" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="successDiv">
	<c:if test="${result != null}">
  		<c:out value="${result}"/>
	</c:if>
	</div>
<form method="post" action="controller">
 	<table class="float">
	<tr>
		<td>Id</td>
		<td><fmt:message key="FirstName"/></td>
		<td><fmt:message key="LastName"/></td>
		<td><fmt:message key="Job"/></td>
		<td>Email</td>
		<td/>
	</tr>
		<c:forEach items="${employees}" var="emp">
			<tr>
				<td>${emp.id}</td>
				<td>${emp.firstName}</td>
				<td>${emp.lastName}</td>
				<td><ex:job jobId="${emp.jobId}"/></td>
				<td>${emp.email}</td>
				<td><input type="radio" name="empId" value="${emp.id}">
				</tr>
		</c:forEach>
	</table>
	<div class="controlDiv">
	<button name="command" value="removeEmployee"><fmt:message key="Remove"/></button><br/>
	<button name="command" value="configureEmployeePage"><fmt:message key="Configure"/></button><br/>
 	<button name="command" value="addEmployeePage"><fmt:message key="Add"/></button>
 	</div>
 	</form>
 	</fmt:bundle>
</body>
</html>