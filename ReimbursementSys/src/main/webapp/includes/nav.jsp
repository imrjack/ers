<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="session" value="${sessionScope}"/>
<div class='nav-wrapper'>
	<ul id="my-nav" class="list-inline">
		<li>
			<span>Reimbursement System</span>
			<c:if test="${session.first_name != null }"> 
			<c:out value = " - ${session.first_name} "/> 
			</c:if>
			<c:choose>
				<c:when test="${session.role == 'manager' }">
				(Manager)
				</c:when>
				<c:when test="${session.role == 'employee' }">
				(Employee)
				</c:when>
			</c:choose>		
		</li>
		<ul class='list-inline pull-right nav-style'>
			<c:if test="${session.first_name != null }">
				<c:choose>	
					<c:when test="${session.role == 'manager'}">
						<c:set var="get_status" value="${params['status']}"/>
						<li>
						<form id='status-get' action = '${pageContext.request.contextPath}/all_reimbursements.do'>
						<span>Status: </span>
						<Select id='status-filter' style='background:#000;' name='status'>
							<option value="1">Pending</option>
							<option value="2">Approved</option>
							<option value="3">Denied</option>
							<option value="0">All</option>
						</Select>
						</form>
						</li>
						<!-- <li><a href="all_reimbursements.do">View Reimbursements</a></li>	-->	
					</c:when>
					<c:when test="${session.role=='employee'}">
						<li>
						<form id='status-get' action = '${pageContext.request.contextPath}/emp_reimbursements.do'>
						<span>Status: </span>
						<Select id='status-filter' style='background:#000;' name='status'>
							<option value="1">Pending</option>
							<option value="2">Approved</option>
							<option value="3">Denied</option>
							<option value="0">All</option>
						</Select>
						</form>						
						</li>
						<li id='show-form'><a href='#'>Add Reimbursement</a></li>
					</c:when>
				</c:choose>
					<li><a href='${pageContext.request.contextPath}/logout.do'>Logout</a></li>
			</c:if>
		</ul>
	</ul> 
</div>
<script>
  $('#status-filter').on('change',function(){
	$('#status-get').submit();
  });	
  
   var optionValue = new URLSearchParams(window.location.search).get("status")
	if(optionValue != null){
		$("#status-filter").val(optionValue)
		.find("option[value=" + optionValue +"]").attr('selected', true);
		
	}else{
		$('#status-filter').find("option[value=1]").attr('selected', true);
	} 
</script>