<jsp:include page="layouts/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="mainwrapper twelve columns">
	
	<form action="" method="post" class="three columns offset-by-four">
	
		<div class="one-half column">
	
			<label class="control-label">Username</label>
			<input type="text" name="username"/>
	
			<label class="control-label">Password</label>
			<input type="password" name="password"/>
			
			<label class="control-label">Mail</label>
			<input type="text" name="mail"/>
			
			<label class="control-label">Status</label> 
			<select name="status">
				<option value="INDIVIDUAL" selected>Individual</option>
				<option value="COMPANY">Company</option>
				<option value="ASSOCIATION">Association</option>
			</select> 
			
			<br>	
			
			<input type="submit" value="Sign Up" />
			
		</div>
		
	</form>
	
	<c:if test="${error}">
		<p class="errorText">All fields are required !</p>
	</c:if>
	
	<c:if test="${username_exists}">
		<p class="errorText">Username already exists !</p>
	</c:if>
	
</div>
<jsp:include page="layouts/footer.jsp" />