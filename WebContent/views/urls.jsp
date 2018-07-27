<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://localhost:8080/taglibs/pagination" prefix="p" %>
<jsp:include page="layouts/header.jsp" />

<div class="mainwrapper twelve columns">
	<div class="twelve columns offset-by-three">
		<table>
		
				<tr>
					<th>Short Url</th>
					<th>Source Url</th>
					<th>Creation Date</th>
					<th>Stats</th>
				</tr>
		
			<c:forEach items="${pagination.pageList}" var="url">
			
				<tr>
					<td><a href="${pageContext.request.contextPath}/y/${url.shortUrl}">http://localhost:8080${pageContext.request.contextPath}/y/${url.shortUrl}</a></td>
					<td><a href="${url.sourceUrl}">${url.sourceUrl}</a></td>
					<td>${url.creationDate}</td>
					<td><a href="<c:url value="/auth/stats?id=${url.id}"/>">Show</a></td>
				</tr>
			
			</c:forEach>
			
		</table>
	</div>
	<div class="eight columns offset-by-four">
		<!-- Ok cool but look at my taglib though -->
		<p:pagination pagination="${pagination}" />
	</div>
</div>

<jsp:include page="layouts/footer.jsp" />