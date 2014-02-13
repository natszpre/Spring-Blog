<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>
  <body>
  
<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<p><spring:message code="label.addcommentssuccess" /></p>

   <a href = "/spring-blog/viewposts"><spring:message code="label.viewposts" /></a>

  </body>
</html>