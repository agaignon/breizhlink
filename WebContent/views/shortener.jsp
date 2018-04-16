<jsp:include page="layouts/header.jsp" />
<div class="mainwrapper twelve columns">
<form action="?action=doShorten" class="three columns offset-by-four" method="post">

	<div class="one-half column"><label for="url_input">URL to shorten</label>
	<input id="url_input" name="urlToShorten" type="text"> 
	
	
	<label for="show_password_div_button" id='show_password_label'>Secure with password
		<input type="checkbox" id="show_password_div_button" value="Secure with password">
	</label>
	</div>

	<div class="one-half column"></div>
	<div id="password_div" style="visibility: hidden; float: right;">
		<label for="password_input">Password</label>
		<input id="password_input" name="password" type="text">
	</div>
	
	<input value="Shorten" style="float: right;" type="submit">
	</div>
</form>
</div>
<jsp:include page="layouts/footer.jsp" />