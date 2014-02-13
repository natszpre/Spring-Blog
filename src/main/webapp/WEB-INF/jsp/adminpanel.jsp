<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>
  <body>
  	
<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<a href="<c:url value="/j_spring_security_logout" />" > <spring:message code="label.logout" /></a>
<a href = "/spring-blog/viewcomments/viewunpublishedcomments"> <spring:message code="label.unpublishedcomments" /> </a>
<a href = "/spring-blog/unpublishedposts"> <spring:message code="label.unpublishedposts" /> </a>

<c:forEach items="${posts}" var="post">
    <tr>      
        <h1>${post.title}</h1>
        <button onclick="window.location.href='/spring-blog/adminpanel/${post.id}/updatepost'"> 
        	<spring:message code="label.update" /> </button>
        <button onclick="window.location.href='/spring-blog/adminpanel/${post.id}/droppost'" > 
        	<spring:message code="label.drop" /> </button>
        <p>${post.date}</p>
	       <c:choose>
			  <c:when test="${fn:length(post.content) gt 300}">
			 	 <p>${fn:substring(post.content, 0, 300)}...
			 	 	<a href="/spring-blog/viewposts/${post.id}/display"><spring:message code="label.fullpost" /></a>
			 	 </p> 
			  </c:when>
			
			  <c:otherwise>
			  	 <p>${post.content}</p>
			  </c:otherwise>
			  
			</c:choose>
    </tr>
</c:forEach>

<c:forEach var="i" begin="1" end="${numOfPages}">
	<a href = "/spring-blog/adminpanel/${i}"> ${i} | </a>
</c:forEach>

  </body>
</html>