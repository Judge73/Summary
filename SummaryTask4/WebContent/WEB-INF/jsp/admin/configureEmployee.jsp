<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="ConfigureEmployee" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<form method="post" action="controller">
	<input type="hidden" name="command" value="configureEmployee">
	<input type="hidden" name="employeeId" value="${emp.id}">
	<table>
	<tr>
		<td><fmt:message key="FirstName"/></td>
		<td><input name="firstName" value="${emp.firstName}"></td>
	</tr>
	<tr>
	<tr>
		<td><fmt:message key="LastName"/></td>
		<td><input name="lastName" value="${emp.lastName}"></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input name="email" value="${emp.email}"></td>
	</tr>
	<tr>
		<td><fmt:message key="Job"/></td>
		<td><select name="job">
			<c:forEach begin="0" end="${jobs-1}" step="1" var="index">
        	<c:choose>
        	<c:when test="${emp.jobId==index}">
        		<option selected value="${index}"><ex:job jobId="${index}"/></option>
        	</c:when>
        	<c:otherwise>
        		<option value="${index}"><ex:job jobId="${index}"/></option>
        	</c:otherwise>
        	</c:choose>
     	</c:forEach> 
		</select></td>
	</tr>
	</table>
	<button type="submit"><fmt:message key="Update"/></button>
	</form>
	</fmt:bundle>
</body>
</html>