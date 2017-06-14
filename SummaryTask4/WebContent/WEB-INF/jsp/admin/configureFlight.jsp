<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="ConfigureFlight" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<script type="text/javascript">
$(document).ready(function(){
	$('#datetimepicker').datetimepicker({
		dateFormat: 'yy-mm-dd'
	});
	$('#datetimepicker').timepicker('setTime', new Date('${flight.departureDate}'));
	$("button:submit").button();
});
</script>
<form method="post" action="controller">
	<input type="hidden" name="command" value="configureFlight">
	<input type="hidden" name="flightId" value="${flight.id}">
	<fieldset>
		<legend><fmt:message key="Origin"/></legend>
		<input name="origin" value="${flight.origin}" required><br/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="Destination"/></legend>
		<input name="destination" value="${flight.destination}" required><br/>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="DepartureDate"/></legend>
		<input type="text" name="date" id="datetimepicker" required>
	</fieldset>
	<fieldset>
		<legend><fmt:message key="Status"/></legend>
		<select name="statuses">
		<c:forEach begin="0" end="${flightStatuses-1}" step="1" var="index">
         <c:choose>
         <c:when test="${flight.flightStatusId==index}">
         	<option selected value="${index}"><ex:fstatus statusId="${index}"/></option>
         </c:when>
         <c:otherwise>
         	<option value="${index}"><ex:fstatus statusId="${index}"/></option>
         </c:otherwise>
         </c:choose>
     </c:forEach> 
		</select>
	</fieldset>
	<button type="submit"><fmt:message key="Update"/></button>
	</form>
		</fmt:bundle>
</body>
</html>