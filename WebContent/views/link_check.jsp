<jsp:include page="layouts/header.jsp" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://localhost:8080/taglibs/captcha" prefix="captcha" %>

<c:if test="${not expired_link and not toosoon_link}">

	<div class="mainwrapper twelve columns">
		<form action="" class="three columns offset-by-four" method="post">
			<div class="eight columns">
				<c:choose>
					<c:when test="${urlType == 'Url'}">
						
						<label for="pwd_input">Password</label>
						<input id="pwd_input" name="password" type="password"> 	
						
					</c:when>
					<c:otherwise>
					
						<c:if test="${sessionScope.url.needsPasswordCheck()}">
							<c:forEach var="i" begin="1" end="${sessionScope.url.passwordListSize()}">
								<label for="pwd_input">Password ${i}</label>
								<input id="pwd_input" name="password${i}" type="password">
							</c:forEach>
						</c:if>
						
						<c:if test="${sessionScope.url.mail != ''}">
							<label>Mail</label>
							<input type="text" name="mail"/>
						</c:if>
						
						<c:if test="${sessionScope.url.captcha}">
							<label for="captchaCode" class="prompt">
							  Retype the characters from the picture :</label>
							
							<!-- Hey look at my taglib -->
							<captcha:captcha />
							
							<input id="captchaCode" type="text" name="captchaCode" />
						</c:if>
						
					</c:otherwise>
				</c:choose>
			
				<input value="Submit" style="float: right;" type="submit">
			</div>
		</form>
		
		<c:if test="${wrong_password}">
			<p class="errorText">Wrong password !</p>
		</c:if>
		
		<c:if test="${wrong_password1}">
			<p class="errorText">Wrong password 1 !</p>
		</c:if>
		
		<c:if test="${wrong_password2}">
			<p class="errorText">Wrong password 2 !</p>
		</c:if>
		
		<c:if test="${wrong_password3}">
			<p class="errorText">Wrong password 3 !</p>
		</c:if>
		
		<c:if test="${wrong_mail}">
			<p class="errorText">Wrong mail !</p>
		</c:if>
		
		<c:if test="${captcha_failed}">
			<p class="errorText">Wrong captcha code !</p>
		</c:if>
		
	</div>
</c:if>
	
<c:if test="${expired_link}">
	<br>
	<h5 class="errorText">This link has expired !</h5>
</c:if>

<c:if test="${toosoon_link}">
	<br>
	<h5 class="errorText">This link has yet to be activated !</h5>
</c:if>
		
<jsp:include page="layouts/footer.jsp" />