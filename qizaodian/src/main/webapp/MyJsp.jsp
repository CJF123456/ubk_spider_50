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

<title>${title}--起早点爬虫文学社区</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="description" content="Qizaodian-全网首家最系统的java爬虫视频网站" />
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
								href="/qizaodian/readerPoetrys.do">文学社区</a></li>
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
							<h1 class="post-title">HttpClient 4.5版本设置连接超时时间</h1>
							<section class="post-meta">
								<span class="author">作者：<a href="">CJF</a></span> •
								<time class="post-date" title="">2017-03-28</time>
							</section>
						</header>
						<section class="post-content">
							<section class="post-content">
	<div>
	<p class="c1">&nbsp; &nbsp; &nbsp; &nbsp; 目前，主流的建站方式都是php和第三方建站，使用java建站的寥寥不幾，但是，少不代表沒有</p><p class="c1">代码如下：</p><pre class="brush:java;toolbar: true; auto-links: false; hljs go"><code class="hljs go">
		public boolean preHandle(HttpServletRequest request,HttpServletResponse response, Object handler) throws Exception {
		StringBuffer url = request.getRequestURL();
		System.out.println(request.getContextPath());
		// 实现灵活配置处理业务请求跳转过滤;请求路径规范统一，方便处理:以下是业务不需要校验
		int dl = url.indexOf("http://qizaodian");
		if(dl>-1){
			response.setStatus(301);
			response.setHeader("Location","http://qizaodian.com");
			response.sendRedirect("http://www.qizaodian.com");
			return false;
		}

		return true;
	}</code></pre>
	
	<p>其实主要添加如下代码就可以了</p>
	 <pre class="brush:java;toolbar: true; auto-links: false; hljs go"><code class="hljs go">
	 response.setStatus(301);
	 response.setHeader("Location","http://qizaodian.com");
	 response.sendRedirect("http://www.qizaodian.com");</code>
			</pre>
	<p>以上观点仅代表个人，如有异议请联系客服。</p>
	</div>			</section>
						</section>

					</article>

				</main>

				<aside cla ss="col-md-4 sidebar">
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