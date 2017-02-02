<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="AddFlight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<form method="post" action="controller">
	<input type="hidden" name="command" value="addFlight">
	<fieldset>
		<legend><fmt:message key="Origin"/></legend>
		<input name="origin" required><br/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="Destination"/></legend>
		<input name="destination" required><br/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="DepartureDate"/></legend>
		<input name="year" maxlength="4" size="4" placeholder="yyyy" class="float" required>
		<input name="month" maxlength="2" size="2" placeholder="mo" class="float" required>
		<input name="day" maxlength="2" size="2" placeholder="dd" class="float" required>
		<input name="hour" maxlength="2" size="2" placeholder="hh" class="float" required>:
		<input name="minute" maxlength="2" size="2" placeholder="mi" class="float" required>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="Status"/></legend>
		<select name="statuses">
			<c:forEach begin="0" end="${flightStatuses-1}" step="1" var="index">
         <option value="${index}"><ex:fstatus statusId="${index}"/></option>
     </c:forEach> 
		</select>
	</fieldset>
	<button type="submit"><fmt:message key="Add"/></button>
	</form>
	</fmt:bundle>
</body>
</html>