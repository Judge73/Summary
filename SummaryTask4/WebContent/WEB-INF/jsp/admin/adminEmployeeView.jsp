<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="AdminFlightView" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<script type="text/javascript">
var str = '${jsonJobs}';
var jobs = $.parseJSON('[' + str + ']');
	$(document).ready(function(){
		(function(){
			$.get('sortEmployees', "command=sortEmployees&sort=Id", function(data){
	    		$.each(data, function(i, item){
	        		jobName = jobs[0][item.jobId];
	    			$tr = $('<tr class="row">').append(
	    				$('<td>').text(item.id),
	    				$('<td>').text(item.firstName),
	    				$('<td>').text(item.lastName),
	    				$('<td>').text(jobName),
	    				$('<td>').text(item.email),
	    				$('<td>').append('<input type="radio" name="empId" value="' + item.id +'">')
	    				);
	    				$("#employeeTable").append($tr);
	        	})
		})
		})()
		$(".sortButton").click(updateTable);
	});
    function updateTable(event){
    	event.preventDefault();
    	var sort = event.target.value;
    	$.get('sortEmployees', "command=sortEmployees&sort=" + sort, function(data){
    		var trs = document.getElementsByClassName("row");
    		$.each(data, function(i, item){
    			var tds = trs[i].childNodes;
    			tds[0].innerHTML = item.id;
    			tds[1].innerHTML = item.firstName;
    			tds[2].innerHTML = item.lastName;
    			tds[3].innerHTML = jobs[0][item.jobId];
    			tds[4].innerHTML = item.email;
    			console.log(item.id);
    			tds[5].children[0].value = item.id.toString;
        	})
    		})
    }
</script>
</head>
<body>
<c:if test="${param['locale'] != null}">
  <fmt:setLocale value="${param['locale']}" scope="session" />
</c:if>
<fmt:bundle basename="resources">
<%@ include file="/WEB-INF/jspf/header.jspf" %>
<div id="successDiv">
	<c:if test="${result != null}">
  		<c:out value="${result}"/>
	</c:if>
	</div>
 	<form method="post" action="controller" class="inline">
 	<table id="employeeTable">
	<tr>
		<th><button class="sortButton" value="Id">Id</button></th>
		<th><button class="sortButton" value="FirstName"><fmt:message key="FirstName"/></button></th>
		<th><button class="sortButton" value="LastName"><fmt:message key="LastName"/></button></th>
		<th><button class="sortButton" value="Job"><fmt:message key="Job"/></button></th>
		<th><button class="sortButton" value="Email">Email</button></th>
		<th/>
	</tr>
	</table>
	<div id="controlDiv">
		<button name="command" value="removeEmployee"><fmt:message key="Remove"/></button><br/>
		<button name="command" value="configureEmployeePage"><fmt:message key="Configure"/></button><br/>
 		<button name="command" value="addEmployeePage"><fmt:message key="Add"/></button>
 	</div>
	</form>
 	</fmt:bundle>
</body>
</html>