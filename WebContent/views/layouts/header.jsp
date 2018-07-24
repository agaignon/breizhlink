<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
<script src="<c:url value="/resources/js/functions.js"/>"></script>
<link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/css/normalize.css"/>" rel="stylesheet"/>
<link href="<c:url value="/resources/css/skeleton.css"/>" rel="stylesheet"/>

<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet"> 
<title>Breizhlink</title>
</head>
<body>
	<div class="eight columns">
		<h2 class="header-content">
			<a href="<c:url value="${not empty sessionScope.user ? '/auth' : ''}/shorten"/>" class="nav-link">Breizhlink</a>
		</h2>
	</div>

	<div id="nav" class="four columns">
		<ul>
			<c:choose>
				<c:when test="${not empty sessionScope.user}">
					<div class="nav-right">
						<a href="<c:url value="/auth/account"/>" class="nav-link">Hello, ${sessionScope.user.username}</a>&emsp;&emsp;
						<a href="<c:url value="/auth/urls"/>" class="nav-link">My URLs</a>&emsp;&emsp;
						<a href="<c:url value="/logout"/>" class="nav-link">Log out</a>
					</div>
				</c:when>
				<c:otherwise>
					<div class="nav-right">
						<a href="<c:url value="/login"/>" class="nav-link">Log in</a>&emsp;&emsp;
						<a href="<c:url value="/signup"/>" class="nav-link">Sign up</a>
					</div>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>
	
	<div class="header-margin"></div>