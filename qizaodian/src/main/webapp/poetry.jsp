<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
<title>书摘推荐</title>
<meta name="keywords"
	content="java爬虫,爬虫,起早点,起早点爬虫,java爬虫视频,webmagic,java爬虫教程">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/screen.css">
<link rel="stylesheet" type="text/css" href="./css/footer2.css">
</head>
<body class="post-template">
	<!-- start header -->
	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="h1_text">
						<span class="hide">Qizaodian - </span>你不该一直敲代码的，看看这里
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
							<li><a href="/qizaodian/readShowTitles.do">主 页</a></li>
							<li><a href="callme.jsp">联系我们</a></li>
							<li><a href="question.jsp">常见问题</a></li>
							<li><a href="disclaimer.jsp">免责申明</a></li>
							<li><a href="download.jsp">下载</a></li>
							<li><a href="http://weibo.com/u/5595062325" target="_blank">微博</a></li>
							<li><a href="weixin.jsp">项目</a></li>
							<li class="nav-current"><a
								href="/qizaodian/readerPoetrys.do" target="_blank">书摘推荐</a></li>
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
							<h1 class="post-title">往期书摘</h1>
						</header>
						<section class="post-content widget">
							<div class="tag-cloud">
									<%-- <a href="/qizaodian/readerShowPoetryTextById.do?id=${poetry.id}"
										onclick="test(${poetry.id});">${poetry.title}</a>
									<br /> --%>
									<ul style="width:680px;">
										<c:forEach items="${poetry}" var="poetry">
											<li style="width:680px;list-style: none;float:left;"><a
												style="border:none; display: block;width:430px;float:left;"
												href="/qizaodian/readerShowPoetryTextById.do?id=${poetry.id}"
												onclick="test(${poetry.id});">${poetry.title}</a> <a
												style="border:none; display:block;width:150px;float:right;"
												class="date"><fmt:formatDate value="${poetry.newstime}"
														pattern="yyyy-MM-dd" type="date" /></a></li>
										</c:forEach>
									</ul>
							</div>
						</section>
					</article>
				</main>
				<aside class="col-md-4 sidebar">
					<%-- <jsp:include page="rightqu.jsp" />
					<jsp:include page="rightdown.jsp" />
					<div id="tag"></div> --%>
					<div id="poetry"></div>
				</aside>
			</div>
		</div>
	</section>
	<jsp:include page="footer2.jsp" />
	<a style="display: none;" href="#" id="back-to-top"><i
		class="fa fa-angle-up"></i></a>
	<script src="./js/jquery.min.js" type="text/javascript"></script>
	<script src="./js/bootstrap.min.js" type="text/javascript"></script>
	<script src="./js/tag.js" type="text/javascript"></script>
	<script src="./js/poetry.js" type="text/javascript"></script>
	<script src="./js/top.js" type="text/javascript"></script>
	<script src="./js/baidu.js" type="text/javascript"></script>
</body>
</html>