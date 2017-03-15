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
<script type="text/javascript">
$(document).ready(function(){
	$('#datetimepicker').datetimepicker({
		dateFormat: 'yy-mm-dd'
	});
	$("button:submit").button();
});
</script>
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
		<input type="text" name="date" id="datetimepicker" required>
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