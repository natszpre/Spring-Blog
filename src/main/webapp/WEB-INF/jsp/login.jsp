<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page pageEncoding="utf-8" %>
<html>
<head>
<title>Login Page</title>
<link href="<c:url value="/resources/css/style.css" />" rel="stylesheet"/>
	
<div style="text-align:right;">
<spring:message code="label.language" /> <a href="?lang=en">UK</a> | <a href="?lang=pl">PL</a>
</div>

<style>
.errorblock {
	color: #ff0000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<center>
<body onload='document.f.j_username.focus();'>

	<h1><spring:message code="label.loginpage" /></h1>

	<c:if test="${not empty error}">
		<div class="errorblock">
			<spring:message code="label.loginnotsucces" /> <br /> <spring:message code="label.caused" />
			${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
		</div>
	</c:if>

	<form name='f' action="<c:url value='j_spring_security_check' />"
		method='POST'>

		<table>
			<tr>
				<td><spring:message code="label.user" /></td>
				<td><input type='text' name='j_username' value=''>
				</td>
			</tr>
			<tr>
				<td><spring:message code="label.password" /></td>
				<td><input type='password' name='j_password' />
				</td>
			</tr>
			<tr>
				<td colspan='2'><input name="LogIn" type="submit"
					value='<spring:message code="label.login" />' />
				</td>
			</tr>
		</table>
	</form>
	
	<button onclick="window.location.href='/spring-blog/register'" > <spring:message code="label.register" /> </button>

	
</body></center>
</html>