<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>启航课堂首页</title>
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/index.css">
	<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/user_manage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">

<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<!-- 课程列表缩略图 -->
	<div class="container" id="courseList">
		<div class="page-header">
			<h3 class="text-center">
				全部课程
			</h3>
		</div>
		<div class="row">
			<c:forEach items="${courseList }" var="course">
				<div class="col-xs-12 col-md-6">
					<div class="thumbnail">
						<a href="${pageContext.request.contextPath}/course/${course.id}"><img
							src="${pageContext.request.contextPath}/${course.picture}"
							alt="课程"></a>
						<div class="caption">
							<h3>${course.cname }</h3>
							<h3 class="course_price">
								免费 <a href="#" class="course_group">Java系列课程</a>
							</h3>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
		<div id="pager">
			<p class="pull-left">
				总共有<span> ${total } </span>条记录，当前是第<span> ${page+1} /
					${totalPages }</span>页
			</p>
			<div class="btngroup pull-right">
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/course?page=0">首页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/course?page=${prevPage}">上一页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/course?page=${nextPage}">下一页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/course?page=${totalPages-1}">尾页</a>
			</div>
		</div>
	</div>
	
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/static/js/index.js"></script>
</body>
</html>