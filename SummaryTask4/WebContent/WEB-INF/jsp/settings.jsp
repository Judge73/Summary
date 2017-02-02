<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<html>

<c:set var="title" value="Settings" scope="page" />
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
	<table id="main-container">

		<%@ include file="/WEB-INF/jspf/header.jspf" %>

		<tr>
			<td class="content">
				<%-- CONTENT --%>

				<form id="settings_form" action="controller" method="post">
					<input type="hidden" name="command" value="updateSettings" />

					<div>
						<p><fmt:message key="Language"></fmt:message></p>
						<select name="locale">
							<option value="ru"><fmt:message key="Russian"/></option>
							<option value="en"><fmt:message key="English"></fmt:message></option>
						</select>
					</div>
					<div>
						<label>
							<c:choose>
							<c:when test="${sessionScope.mailNotification == true}">
								<input type="checkbox" name="mailNotification" value="true" checked>
							</c:when>
							<c:otherwise>
							<input type="checkbox" name="mailNotification" value="true">
							</c:otherwise>
							</c:choose>
							<fmt:message key="Notifications"/>
						</label>
					</div>
					
					<button type="submit"><fmt:message key="Update"/></button>
				</form> 
				
				<%-- CONTENT --%>
			</td>
		</tr>

	</table>
	</fmt:bundle>
</body>
</html>