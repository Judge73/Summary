<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<c:set var="title" value="RequestForm" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
	<form method="post" action="controller" class="mTop">
		<input type="hidden" name="command" value="writeRequest"/>
		<h4><fmt:message key="Header"/></h4>
		<input name="header" required>
		<h4><fmt:message key="Description"/></h4>
		<textarea name="description" cols="40" rows="5"></textarea><br/>
		<button type = "submit"><fmt:message key="Send"/></button>
	</form>
	</fmt:bundle>
</body>
</html>