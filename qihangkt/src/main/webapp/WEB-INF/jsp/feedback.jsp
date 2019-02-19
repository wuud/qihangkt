<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
	<title>意见反馈</title>
</head>
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />

<body>
<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div class="container">
		<p class="lead">联系作者：</p>
		<p class="lead">1.直接通过下方表单提交信息</p>
		<p class="lead">2.发送邮件到作者个人邮箱wuu_dd@qq.com</p>
		<form action="${pageContext.request.contextPath}/feedback" method="post">
			<label class=" control-label" style="font-size: 22px">请在下方说出你的想法：</label>
			<textarea class="form-control" rows="5" name="message"></textarea>

			<button type="submit" class="btn btn-primary"style="margin-top: 10px">提交</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>