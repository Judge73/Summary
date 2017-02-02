<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="AddEmployee" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<form method="post" action="controller">
	<input type="hidden" name="command" value="addEmployee">
	<table>
	<tr>
		<td><fmt:message key="FirstName"/></td>
		<td><input name="firstName" required></td>
	</tr>
	<tr>
		<td><fmt:message key="LastName"/></td>
		<td><input name="lastName" required></td>
	</tr>
	<tr>
		<td>Email</td>
		<td><input name="email" required></td>
	</tr>
	<tr>
		<td><fmt:message key="Job"/></td>
		<td><select name="job">
		<c:forEach begin="0" end="${jobs-1}" step="1" var="index">
         <option value="${index}"><ex:job jobId="${index}"/></option>
     </c:forEach></select></td> 
	</tr>
	</table>
	<button type="submit"><fmt:message key="Add"/></button>
	</form>
	</fmt:bundle>
</body>
</html>