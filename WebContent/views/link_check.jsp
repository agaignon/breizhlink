<jsp:include page="layouts/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="mainwrapper twelve columns">
	<form action="" class="three columns offset-by-four" method="post">
	
		<div class="one-half column">
			<label for="pwd_input">Password</label>
			<input id="pwd_input" name="password" type="password"> 	
			
			<input value="Submit" style="float: right;" type="submit">			
		</div>
		
	</form>
	
	<c:if test="${error}">
		<p class="errorText">Wrong password !</p>
	</c:if>
		
</div>
<jsp:include page="layouts/footer.jsp" />