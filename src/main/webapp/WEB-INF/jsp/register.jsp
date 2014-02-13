<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ page pageEncoding="utf-8" %>
<html>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

  <form action="/spring-blog/login" method="post">

		<spring:message code="label.user" /> <input type="text" name="username"><br>
		<spring:message code="label.password" /> <input type="password" name="password">
		<input type="submit" onclick="formSubmit()" value="<spring:message code="label.register" />">	
			
		</form>
</html>