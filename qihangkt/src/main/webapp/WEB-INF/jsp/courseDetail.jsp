<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="zh">
<head>
<meta charset="UTF-8">
<meta name="renderer" content="webkit">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<meta name="format-detection" content="telephone=no,email=no" />

<meta http-equiv="Cache-Control" content="no-transform" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<meta name="applicable-device" content="pc" />


<meta name="mobile-agent"
	content="format=html5;">
<link rel="alternate" media="only screen and (max-width: 640px)">

<meta itemprop="name" content="${course.cname }" />

<title>${course.cname}</title>
<link rel="icon" href="${pageContext.request.contextPath}/static/images/logo.png" type="image/x-icon" />

<link
	href="${pageContext.request.contextPath}/static/lib/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href='${pageContext.request.contextPath}/static/css/a.css' />
<link rel="stylesheet"
	href='${pageContext.request.contextPath}/static/css/b.css' />

<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/static/css/main.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/font/iconfont.css">

</head>
<body class="page-course l-media l-aside-right ">
	<%@ include file="/WEB-INF/jsp/header.jsp" %>
	<!--合作头部展示-->
	<nav id="js-nav-cjol" class="nav-cjol">
		<div class="clearfix nav-cjol-cnt">
			<h2>联合课程专区</h2>
			<a class="nav-cjol-back" href="http://learning.cjol.com" title="返回">返回</a>
			<a class="nav-cjol-judge" href="http://assessment.cjol.com"
				title="测评入口">测评入口</a> <a class="nav-cjol-index"
				href="http://cjol.com" title="热线首页">热线首页</a>
		</div>
	</nav>



	<!--置顶条-->
	<nav class="sticky-nav hide" id="js_float_tab"
		auto-test="mod_sticky_nav">
		<div class="inner-center">
			<ul class="tabs-tt-bar js_tab">
				<li class="tabs-tt active" ref="js_basic_tab">课程概述</li>
			</ul>
			<div class="btn-join js-apply-btn btn-default js-apply-btn-task">

				<span>立即学习</span>
			</div>
		</div>
	</nav>

	<!--banner-->


	<section class="section-feature section--course-banner "
		style="margin-top: -20px">
		<div class="course-banner inner-center clearfix">
			<!--面包屑-->
			<nav class="breadcrumb inner-center">
				<a class="breadcrumb-item" href="/course/list">全部课程</a> <i
					class="icon-font i-v-right"></i> <a class="breadcrumb-item"
					href="/course/list?mt=1001">IT·互联网</a> <i
					class="icon-font i-v-right"></i> <a class="breadcrumb-item"
					href="/course/list?mt=1001&st=2002">编程语言</a> <i
					class="icon-font i-v-right"></i> <a class="breadcrumb-item"
					href="/course/list?mt=1001&st=2002&tt=3007">Java</a> <i
					class="icon-font i-v-right"></i> <a
					class="breadcrumb-item item--tt" href="/course/361593">${course.cname }</a>
			</nav>


			<div id="js-imgtext" class="imgtext-course "
				auto-test="mod_course_imgtext">

				<div class="img-left--wrap" auto-test="mod_course_imgtext_left">
					<img class="img-left"
						src="${pageContext.request.contextPath}/${course.picture}"
						width="600" height="338" />
					<div id="js-banner-cover" class="banner-cover"></div>



					<ul class="pay-policy">

						<li class="pay-policy-title">服务承诺 :</li>
						<li><a href="//ke.qq.com/faq.html#id=10&anchor=payGuarantee"
							title="带有此标识的课程，平台内的付款由腾讯课堂承诺保障" target="_blank"> <i
								class="icon-policy"></i>支付保障
						</a></li>

						<li><a
							href="//ke.qq.com/faq.html#id=10&anchor=refundEverytime"
							title="带有此标识的课程，平台内付费用户在课程开始前可无条件退款" target="_blank"> <i
								class="refund-icon1"></i>开课前随时退
						</a></li>

					</ul>

				</div>

				<div class="text-right text-right--pay"
					auto-test="mod_course_imgtext_right">

					<h1 style="font-size: 24px; text-align: left">${course.cname }</h1>
					<div class="tt-below-line">
						<span id="js-statistics-apply" class="line-item statistics-apply">
							63人 购买 </span> <i class="icon-sep"></i> <span
							class="line-item statistics-rate"> 好评度 <span
							class="rate-num"> 100% </span>
						</span> <i class="icon-sep"></i>
						<div class="line-item item--share" id="js_share">
							<i class="icon-font i-share"></i>分享
							<div class="hover-tips tips--share" id="js_share_panel">
								<ul class="share-list">
									<li class="share-qq icon-qq" data-to="qq"></li>
									<li class="share-qzone icon-qzone" data-to="qzone"></li>
									<li class="share-weixin icon-weixin" data-to="weixin"></li>
								</ul>
							</div>
						</div>
						<span class="line-item btn-favorite " id="js_fav"> <i
							class="icon-font i-heart"></i>收藏
						</span> <span class="line-item sale-btn"></span>


					</div>
					<!-- 班级 -->



					<div class="course-tag ">
						<div class="f-dropdown">
							<input type="checkbox" id="checkbox_toggle"> <label
								class="nor-link not-sel expand" for="checkbox_toggle"> <span
								class="txt">全部</span><i class="icon-v icon-font i-v-bottom"></i>
							</label>
							<div class="f-dropdown-content">
								<ul>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22JavaEE%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22JavaEE%22%5D%5D&ver1=$361593"
										title="JavaEE"><li>JavaEE</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22JavaWeb%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22JavaWeb%22%5D%5D&ver1=$361593"
										title="JavaWeb"><li>JavaWeb</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22MyBatis%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22MyBatis%22%5D%5D&ver1=$361593"
										title="MyBatis"><li>MyBatis</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22Maven%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22Maven%22%5D%5D&ver1=$361593"
										title="Maven"><li>Maven</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E9%A1%B9%E7%9B%AE%E5%AE%9E%E6%88%98%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E9%A1%B9%E7%9B%AE%E5%AE%9E%E6%88%98%22%5D%5D&ver1=$361593"
										title="项目实战"><li>项目实战</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E8%BF%9B%E9%98%B6%E8%AF%BE%E7%A8%8B%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E8%BF%9B%E9%98%B6%E8%AF%BE%E7%A8%8B%22%5D%5D&ver1=$361593"
										title="进阶课程"><li>进阶课程</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E5%90%8E%E5%8F%B0%E5%BC%80%E5%8F%91%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E5%90%8E%E5%8F%B0%E5%BC%80%E5%8F%91%22%5D%5D&ver1=$361593"
										title="后台开发"><li>后台开发</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E9%9B%B6%E5%9F%BA%E7%A1%80%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%22%E9%9B%B6%E5%9F%BA%E7%A1%80%22%5D%5D&ver1=$361593"
										title="零基础"><li>零基础</li></a>

									<a
										href="/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%220-3%E5%B9%B4%E5%B7%A5%E4%BD%9C%E7%BB%8F%E9%AA%8C%22%5D%5D"
										report-tdw="module=course_tag&action=click&obj1=all&obj2=/course/list?mt=1001&amp;st=2002&amp;tt=3007&amp;label_filter=%5B%5B%220-3%E5%B9%B4%E5%B7%A5%E4%BD%9C%E7%BB%8F%E9%AA%8C%22%5D%5D&ver1=$361593"
										title="0-3年工作经验"><li>0-3年工作经验</li></a>

								</ul>
							</div>
						</div>
					</div>


					<!-- 班级 -->


					<div class="course-class course-class--one">
						<div class="class-tt-list-wrap">
							<ul class="class-tt-list" id="js_term_master">

								<li class="js-term-item active" data-idx="0"
									data-termid="100429761"><span class="item-name"
									title="ssm项目">java</span></li>

							</ul>
							<span class="class-more"><i class="icon-font i-v-left"></i>更多班级<i
								class="icon-font i-v-right"></i></span>
						</div>
						<div class="class-content-list ">

							<div
								class="class-content js-term-item js-term-baseinfo class-content--no-des active"
								data-idx="0" data-termid="100429761">
								<h2 class="hidden-clip">Java</h2>
								<p class="class-date">支持随到随学，23年12月过期</p>

								<i class="icon-class-applyed"></i>
							</div>

						</div>
					</div>

					<!-- 价格 -->
					<div class="js-course-price normal-course course-price active"
						data-termid="100429761">
						<div class="course-price-groupbuy js-course-price-groupbuy"></div>
						<div class="course-price-partner js-course-price-partner"></div>
						<p class="course-price-info ">

							<span class="price " style="float: left">免费</span>

						</p>
						<div
							class="course-price-discount course-jd-tags js-course-jd-tags"
							style="display: none;">
							<i></i>
							<p>支持分期付款</p>
						</div>
						<div class="course-price-discount js-course-price-discount">
							<div class="course-coupon js-coupon-wrap hide"></div>
						</div>
					</div>


					<div class="oper-bar" id="js_btn_bar">
						<c:if test="${isJoin}">
						<a href="${pageContext.request.contextPath}/course/${course.id}/list">
							<span
							class="btn-join js-apply-btn btn-default js-apply-btn-task "
							style="float: left">立即学习 </span>
						</a>
						<form id="unJoinForm" action="${pageContext.request.contextPath}/unJoinCourse" method="post">
							<input type="hidden" name="courseId" value="${course.id }">
							<button id="unJoinButton"
							class="btn-join btn-default.disabled"
							style="float:left;margin-left:30px;">取消参加</button>
						</form>	
						</c:if>
						<c:if test="${!isJoin}">
						<form id="joinForm" action="${pageContext.request.contextPath}/joinCourse" method="post">
							<input type="hidden" name="courseId" value="${course.id }">
							<button id="joinButton"
							class="btn-join btn-default"
							style="float:left">参加课程</button>
						</form>	
						</c:if>
					</div>
					<div id="js-partner" class="course-partner"></div>
				</div>
			</div>


		</div>
	</section>

	<!--groups-->
	<section class="group-list-wrapper inner-center js-group-list hide"></section>

	<!--package-->

	<!--main-->
	<section id="js-section-main" class="section-main">
		<div class="inner-center clearfix">
			<main class="main">
			<div class="content tabs">
				<div class="tabs-tt-bar js_tab js-tab-nav" id="js_tab">
					<h2 class="tabs-tt active" ref="js_basic_tab">课程概述</h2>

				</div>

				<div class="tabs-content" id="js_basic_tab">


					<div class="guide-bd">
						<table class="tb-course">
							<tbody>
								<tr>
									<th>课程简介</th>
									<td>${course.introduction }</td>
								</tr>
								<tr>
									<th>资料下载</th>
									<td><a href="${pageContext.request.contextPath}/course/${course.id}/download">点此下载</a></td>
								</tr>

							</tbody>
						</table>

					</div>

				</div>

				<div class="tabs-content hide" id="js_dir_tab"></div>

				<div class="tabs-content hide" id="js_comment_tab">
					<div class="sm-cont sm-main-inner ">
						<div class="mod-comments" id="js-comments">
							<div class="mod-comments-list" id="js-comments-list"></div>
						</div>
					</div>
				</div>
			</div>

			</main>

			<aside class="aside-right" auto-test="mod_course_aside">
				<div class="aside-recommend hide"></div>
				<div class="aside-blocks">
					<div class="aside-block block--agency"
						auto-test="mod_course_aside_agency">
						<div class="block-bd">
							<h4 class="agency-tt">

								<a class="tt-link js-agency-name"
									title="${course.user.username }" target="_blank"
									style="float: left; margin-top: -10px">课程老师：
									${course.user.username } </a>

							</h4>
						</div>

						<div class="agency-summary" style="margin-top: 50px">JavaWeb开发十年开发经验！</div>
					</div>
				</div>
		</div>
		<div class="aside-course hide"></div>
		</div>
	</section>
	<%@ include file="/WEB-INF/jsp/footer.jsp" %>
	<script
		src="${pageContext.request.contextPath}/static/js/join.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/jquery/jquery.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/static/lib/bootstrap/js/bootstrap.min.js"></script>

</body>
</html>