<%@ include file="/WEB-INF/jspf/directive/page.jspf" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="style.css" rel="style">
<c:set var="title" value="DispatcherView" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<script type="text/javascript">
Number.prototype.padLeft = function(base,chr){
    var  len = (String(base || 10).length - String(this).length)+1;
    return len > 0? new Array(len).join(chr || '0')+this : this;
}
var str = '${jsonStatuses}';
var statuses = $.parseJSON('[' + str + ']');
	$(document).ready(function(){
		(function(){
			$.get('sortFlights', "command=sortFlights&sort=Id", function(data){
	    		$.each(data, function(i, item){
	    			var d = new Date(item.departureDate),
	    			dformat = [(d.getMonth()+1).padLeft(),
	        		               d.getDate().padLeft(),
	        		               d.getFullYear()].join('/')+' '+
	        		              [d.getHours().padLeft(),
	        		               d.getMinutes().padLeft(),
	        		               d.getSeconds().padLeft()].join(':'),
	        		statusName = statuses[0][item.flightStatusId];
	    			$tr = $('<tr class="row">').append(
	    				$('<td>').text(item.id),
	    				$('<td>').text(item.origin),
	    				$('<td>').text(item.destination),
	    				$('<td>').text(dformat),
	    				$('<td>').text(statusName),
	    				$('<td>').append('<input type="radio" name="flightId" value="' + item.id +'">')
	    				);
	    				$("#flightsTable").append($tr);
	        	})
		})
		})()
		$(".sortButton").click(updateTable);
	});
    function updateTable(event){
    	event.preventDefault();
    	var sort = event.target.value;
    	$.get('sortFlights', "command=sortFlights&sort=" + sort, function(data){
    		var trs = document.getElementsByClassName("row");
    		$.each(data, function(i, item){
    			console.log(i);
    			var tds = trs[i].childNodes;
    			tds[0].innerHTML = item.id;
    			tds[1].innerHTML = item.origin;
    			tds[2].innerHTML = item.destination;
    			var d = new Date(item.departureDate),
    			dformat = [(d.getMonth()+1).padLeft(),
    		               d.getDate().padLeft(),
    		               d.getFullYear()].join('/')+' '+
    		              [d.getHours().padLeft(),
    		               d.getMinutes().padLeft(),
    		               d.getSeconds().padLeft()].join(':');
    			tds[3].innerHTML = dformat;
    			tds[4].innerHTML = statuses[0][item.flightStatusId];
    			tds[5].children[0].value = item.id.toString();
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
	<div class="successDiv">
	<c:if test="${result != null}">
  		<c:out value="${result}"/>
	</c:if>
	</div>
	<form method="post" action="controller">
	<div id="flightsDiv">
	<fmt:message key="FormCrew"/>
	<table id="flightsTable">
		<tr>
			<th><button class="sortButton" value="Id">Id</button></th>
			<th><button class="sortButton" value="Origin"><fmt:message key="Origin"/></button></th>
			<th><button class="sortButton" value="Destination"><fmt:message key="Destination"/></button></th>
			<th><button class="sortButton" value="Date"><fmt:message key="Date"/></button></th>
			<th><button class="sortButton" value="Status"><fmt:message key="Status"/></button></th>
			<th/>
		</tr>
	</table>
	</div>
	<div id="controlDiv">
		<div>
		<button name="command" value="formCrew"><fmt:message key="Form"/></button><br/>
		<label class="mAuto"><fmt:message key="SetStatus"/></label><br/>
		<select name="statuses" class="mAuto">
			<c:forEach begin="0" end="${statuses-1}" step="1" var="index">
    	    	<option value="${index}"><ex:fstatus statusId="${index}"/></option>
   		</c:forEach> 
		</select><br/>
		<button type="submit" name="command" value="setStatus" class="mAuto"><fmt:message key="Set"/></button>
	</div>
	<table>
		<tr>
			<td><fmt:message key="Pilots"/></td>
			<td><select name="pilots">
			<c:forEach items="${pilots}" var="pilot">
				<option value="${pilot.id}">${pilot.firstName} ${pilot.lastName}</option>
			</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><fmt:message key="Navigators"/></td>
			<td><select name="navigators">
			<c:forEach items="${navigators}" var="navigator">
				<option value="${navigator.id}">${navigator.firstName} ${navigator.lastName}</option>
			</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><fmt:message key="Operators"/></td>
			<td><select name="operators">
			<c:forEach items="${operators}" var="operator">
				<option value="${operator.id}">${operator.firstName} ${operator.lastName}</option>
			</c:forEach>
			</select></td>
		</tr>
		<tr>
			<td><fmt:message key="Stewardessess"/></td>
			<td><select name="stewardessess">
			<c:forEach items="${stewardessess}" var="stewardess">
				<option value="${stewardess.id}">${stewardess.firstName} ${stewardess.lastName}</option>
			</c:forEach>
			</select></td>
		</tr>
	</table>
	</div>
	</form>
	</fmt:bundle>
</body>
</html>