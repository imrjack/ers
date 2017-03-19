<jsp:include page="includes/header.jsp"/>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
	<div class='form-wrapper'>
	<h3> Login </h3>
	<form action='login.do' method="post">
		<div class='form-group'>
			<label>username</label> 
			<input class='form-control' type='text' name='u_name' />
		</div>
		<div class='form-group'> 
			<label>password</label>
			<input class='form-control' type='password' name='pass'/>
		</div>
			<button type="submit" name="login" class='btn btn-primary text-right'>
				Login
			</button>
	</form>	
	<c:if test="${not empty please_login}">
		<div style="color: red; font-weight: bold;">
			<c:out value="${please_login}"/>
		</div>
	</c:if>
	</div>
<jsp:include page="includes/footer.jsp"/>