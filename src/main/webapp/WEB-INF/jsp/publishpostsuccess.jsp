<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>
  <body>
  
<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<p><spring:message code="label.publishpostsuccess" /></p>

<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href = "/spring-blog/adminpanel">Admin Panel</a>
</sec:authorize>

<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_USER')">
	<a href = "/spring-blog/unpublishedposts"><spring:message code="label.unpublishedposts" /></a>
 </sec:authorize>

<a href = "/spring-blog/viewposts"><spring:message code="label.viewposts" /></a>

  </body>
</html>