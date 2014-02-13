<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>
  <body>
  
<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

    <tr>      
        <h1>${title}</h1>
        <p>${date}</p>
        <p>${content}</p> 
    </tr>
    
    <a href = "/spring-blog/adminpanel"> Admin Panel </a>

  </body>
</html>