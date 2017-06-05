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
<title>${title}--起早点爬虫文学社区</title>
<meta name="keywords"
	content="java爬虫,爬虫,起早点,起早点爬虫,java爬虫视频,webmagic,java爬虫教程">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css">
<link rel="stylesheet" href="./css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="./css/screen.css">
<link rel="stylesheet" type="text/css" href="./css/footer2.css">
<style type="text/css">
div#Cnt-Main-Article-QQ {
	line-height: 28px;
}

p img {
	display: block;
	margin: 0 auto;
}

v {
	display: block;
	font-size: 10px;
	text-align: center;
	margin: 0 auto;
	font-size: 10px;
	font-weight: bold;
}

p {
	text-indent: 2em;
	width: 680px;
}
p.remarks{
   font-size: 10px;
   font-weight: bold;
}
</style>
</head>
<body class="post-template">

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
							<li><a href="question.jsp">常见问题</a></li>
							<li><a href="disclaimer.jsp">免责申明</a></li>
							<li><a href="download.jsp">下载</a></li>
							<li><a href="http://weibo.com/u/5595062325">微博</a></li>
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
					<article class="post">

						<header class="post-head">
							<h1 class="post-title">${title}</h1>
							<section class="post-meta">
								<span class="author">作者：<a href="">${author} </a></span> •
								<time class="post-date" title=""><fmt:formatDate value="${newstime}" pattern="yyyy-MM-dd" type="date"/></time>
							</section>
						</header>
						<section class="post-content">${text}
						<br>
						<p class="remarks">本文为腾讯文化签约的合作方内容,<a target="_black" href="${url}">点击跳转原网站</a></p>
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
	<script src="./js/top.js" type="text/javascript"></script>
	<script src="./js/baidu.js" type="text/javascript"></script>

</body>
</html>