<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />
<title>启航课堂首页</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/index.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">

<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<!-- 轮播图 -->
	<div id="carousel-qihangkt" class="carousel slide" data-ride="carousel">
		<!-- Indicators -->
		<ol class="carousel-indicators">
			<li data-target="#carousel-example-generic" data-slide-to="0"
				class="active"></li>
			<li data-target="#carousel-example-generic" data-slide-to="1"></li>
			<li data-target="#carousel-example-generic" data-slide-to="2"></li>
		</ol>

		<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active"
				data-lg-img="${pageContext.request.contextPath}/static/images/slide_01_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/images/slide_01_768x410.jpg"></div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath}/static/images/slide_02_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/images/slide_02_768x410.jpg"></div>
			<div class="item"
				data-lg-img="${pageContext.request.contextPath}/static/images/slide_03_2000x410.jpg"
				data-xs-img="${pageContext.request.contextPath}/static/images/slide_03_768x410.jpg"></div>
		</div>

		<!-- Controls -->
		<a class="left carousel-control" href="#carousel-qihangkt"
			role="button" data-slide="prev"> <span
			class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span> <span
			class="sr-only">上一张</span>
		</a> <a class="right carousel-control" href="#carousel-qihangkt"
			role="button" data-slide="next"> <span
			class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
			<span class="sr-only">下一张</span>
		</a>
	</div>
	<!-- 课程列表缩略图 -->
	<div class="container" id="courseList">
		<div class="page-header">
			<h3 class="text-center">
				最新发布课程<a href="${pageContext.request.contextPath}/allCourse">全部课程></a>
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
								 免费<a href="#" class="course_group">Java系列课程</a>
							</h3>
						</div>
					</div>
				</div>
			</c:forEach>
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