<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>教师管理</title>

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/user_manage.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">
<link
	href="${pageContext.request.contextPath}/static/bootstrap-select/css/bootstrap-select.min.css"
	rel="stylesheet" />

<!--[if lt IE 9]>
      <script src="https://cdn.jsdelivr.net/npm/html5shiv@3.7.3/dist/html5shiv.min.js"></script>
      <script src="https://cdn.jsdelivr.net/npm/respond.js@1.4.2/dest/respond.min.js"></script>
    <![endif]-->
</head>
<body>
	<div class="container">
		<form id="searchForm" action="${pageContext.request.contextPath}/admin/searchUser" method="post">
			<div class="input-group col-md-12 col-md-offset-4">
				<input type="text" name="userInfo"
					class="form-control text-center" placeholder="输入用户名/手机号/邮箱"> 
					<span class="input-group-btn">
					<button id="searchBtn" type="button" class="btn btn-primary">搜索</button>
				</span>
			</div>
		</form>
		<div class="row">
			<div class="col-md-4">
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#addUserModal">增加</button>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-default">查询</button>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-default" id="batchDelUsersBtn">删除</button>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-default">修改</button>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-default">导入</button>
			</div>
			<div class="col-md-4">
				<button type="button" class="btn btn-default">导出</button>
			</div>
		</div>
		<div id="content_table" class="row">
			<div class="table-responsive">
				<table class="table table-bordered table-hover">
					<tr>
						<td style="width: 30px;"><input type="checkbox"
							class="chkall" onclick="chkall();"></td>
						<td>用户名</td>
						<td>手机号</td>
						<td>邮箱地址</td>
						<td>角色</td>
						<td>用户状态</td>
						<td style="width: 100px;">操作</td>
					</tr>
					<c:forEach items="${userList}" var="user">
						<tr>
							<td style="width: 30px;"><input value="${user.id}" type="checkbox"
								class="chkone" onclick="chkone();"></td>
							<td>${user.username }</td>
							<td>${user.phone}</td>
							<td>${user.email}</td>
							<td>${user.roleId.rname}</td>
							<td>${user.status}</td>
							<td><a
								href="${pageContext.request.contextPath}/admin/updateUserModal?id=${user.id}"
								data-target="#updateUserModal" data-toggle="modal">编辑</a> <a
								href="${pageContext.request.contextPath}/admin/delUser?id=${user.id}"
								onclick="return delSure()">删除</a></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
		<div id="pager">
			<p class="pull-left">
				总共有<span> ${total } </span>条记录，当前是第<span> ${page+1} /
					${totalPages }</span>页
			</p>
			<div class="btngroup pull-right">
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/user?page=0">首页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/user?page=${prevPage}">上一页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/user?page=${nextPage}">下一页</a>
				<a type="button" class="btn btn-default"
					href="${pageContext.request.contextPath}/admin/user?page=${totalPages-1}">尾页</a>
			</div>
		</div>
	</div>
	<!-- 模态框 -->
	<div class="modal fade" id="addUserModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span>&times;</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">添加用户</h4>
				</div>
				<div class="modal-body">
					<form id="addUserForm"
						action="${pageContext.request.contextPath}/admin/addUser"
						method="post">
						<div class="form-group">
							<label>用户名：</label> <input type="text" name="username"
								class="form-control" placeholder="用户名">
						</div>
						<div class="form-group">
							<label>密码：</label> <input type="password" name="password"
								class="form-control" placeholder="密码">
						</div>
						<div class="form-group">
							<label>手机号：</label> <input type="text" name="phone"
								class="form-control" placeholder="手机号码">
						</div>
						<div class="form-group">
							<label>邮箱：</label> <input type="text" name="email"
								class="form-control" placeholder="电子邮箱">
						</div>
						<div class="form-group">
							<label>用户类型：</label> <select class="selectpicker form-control"
								name="role_Id">
								<c:forEach items="${roleList}" var="role">
									<option value="${role.id}">${role.rname}</option>
								</c:forEach>
							</select>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button id="addUserBtn" type="button" class="btn btn-primary">添加</button>
				</div>
			</div>
		</div>
	</div>
	<!-- 用户编辑 模态框-->
	<div class="modal fade" id="updateUserModal" tabindex="-1">
		<div class="modal-dialog">
			<div class="modal-content">
				<!-- 动态内容生成 -->
			</div>
		</div>
	</div>

	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<script src="${pageContext.request.contextPath}/static/js/user.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-select/js/bootstrap-select.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/bootstrap-select/js/i18n/defaults-zh_CN.min.js"></script>
</body>
</html>