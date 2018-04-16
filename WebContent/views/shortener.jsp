<jsp:include page="layouts/header.jsp" />
<form action="?action=doShorten" method="post">

	<label for="url_input">URL to shorten</label>
	<input id="url_input" name="urlToShorten" type="text"> 
	
	<input type="button" id="show_password_div_button" value="Secure with password">

	<div id="password_div" style="display: none">
		<label for="password_input">Password</label>
		<input id="password_input" name="password" type="text">
	</div>
	
	<input value="Shorten" type="submit">

</form>
<jsp:include page="layouts/footer.jsp" />