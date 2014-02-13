<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<h2><spring:message code="label.dropconfirm" /></h2>

  <button onclick="window.location.href='/spring-blog/viewposts/${postid}/drop'"> 
  	<spring:message code="label.confirmyes" /> </button>
  <button onclick="window.location.href='/spring-blog/viewposts'" > 
  	<spring:message code="label.confirmno" /> </button>

</html>