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
	<div class="mTop">
	<form method="post" action="controller" class="inline float">
	<input type="hidden" name="command" value="sortFlight"/>
	<table>
	<tr>
		<td class="fh"><button class="button sortButton" name="sortFlight" value="Id">Id</button></td>
		<td class="fh"><button class="button sortButton" name="sortFlight" value="Origin"><fmt:message key="Origin"/></button></td>
		<td class="fh"><button class="button sortButton" name="sortFlight" value="Destination"><fmt:message key="Destination"/></button></td>
		<td class="fh"><button class="button sortButton" name="sortFlight" value="Date"><fmt:message key="Date"/></button></td>
		<td class="fh"><fmt:message key="Status"/></td>
	</tr>
		<c:forEach items="${flights}" var="flight">
			<tr>
				<td class="fh">${flight.id}</td>
				<td class="fh">${flight.origin}</td>
				<td class="fh">${flight.destination}</td>
				<td class="fh">${flight.departureDate}"</td>
				<td class="fh"><ex:fstatus statusId="${flight.statusId}"/></td>
				</tr>
		</c:forEach>
	</table>
	</form>
	</div>
	<form method="post" action="controller" class="inline">
	<table class="float">
		<tr><td class="fh"><fmt:message key="Select"/></td></tr>
		<c:forEach items="${flights}" var="flight">
			<tr><td class="fh"><input type="radio" name="flightId" value="${flight.id}"></td></tr>
		</c:forEach>
	</table>
	<div class="controlDiv">
		<button name="command" value="removeFlight"><fmt:message key="Remove"/></button><br/>
		<button name="command" value="configureFlightPage"><fmt:message key="Configure"/></button><br/>
 		<button name="command" value="addFlightPage"><fmt:message key="Add"/></button><br/>
 		<button name="command" value="freeCrew"><fmt:message key="FreeCrew"/></button><br/>
 	</div>
 	</form>
	</fmt:bundle>
</body>
</html>