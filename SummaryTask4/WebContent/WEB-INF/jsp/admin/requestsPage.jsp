<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="Requests" scope="page"/>
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
<div id="requestsForm">
<form method="post" action="controller">
	<input type="hidden" name="command" value="openRequest"/>
	<table>
	<tr>
		<td>Id</td>
		<td><fmt:message key="Header"/></td>
		<td><fmt:message key="FirstName"/></td>
		<td><fmt:message key="LastName"/></td>
		<td><fmt:message key="Selection"/></td>
	</tr>
	<c:forEach items="${requests}" var="request">
	<tr>
		<td>${request.id}</td>
		<td>${request.header}</td>
		<td>${request.firstName}</td>
		<td>${request.lastName}</td>
		<td><input type="radio" name="requestId" value="${request.id}">
	</tr>
	</c:forEach>
	</table>
	<button type="submit"><fmt:message key="Open"/></button>
	</form>
	</div>
</fmt:bundle>
</body>
</html>