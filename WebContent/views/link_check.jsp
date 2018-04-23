<jsp:include page="layouts/header.jsp" />
<div class="mainwrapper twelve columns">
	<form action="/y/${shortUrl}" class="three columns offset-by-four" method="post">
	
		<div class="one-half column">
			<label for="pwd_input">Password</label>
			<input id="pwd_input" name="password" type="text"> 	
			
			<input value="Submit" style="float: right;" type="submit">			
		</div>
				
	</form>
</div>
<jsp:include page="layouts/footer.jsp" />