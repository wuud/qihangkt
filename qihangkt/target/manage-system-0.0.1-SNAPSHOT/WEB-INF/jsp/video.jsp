<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${course.cname}_在线播放</title>
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
<link rel="stylesheet"
	href='${pageContext.request.contextPath}/static/css/video-js.min.css' />

</head>
<body>
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<div class="container ">
		<div class=" col-md-offset-3" >
			<video id="courseVideo"
				class="video-js vjs-default-skin vjs-big-play-centered" controls
				preload="none" width="800" height="480"
				poster="${pageContext.request.contextPath}/${course.picture}"
				data-setup="{}">
				<source src="${pageContext.request.contextPath}/${video.videoPath}"
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
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
</body>
</html>