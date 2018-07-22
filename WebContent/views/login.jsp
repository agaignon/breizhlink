<jsp:include page="layouts/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <c:if test="${param.error != null}">	 --%>
<!-- 	<div class="alert alert-danger-custom">Identifiant ou mot de passe incorrect.</div> -->
<%-- </c:if> --%>

<%-- <c:if test="${param.logout != null}"> --%>
<!-- 	<div class="alert alert-info-custom">Vous êtes déconnecté.</div> -->
<%-- </c:if> --%>

<br>
<div class="mainwrapper twelve columns">
	
	<form action="" method="post" class="three columns offset-by-four">
	
		<div class="one-half column">
	
			<label class="control-label">Username</label>
			<input type="text" name="username"/>
	
			<label class="control-label">Password</label>
			<input type="password" name="password"/>
						
			<br>	
			
			<input type="submit" value="Log in" />
			
		</div>
		
	</form>
	
</div>
<jsp:include page="layouts/footer.jsp" />