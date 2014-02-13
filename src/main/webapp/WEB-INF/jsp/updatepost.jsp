<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="utf-8" %>
<html>

<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

   <form action="../../viewposts/${post.id}/update" method="post">
   		<h1><spring:message code="label.updatepage" /></h1>
        <spring:message code="label.title" /> <input type="text" name="title" value=${post.title}><br>		
		<spring:message code="label.content" /> <input type="text" name="content" value="${post.content}"> 		
		<input type="submit" onclick="formSubmit()" value="<spring:message code="label.update" />">	
   </form>


</html>