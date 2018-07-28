<jsp:include page="layouts/header.jsp" />

<div class="mainwrapper twelve columns">
	<div class="eight columns offset-by-three">
		<form action="" method="post">
		
			<div class="six columns">
				<label for="url_input">URL to shorten</label>
				<input id="url_input" name="sourceUrl" type="text"> 	
			
				<label for="show_password_div_button" id='show_password_label'>
					Secure with password(s)
					<input type="checkbox" name="pwdCheck" id="show_password_div_button" value="Secure with password">
				</label>
				
				<label for="captcha" id='captcha'>
					Captcha
					<input type="checkbox" name="captcha" id="captcha" value="Captcha">
				</label>
				
				<label>Mail</label>
				<input type="text" name="mail"/>
				
				<div>
					<input type="radio" id="fromto" name="date" value="fromto"/>
					Available from <input type="text" name="from" placeholder="dd/mm/yyyy"/> to <input type="text" name="to" placeholder="dd/mm/yyyy"/>
				</div>
		
				<div>
					<input type="radio" id="until" name="date" value="until"/>
					Available until <input type="text" name="until" placeholder="dd/mm/yyyy"/>
				</div>
				
				<input value="Shorten" style="float: right;" type="submit">
			</div>
		
			<div class="one-half column">
				<div id="password_div" class="password_div">
					<label for="password_input1">Password 1 (required)</label>
					<input id="password_input1" name="password1" type="password">
				</div>
				
				<div id="password_div" class="password_div">
					<label for="password_input2">Password 2 (not required)</label>
					<input id="password_input2" name="password2" type="password">
				</div>
				
				<div id="password_div" class="password_div">
					<label for="password_input3">Password 3 (not required)</label>
					<input id="password_input3" name="password3" type="password">
				</div>
			</div>
		</form>
	</div>
</div>
<jsp:include page="layouts/footer.jsp" />