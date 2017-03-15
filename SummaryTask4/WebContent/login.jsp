<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<head>
<c:set var="title" value="Login" scope="page"/>
</head>
<body>
	<form id="login_form" action="controller" method="post" class="center">

<%--=========================================================================== 
Hidden field. In the query it will act as command=login.
The purpose of this to define the command name, which have to be executed 
after you submit current form. 
===========================================================================--%> 
					<input type="hidden" name="command" value="login"/>

					<fieldset>
						<legend align="center">Login</legend>
						<input name="login" required/><br/>
					</fieldset><br/>
					<fieldset>
						<legend align="center">Password</legend>
						<input type="password" name="password" required/>
					</fieldset><br/>
					
					<input type="submit" value="Login">								
				</form>
</body>
</html>