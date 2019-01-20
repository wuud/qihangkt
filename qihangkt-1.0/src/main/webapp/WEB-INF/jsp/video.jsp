<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${course.cname}_在线播放</title>
<link rel="stylesheet"
	href='${pageContext.request.contextPath}/static/css/video-js.min.css' />
<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/video.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">
</head>
<body>
	<!-- 导航条 -->
	<nav class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#qh_navbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a href="${pageContext.request.contextPath}/" class="navbar-brand"><span
					class="iconfont icon-chuanxiang "></span>启航课堂</a>
			</div>
			<div id="qh_navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="${pageContext.request.contextPath}/">首页</a></li>
					<li><a href="#">全部课程</a></li>
					<li><a href="#">问题讨论</a></li>
					<li><a href="#">学习路线</a></li>
					<li><a href="#">资料下载</a></li>
				</ul>
				<div class="navbar-form navbar-left">
					<form action="" accept="#" method="get">
						<div class="input-group">
							<input type="text" name="search_str" class="form-control"
								placeholder="课程名称"> <span class="input-group-btn">
								<button type="button" class="btn btn-primary">
									<span class="glyphicon glyphicon-search"></span>
								</button>
							</span>
						</div>
					</form>
				</div>
				<ul class="nav navbar-nav navbar-right" id="reglog">
					<c:if test="${user==null}">
						<li><a href="${pageContext.request.contextPath}/login"><span
								class="glyphicon glyphicon-user"></span> 登录 | <span
								class="glyphicon glyphicon-log-in"></span> 注册</a></li>
					</c:if>
					<c:if test="${user!=null}">
						<li><a href="#" id="username">${user.username}</a></li>
						<li><a href="${pageContext.request.contextPath}/logout"
							alt="logout"><i class="iconfont icon-tuichu"></i></a></li>

					</c:if>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container ">
		<div class=" col-md-offset-3">
			<video id="courseVideo"
				class="video-js vjs-default-skin vjs-big-play-centered" controls
				preload="none" width="800" height="480"
				poster="${pageContext.request.contextPath}${course.picture}"
				data-setup="{}">
				<source src="${pageContext.request.contextPath}${course.video}"
					type='video/mp4' />

			</video>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/static/js/video.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<script>
		videojs.options.flash.swf = "video-js.swf";
	</script>
</body>
</html>