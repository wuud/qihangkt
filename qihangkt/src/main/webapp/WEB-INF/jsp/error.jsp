<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html>
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="renderer" content="webkit" />
    <title>发生错误</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/css/error.css" />
  <link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />
  
  </head>
  <body>
    <div class="page">
      <div class="error">
        <h1 class="header">
          
          404 - 页面不存在
        </h1>
        <div class="content">
         <p>
           <strong>没有这个页面哦</strong>
         </p>
         <p>来源链接是否正确？检查一下拼写</p>
         <hr>
         <p>
           <a href="${pageContext.request.contextPath}">返回首页</a>
         </p>
       </div>
      </div>
    </div>
  </body>
</html>
