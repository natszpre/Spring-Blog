<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>

<head>
	<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
</head>

  <body>
  
<div id="header">
	<div>
		<div id="navigation">
			<div>
				<ul>
					<li>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href = "adminpanel">Admin Panel</a>
						</sec:authorize>
					</li>	
					<li>
						<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
							<a href = "unpublishedposts"><spring:message code="label.unpublishedposts" /></a>
						 </sec:authorize>
					</li>
				</ul>
			</div>
		</div>	
		<div>
			<spring:url value="/resources/images/uk.png" htmlEscape="true" var="uk"/>
			<a href="?lang=en">UK <img src="${uk}"/> </a>	
				<spring:url value="/resources/images/pl.png" htmlEscape="true" var="pl"/>							
			<a href="?lang=pl">PL <img src="${pl}"/></a>
				
		</div>
	</div>
</div>
  	
  	<div id="content">
		<div id="blog">    	
			<c:forEach items="${posts}" var="post">	    			     
			        <h1>${post.title}</h1>					        
			        <i>${post.date}</i>    <br></br>    			
					<c:choose>
					  <c:when test="${fn:length(post.content) gt 300}">
					 	 <p>${fn:substring(post.content, 0, 300)}...
					 	 	<a href="/spring-blog/viewposts/${post.id}/display"><spring:message code="label.fullpost" /></a>					 	 </p> 
					  </c:when>					
					  <c:otherwise>
					  	 <p>${post.content}</p>
					  </c:otherwise>					  
					</c:choose>	        
			        <a href = "addfeedback/${post.id}"><spring:message code="label.feedback" /></a>	        
			        <a href = "viewcomments/${post.id}"><spring:message code="label.viewcomments" /></a>		
			</c:forEach>
		</div>
	</div>


<c:forEach var="i" begin="1" end="${numOfPages}">
	<a href = "/spring-blog/viewposts/${i}"> ${i} | </a>
</c:forEach>
    
  </body>
</html>