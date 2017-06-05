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
<meta name="baidu-site-verification" content="cl53xmkEkm" />
<title>起早点爬虫视频-全网首家最系统的java爬虫视频网站</title>
<meta name="keywords"
	content="java爬虫,爬虫,起早点,起早点爬虫,java爬虫视频,webmagic,java爬虫教程">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css?v=<%=System.currentTimeMillis()%>"/>
<link rel="stylesheet" href="./css/bootstrap.min.css?v=<%=System.currentTimeMillis()%>"/>
<link rel="stylesheet" type="text/css" href="./css/screen.css?v=<%=System.currentTimeMillis()%>"/>
<link rel="stylesheet" type="text/css" href="./css/footer2.css?v=<%=System.currentTimeMillis()%>"/>
</head>
<body class="home-template">
	<!-- start header -->
	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="h1_text">
						<span class="hide">Qizaodian - </span>起早点为你提供时下最系统的是爬虫视频
					</h1>
				</div>
				<div class="col-sm-12">
					<a href="http://mvnrepository.com/" class="btn btn-default btn-doc"
						target="_blank">maven repository</a> <a
						href="http://www.yeetrack.com/?p=779"
						class="btn btn-default btn-doc" target="_blank">httpclient 4.3
						教程</a> <a href="http://www.open-open.com/jsoup/"
						class="btn btn-default btn-doc" target="_blank">jsoup 中文文档</a> <a
						href="http://webmagic.io/docs/zh/" class="btn btn-default btn-doc"
						target="_blank">webmgic 中文文档</a> <a href="weixin.jsp"
						class="btn btn-default btn-doc" target="_blank">sogou微信采集</a>
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
							<li class="nav-current"><a href="http://www.qizaodian.com/">主
									页</a></li>
							<li><a href="callme.jsp">联系我们</a></li>
							<li><a href="question.jsp">常见问题</a></li>
							<li><a href="disclaimer.jsp">免责申明</a></li>
							<li><a href="download.jsp">下载</a></li>
							<li><a href="http://weibo.com/u/5595062325" target="_blank">微博</a></li>
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
					<c:forEach items="${newsList}" var="news">
						<article class="post">

							<div class="post-head">
								<h1 class="post-title">
									<a href="/qizaodian/readerShowTextById.do?id=${news.id}">${news.title}</a>
								</h1>
								<div class="post-meta">
									<span class="author">作者：<a>CJF</a></span> •
									<time class="post-date" title=" ${news.pubdate}">
										<fmt:formatDate value="${news.pubdate}" pattern="yyyy-MM-dd" type="date" /></time>
								</div>
							</div>
							<div class="featured-media">
								<a href="/qizaodian/readerShowTextById.do?id=${news.id}"><img
									src=" ${news.picurl}" alt="${news.title}"></a>
							</div>
							<div class="post-content">
								<p>${news.brief}</p>
							</div>
							<div class="post-permalink">
								<a href="/qizaodian/readerShowTextById.do?id=${news.id}"
									class="btn btn-default">阅读全文</a>
							</div>
							<div class="post-url">
								<p class="post-down "
									style=" text-shadow: 1px 1px 1px #a2a2a200;">
									源码下载: <a class="" href="">${news.url}</a>
								</p>
							</div>
							<div class="pull-left tag-list" class="fa fa-folder-open-o">
								<i class="fa fa-folder-open-o"></i>
								<c:forEach items="${tags}" var="tag">
									<c:if test="${tag.key==news.id}">
										<c:forEach items="${tag.value}" var="temp">
											<i class="fa fa-folder-open-o1"
												style="font-family: Microsoft YaHei ; text-shadow: 1px 1px 1px #a2a2a200;">
												${temp.tagname}</i>
										</c:forEach>
									</c:if>

								</c:forEach>
							</div>
							<div class="pull-right share"></div>
						</article>
					</c:forEach>
					<c:if test="${single==1}">
						<nav class="pagination" role="navigation">
						
							<a class="older-posts"
									href="/qizaodian/readShowTitles.do?pageNo=${page.pageNo-1}">
									<c:if test="${page.pageNo>1}">
										<i class="fa fa-angle-left"></i>
									</c:if>
								</a> <span class="page-number">第 ${page.pageNo} 页 ⁄ 共
									${page.pageCount}页</span> <a class="older-posts"
									href="/qizaodian/readShowTitles.do?pageNo=${page.pageNo+1}">
									<c:if test="${page.pageCount>page.pageNo}">
										<i class="fa fa-angle-right" id="next"></i>
									</c:if>
								</a>
							
						</nav>
					</c:if>
						
					
				</main>
				<aside class="col-md-4 sidebar">
					<jsp:include page="rightqu.jsp" />
					<jsp:include page="rightdown.jsp" />
					<div id="tag"></div>
					<div id="poetry"></div>
				</aside>
			</div>
		</div>
	</section>

	<footer class="main-footer">
		<div class="container">
			<div class="row">
				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">最新文章</h4>
						<div class="content recent-post">
							<c:forEach items="${newsList}" var="news" begin="0" end="2">
								<div class="recent-single-post">
									<a href="/qizaodian/readerShowTextById.do?id=${news.id}">${news.title}</a>
									<div class="date">${news.pubdate}</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">标签云</h4>
						<div class="content tag-cloud ">
							<c:forEach items="${righttag}" var="tags">
								<a href="javascript:void(0)" onclick="test(${tags.id});">${tags.tagname}</a>
							</c:forEach>
							<a href="/qizaodian/readerShowTags.do">...</a>
						</div>
					</div>
				</div>

				<div class="col-sm-4">
					<div class="widget">
						<h4 class="title">合作伙伴</h4>
						<div class="content tag-cloud friend-links">
							<a href="http://www.bootcss.com/" title="Bootstrap中文网"
								target="_blank">Bootstrap中文网</a> <a
								href="http://www.jquery123.com/" title="jQuery中文网"
								target="_blank">jQuery中文文档</a> <a
								href="http://www.w3school.com.cn/" title="w3school中文网"
								target="_blank">w3school中文网</a>
							<hr>
							<a href="https://www.aliyun.com/" title="阿里云" target="_blank">阿里云</a>
							<a href="https://aws.amazon.com/cn/" title="AWS云服务"
								target="_blank">AWS云服务</a> <a href="http://www.baidu.com/"
								title="百度" target="_blank">百度</a>
						</div>
					</div>
				</div>
			</div>
		</div>
	</footer>

	<jsp:include page="footer2.jsp" />

	<a href="#" id="back-to-top" style="display: block;"><i
		class="fa fa-angle-up"></i></a>



	<script src="./js/jquery.min.js?v=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<script src="./js/bootstrap.min.js?v=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<script src="./js/tag.js?v=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<!-- <script src="./js/poetry.js" type="text/javascript"></script> -->
	<script src="./js/top.js?v=<%=System.currentTimeMillis()%>" type="text/javascript"></script>
	<script src="./js/baidu.js?v=<%=System.currentTimeMillis()%>" type="text/javascript"></script>



</body>
</html>