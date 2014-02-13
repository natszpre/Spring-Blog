<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page pageEncoding="utf-8" %>
<html>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

  <form action="viewposts" method="post">

		<spring:message code="label.title" /> <input type="text" name="title"><br>
		<spring:message code="label.content" /> <input type="text" name="content">
		<input type="submit" onclick="formSubmit()" value="<spring:message code="label.addpost" />">	
			
		</form>
</html>