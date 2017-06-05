<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Qizaodian-全网首家最系统的java爬虫视频网站" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>起早点爬虫视频-有分布式部署的爬虫案例吗？</title>
<meta name="keywords"
	content="java爬虫,爬虫,起早点,起早点爬虫,java爬虫视频,webmagic,java爬虫教程">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/screen.css">
<link rel="stylesheet" type="text/css" href="./css/footer2.css">
</head>
<body
	class="post-template page-template page page-download page-template-download">
	<!-- start header -->
	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">

					<h1 class="h1_text">
						<span class="hide">Qizaodian - </span>起早点为你提供时下最系统的是爬虫视频
					</h1>
				</div>
			</div>
		</div>
	</header>
	<!-- end header -->
	<!-- start navigation -->
	<nav class="main-navigation">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div class="navbar-header">
						<span class="nav-toggle-button collapsed" data-toggle="collapse"
							data-target="#main-menu"> <i class="fa fa-bars"></i>
						</span>
					</div>
					<div class="collapse navbar-collapse" id="main-menu">
						<ul class="menu">
							<li><a href="http://www.qizaodian.com/">主 页</a></li>
							<li><a href="callme.jsp">联系我们</a></li>
							<li class="nav-current"><a href="question.jsp">常见问题</a></li>
							<li><a href="disclaimer.jsp">免责申明</a></li>
							<li><a href="download.jsp">下载</a></li>
							<li><a target="_blank" href="http://weibo.com/u/5595062325">微博</a></li>
							<li><a href="weixin.jsp">项目</a></li>
							<li><a href="/qizaodian/readerPoetrys.do" target="_blank">书摘推荐</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</nav>
	<!-- end navigation -->
	<!-- start site's main content area -->
	<section class="content-wrap">
		<div class="container">
			<div class="row">
				<main class="col-md-8 main-content">
					<article class="post page">
						<header class="post-head">
							<h1 class="post-title">常见问题</h1>
						</header>
						<section class="post-content">
							<h4>什么是网络爬虫?</h4>
							<pre>网络爬虫(又被称为网页蜘蛛，网络机器人)<br>是一种按照一定的规则，自动地抓取万维网信息的程序或者脚本<br>另外一些不常使用的名字还有蚂蚁、自动索引、模拟程序或者蠕虫</pre>
							<h4>网络爬虫的工作原理？</h4>
							<pre>1.给爬虫一个种子URL<br>2.获取页面内容并存储，抽取页面中的URL<br>3.将新获取到的URL加入到URL队列中，等待处理<br>4.从URL队列中获取一个URL然后重复步骤2</pre>
							<h4>这个网站主要是用来做什么的？</h4>
							<pre>配合爬虫视频，做图文详解</pre>
							<h4>这个网站会更新最新数据吗？</h4>
							<pre>每天一更新</pre>
							<h4>这个站为什么没有登录注册按钮？</h4>
							<pre>为了给用户更好的体验，不需要登录注册</pre>
							<h4>这个网站主要是用来做什么的？</h4>
							<pre>配合爬虫视频，做图文详解</pre>
							<h4>这个网站会更新最新数据吗？</h4>
							<pre>每天一更新</pre>
							<h4>视频有效期是多久？</h4>
							<pre>永久免费</pre>
						</section>
					</article>
				</main>
				<aside class="col-md-4 sidebar">
					<jsp:include page="rightqu.jsp" />
					<jsp:include page="rightdown.jsp" />
					<div id="tag"></div>
				</aside>

			</div>
		</div>
	</section>

	<jsp:include page="footer2.jsp" />
	<a href="#" id="back-to-top" style="display: none;"><i
		class="fa fa-angle-up"></i></a>
	<script src="./js/jquery.min.js" type="text/javascript"></script>
	<script src="./js/bootstrap.min.js" type="text/javascript"></script>
	<script src="./js/tag.js" type="text/javascript"></script>
	<script src="./js/top.js" type="text/javascript"></script>
	<script src="./js/baidu.js" type="text/javascript"></script>




</body>
</html>