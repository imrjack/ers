<jsp:include page="includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<form action='login.do' method="post">
		<label style="color:#fff;">username</label> <input type='text' name='u_name' /> 
		<input type='password' name='pass'/>
		<input type="submit" name="login"/>
	</form>	
	<c:if test="${not empty please_login}">
		<div style="color: red; font-weight: bold;">
			<c:out value="${please_login}"/>
		</div>
	</c:if>
<jsp:include page="includes/footer.jsp"/>