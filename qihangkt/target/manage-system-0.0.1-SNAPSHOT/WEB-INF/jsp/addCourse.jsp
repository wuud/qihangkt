<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>发布课程</title>
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />

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
	<div class="container">

		<form action="${pageContext.request.contextPath}/addCourse"
			method="post" enctype="multipart/form-data">
			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="courseName">课程名称</label> <input type="text"
						class="form-control" name="courseName" placeholder="课程名称">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="courseIntro">课程介绍</label> <input type="text"
						class="form-control" name="courseIntro" placeholder="课程介绍">
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="courseUser">课程发布者</label> <input type="text"
						class="form-control" name="courseUser" placeholder="课程发布者">
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="coursePicture">课程封面</label> <input type="file"
						multiple="multiple" class="form-inline" name="coursePicture"
						placeholder="课程封面">
					<p class="help-block">请上传600x1060分辨率的图片</p>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="courseFile">课程视频</label> <input type="file"
						multiple="multiple" class="form-inline" name="courseVideo"
						placeholder="课程视频">
					<p class="help-block">课程学习视频（可一次上传多个文件，不要大于100M）</p>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-8 col-md-offset-8 ">
					<label for="courseFile">课程资料</label> <input type="file"
						name="courseFile" placeholder="课程资料">
					<p class="help-block">课程学习资料（请打包后上传）</p>
				</div>
			</div>

			<button type="submit" class="btn btn-primary col-md-offset-11">发布课程</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>