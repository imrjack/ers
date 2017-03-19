
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@include file="../includes/header.jsp" %>
 
 <!--  <div class='btn-wrapper'>
	 <button id='show-form' class='btn btn-primary'>
    	Add Reimbursement
  	<span class='glyphicon glyphicon-plus'></span>
  	</button>
 -->
 </div>
 <div id="reimb_form" class='form-wrapper'>
 	<div class='my-form'>
 	
 	<span id='remove-reimb-form' class='glyphicon glyphicon-remove pull-right'></span>
 	<h3>New Reimbursement</h3>
 	<form action='add_reimb.do' method='post'>
 		<div class='form-group'>
	 		<label>Amount</label>
	 		<input class="form-control" type="number"  step="0.01" name="amount"/>
 		</div>
 		<div class='form-group'>
 		<label>Type</label>
	 		<select class="form-control" name="type">
	 			<option value="1">Lodging</option>
	 			<option value="2">Travel</option>
	 			<option value="3">Food</option>
	 			<option value="4">Other</option>
	 		</select>
 		</div>
 		<div class='form-group'>
	 		<label>Description</label>
	 		<textarea class="form-control" name="description" placeholder ="Optional Description"></textarea>
 		</div>
 		<div class='form-group'>
 			<label>Upload Receipt</label>
 			<input type='file' class='form-control' name ='receipt'/>
 		</div>
 		<div class='form-group'>
 			<input type='submit' class='btn btn-success'/>
 		</div>
 	</form>
 	</div>
 	</div>
  <table id="keywords" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
       <!--   <th><span>Author Name</span></th>-->
        <th><span>Amount</span></th>
        <th><span>Type</span></th>
        <th><span>Description</span></th>
        <th><span>Submitted</span></th>
        <th><span>Resolved</span></th>
        <th><span>Resolver</span></th>
        <th><span>Status</span></th>
      </tr>
    </thead>
    <tbody>
	<c:forEach items="${employee_reimbursement}" var="reimb">
	  <c:set value="${reimb.getResolved() }" var="resolvedDateString" />
	  <fmt:formatDate value="${resolvedDateString}" var="resolved_time" />
	  
	  <c:set value="${reimb.getSubmitted() }" var="submittedDateString" />
	  <fmt:formatDate value="${submittedDateString}" var="submitted_time" />
      
      <tr>
       <!--  <td class="lalign"><c:out value="${reimb.getAuthorName()}"/></td>-->
        <td><fmt:formatNumber type="currency" value="${reimb.getAmount()}"/></td>
        <td><c:out value="${reimb.getType().getType()}" /></td>
        <td><c:out value="${reimb.getDescription()}" /></td>
      	<td><c:out value="${submitted_time }"/></td>
      	<td><c:out value="${resolved_time}"/></td>
      	<td><c:out value="${reimb.getResolverName() }"/></td>
        <td><c:out value="${reimb.getStatus().getStatus()}"/></td>
      </tr>
	</c:forEach>
     </tbody>
    </table>
<%@ include file="../includes/footer.jsp"%>
<script>
  //activate tablesorter
  $('#keywords').tablesorter(); 

 //show reimb form
 $("#show-form").on('click',showform);
 $("#remove-reimb-form").click(showform);
 function showform(){
	$("#reimb_form").fadeToggle();
	
 }

</script>		