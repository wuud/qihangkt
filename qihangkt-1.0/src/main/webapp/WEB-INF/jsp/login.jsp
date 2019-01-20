<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>登录 | 注册</title>

<link href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/css/login.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/static/font/iconfont.css">

<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	
	<!-- 登录表单 -->
	<div id="loginhtml" class="container">
		<div class="row">
			<div class="col-md-7 col-sm-6 col-xs-5"></div>
			<div class="col-md-10  col-sm-12 col-xs-14 login-col">
				<ul class="nav nav-tabs">
					<li class="col-xs-12 active"><a href="#loginform"
						data-toggle="tab">登录账号</a></li>
					<li class="col-xs-12"><a data-toggle="tab" href="#regform">注册账号</a></li>
				</ul>
				<div class="tab-content">
					<div id="loginform" class="tab-pane fade in active">
						<form id="forcss"action="${pageContext.request.contextPath}/doLogin" method="post">
							<div class="form-group">
								<label>账号:</label> <c:if test="${error!=null }"><span style="color:red;float:right;font-size:20px">${error}</span></c:if>
								<input type="text" name="number"
									class="form-control" placeholder="邮箱/手机">
							</div>
							<div class="form-group">
								<label>密码:</label> <input type="password" name="password"
									class="form-control" placeholder="密码">
							</div>
							<div class="checkbox">
								<label><input type="checkbox" name="rememberme">10天内自动登录</label>
							</div>
							<div class="form-group">
								<input type="submit" class="form-control btn btn-primary"
									value="登录">
							</div>
							<div class="form-group">
								<p>
									<a href="#">找回密码</a> | 还没有账号? <a href="#">注册账号</a>
								</p>
								<p style="text-align: right;">使用第三账号登录:</p>
								<p style="text-align: right;">
									<a href="#" style="text-decoration: none;"><span
										style="font-size: 30px;" class="iconfont icon-weixin-copy "></span></a>
									<a href="#" style="text-decoration: none;"><span
										style="font-size: 30px;" class="iconfont icon-QQ"></span></a>
								</p>
							</div>
						</form>
					</div>
					<div id="regform" class="tab-pane fade">
						<form action="#" method="post">
							<div class="form-group">
								<label>手机:</label> <input type="text" name="username"
									class="form-control" placeholder="常用的手机号码">
							</div>
							<div class="form-group">
								<label>用户名:</label> <input type="text" name="username"
									class="form-control" placeholder="用户名">
							</div>
							<div class="form-group">
								<label>密码:</label> <input type="password" name="password"
									class="form-control" placeholder="密码">
							</div>
							<div class="form-group">
								<label>手机验证码:</label>
								<div class="row">
									<div class="col-xs-12">
										<input type="text" name="phoneCode" class="form-control"
											placeholder="验证码">
									</div>
									<div class="col-xs-12">
										<input value="点击获取验证码" type="button" name="phoneCode"
											class="form-control btn btn-default">
									</div>
								</div>
							</div>
							<div class="form-group"
								style="margin-top: 30px; margin-bottom: 20px;">
								<input type="submit" class="form-control btn btn-primary"
									value="登录">
							</div>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-7  col-sm-6 col-xs-5"></div>
		</div>
	</div>
	<!-- 页面底部 -->
	<div class="footer hidden-xs">
		<div class="footericon">
			<span class="iconfont icon-chuanxiang"></span>
		</div>
		<p class="cr">Copyright © 2018 qihangzaixian. All Rights Reserved.</p>
		<p class="cr">
			启航在线课程 版权所有 | <a href="#">工具下载</a> | <a href="#">资料下载</a> | <a
				href="#">视频下载</a> | <a href="#">问题反馈</a> | <a href="#">帮助</a>
		</p>
	</div>
	<script src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/static/js/index.js"></script>
</body>
</html>