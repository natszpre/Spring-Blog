<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>

  <body>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<h1><spring:message code="label.comments" /></h1>  
<c:forEach items="${comments}" var="comment">
    <tr>            
        <p>>${comment.content}</p>
        <p>${comment.email}</p>
        <a href = "/spring-blog/viewcomments/${comment.id}/publish"> <spring:message code="label.publish" /> </a>     
    </tr>
</c:forEach>
    
  </body>
</html>