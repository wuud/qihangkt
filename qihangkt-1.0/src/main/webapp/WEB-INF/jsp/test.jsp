<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<ul class="nav navbar-nav navbar-right" id="reglog">

		<c:choose>
			<c:when test="${user.username==null}">
			1
			</c:when>
			<c:when test="${user.username!=null}">
			2
			</c:when>
		</c:choose>
	</ul>
</body>
</html>