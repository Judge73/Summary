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
<form method="post" action="controller" class="inline float">
	<input type="hidden" name="command" value="flightsStatuses"/>
	<table>
		<tr>
			<td><fmt:message key="Status"/></td>
			<td><button name="sortType" value="${sortAsc}"><fmt:message key="FlightsAmount"/></button></td>
		</tr>
		<c:forEach var="entry" items="${statMap}">
		<tr>
			<td><c:out value="${entry.key}"/></td>
			<td><c:out value="${entry.value}"/></td>
		</tr>
		</c:forEach>
	</table>
	</form>
</fmt:bundle>
</body>
</html>