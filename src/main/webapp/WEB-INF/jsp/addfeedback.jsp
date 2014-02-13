<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page pageEncoding="utf-8" %>
<html>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

  <form action="../viewposts/${postid}/comments" method="post">

		Mail: <input type="text" name="email"><br>
		<spring:message code="label.content" /> <input type="text" name="content">
		<input type="submit" onclick="formSubmit()" value="<spring:message code="label.feedback" />">	
			
		</form>
</html>