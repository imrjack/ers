
<jsp:include page="../includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
  <table id="keywords" cellspacing="0" cellpadding="0">
    <thead>
      <tr>
        <th><span>Author Name</span></th>
        <th><span>Amount</span></th>
        <th><span>Type</span></th>
        <th><span>Description</span></th>
        <th><span>Status</span></th>
        <th><span>Approve / Deny</span></th>
      </tr>
    </thead>
    <tbody>
	<c:forEach items="${all_reimbursements}" var="reimb">
      	<c:set var="status" value = "${reimb.getStatus().getStatus() }"/>
      <tr>
        <td><a href=""><c:out value="${reimb.getAuthorName()}"/></a></td>
        <td><fmt:formatNumber type="currency" value="${reimb.getAmount()}"/></td>
        <td><c:out value="${reimb.getType().getType()}" /></td>
        <td><c:out value="${reimb.getDescription()}" /></td>
        <td><c:out value="${reimb.getStatus().getStatus()}"/></td>
      	<td>
 			<c:if test="${status == 'PENDING'}">
 				<form method="post" action="${pageContext.request.contextPath}/update_status.do">
	 				<input type="hidden" name="reimb_id" value="${reimb.getId()}"/>
	   				<button class='custom-glyph-btn' name="status" value ="2" type="submit">
			   			<span class="glyphicon glyphicon-ok"></span>
					</button>
					<div class='horizontal-spacer'></div>
			   		<button class='custom-glyph-btn' name="status" value ="3" type="submit">
					   <span class="glyphicon glyphicon-remove"></span>
					</button>
				</form>
 			</c:if>
      	</td>
      </tr>
	</c:forEach>
     </tbody>
    </table>
<script>
$(function(){
  $('#keywords').tablesorter(); 
  

})
</script>
<%@ include file="../includes/footer.jsp"%>