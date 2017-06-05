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
<title>起早点爬虫视频-sougou微信采集</title>
<meta name="keywords"
	content="java爬虫,爬虫,起早点,起早点爬虫,java爬虫视频,webmagic,java爬虫教程">
<link rel="stylesheet"
	href="http://cdn.bootcss.com/font-awesome/4.3.0/css/font-awesome.min.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" href="./css/bootstrap.min.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="./css/screen.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="./css/footer2.css?v=<%=System.currentTimeMillis()%>">
<link rel="stylesheet" type="text/css" href="./css/weixin.css?v=<%=System.currentTimeMillis()%>">
</head>
<body
	class="post-template page-template page page-download page-template-download">
	<!-- start header -->
	<header class="main-header">
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<h1 class="h1_text">
						<span class="hide">Qizaodian - </span>搜狗微信文章、公众号采集演示Demo
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
							<li><a target="_blank" href="http://weibo.com/u/5595062325">微博</a>
							</li>
							<li class="nav-current"><a href="weixin.jsp">项目</a></li>
							<li><a href="/qizaodian/readerPoetrys.do">文学社区</a></li>
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
					<div class="search_input_wrap ">
						<!-- 搜索框区域 -->
						<div class="searchInp_box">
							<input id="keyword" class="searchInp_form" placeholder="输入关键词"
								maxlength="10" autocomplete="off" required /><a id="weiSearch"
								class="btn btn-default btn-block searchBtn" node-type="submit">搜&nbsp;文&nbsp;章</a>
							<a id="weiSearchNumber"
								class="btn btn-default btn-block searchBtnNumber"
								node-type="submit">搜公众号</a>
						</div>
						<div class="alert alert-warning alert-dismissible fade in"
							role="alert" id="alert_" style="display:none;">
							<strong>输入不能为空</strong>
						</div>
						<div class="searchBtn_box"></div>
					</div>
					<!-- 数据加载中 -->
					<!--显示内容区域 -->
					<div class="content" id="show">
						<!-- <div class="load_box" id="load_" >
						<p><span><img src="images/5-121204193R5-50.gif"></p></span>
					</div> -->
						<article class="post">
							<div>
								<div class="head">
									<h5 id="title" class="titles">
										<a id="post" target="_black" href="">采集须知：</a>
									</h5>
								</div>
								<div class="post-content">
									<p>此项目仅是微信公众号和微信文章采集的一部分演示，如果需要完整项目请联系客服</p>
									<p>微信文章测试采集第11-15页，因为从第11页开始就需要登录</p>
									<p>
										微信公众号测试采集只采集前两页，因为前两页是按关键词搜出来的大<i class="weixinV"></i>
									</p>
									<p>为了防止采集过于频繁给源网站造成压力，有休眠，请大家内心等待一小会</p>
								</div>
								<div class="post-meta">
									<span class="author"><a id="post" target="_black"
										href="http://www.qizaodian.com/callme.jsp">起早点团队</a>&nbsp;&nbsp;<a
										id="post-date">2016-12-01</a> </span>
								</div>
							</div>
						</article>
						<article class="post">
							<div class="gzh-box2">
								<div class="img-box">
									<a target="_blank"> </a>
								</div>
								<div class="txt-box">
									<p class="tit">
										<a target="_black"
											href="">二位先生</a><i
											class="weixinV"></i>
									</p>
									<p class="info">
										微信号: <label name="em_weixinhao">RainDong314</label>
									</p>
								</div>
							</div>
							<dl>
								<dt>功能介绍：</dt>
								<dd>年轻人的思想集散地</dd>
							</dl>
							<dl>
								<dt>微信认证：</dt>
								<dd>原创</dd>
							</dl>
							<dl>
								<dt>最近文章：</dt>
								<dd>
									<a href="https://mp.weixin.qq.com/s/1FgD6zCzHL21Actzs9jVbQ"
										target="_black">离家的车</a> <span>2017-02-12</span>
								</dd>
							</dl>
						</article>
						<article class="post">
							<div>
								<div class="head">
									<h5 id="title" class="titles">
										<a id="post" target="_black"
											href="http://mp.weixin.qq.com/s/u5s3HIp3hTZYFKRBKb8j2g">向外张望却未曾向内审视</a>
									</h5>
								</div>
								<div class="post-content">
									<p>荣格说过:向外张望是做梦，向内审视才是清醒，起初不是很体会这句话，直到反复“做梦”到如今才好像明白一二。</p>
								</div>
								<div class="post-meta">
									<span class="author"><a id="post" target="_black"
										href="">二位先生</a>&nbsp;&nbsp;<a id="post-date">2017-03-03</a> </span>
								</div>
							</div>
						</article>
						<article class="post">
							<div>
								<div class="head">
									<h5 id="title" class="titles">
										<a id="post" target="_black"
											href="http://mp.weixin.qq.com/s/ID428kjJS7kzz7eq-v08CA">愿你是我良人</a>
									</h5>
								</div>
								<div class="post-content">
									<p>父母在，不远嫁</p>
									<p>我终究是食言了</p>
									<p>望你是我的良人</p>
									<p>能让我的父母下定决心</p>
									<p>忍痛割爱与你</p>
								</div>
								<div class="post-meta">
									<span class="author"><a id="post" target="_black"
										href="">二位先生</a>&nbsp;&nbsp;<a id="post-date">2017-03-03</a> </span>
								</div>
							</div>
						</article>
					</div>
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
		class="fa fa-angle-up"></i> </a>
	<script src="./js/jquery.min.js" type="text/javascript"></script>
	<script src="./js/bootstrap.min.js" type="text/javascript"></script>
	<script src="./js/tag.js" type="text/javascript"></script>
	<script src="./js/top.js" type="text/javascript"></script>
	<script src="./js/baidu.js" type="text/javascript"></script>
<script type="text/javascript">$(document).ready(function(){$("#weiSearch").click(function(){if($("#keyword").val()==''){$('#alert_').show();}else{$("#show").html("");var temp1="<div class='load_box' id='load_'><p><span><img src='images/5-121204193R5-50.gif'></p></span></div>";$("#show").html(temp1);var keyword=$("#keyword").val();var htmlobj=$.ajax({url:"readerWinxinByKeyword.do?keyword="+keyword,async:false,beforeSend:function(){$("#show").html(temp1);},success:function(mag){var jsonobj=eval("("+mag+")");console.log(jsonobj);var temp="";for(var i=0;i<jsonobj.length;i++){temp=temp+'<article class="post" ><div><div class="head"><h5 id="title" class="titles"><a id="post" target="_black" href="'+jsonobj[i].titleurl+'">'+jsonobj[i].title+'</a></h5></div><div class="post-content"><p>'+jsonobj[i].brief+'</p></div><div class="post-meta"><span class="author"><a id="post" target="_black" href="'+jsonobj[i].authorurl+'">'+jsonobj[i].author+'</a>&nbsp;&nbsp;<a id="post-date">'+jsonobj[i].date+'</a></span></div></div></article>';}if(temp!=""){$("#show").html(temp);}}});};});$("#keyword").blur(function(){if(this.value==''){$('#alert_').show();}else{$('#alert_').hide();}});});$(document).ready(function(){$("#weiSearchNumber").click(function(){if($("#keyword").val()==''){$('#alert_').show();}else{var keyword=$("#keyword").val();var htmlobj=$.ajax({url:"readerWinxinNumberByKeyword.do?keyword="+keyword,async:false});var jsonobj=eval("("+htmlobj.responseText+")");var temp="";for(var i=0;i<jsonobj.length;i++){temp=temp+'<article class="post"><div class="gzh-box2"><div class="img-box"><a target="_blank"></a></div><div class="txt-box"><p class="tit"><a href="'+jsonobj[i].numnameurl+'">'+jsonobj[i].numname+'</a><i class="weixinV"></i></p><p class="info">微信号: <label name="em_weixinhao">'+jsonobj[i].numid+'</label></p></div></div><dl><dt>功能介绍：</dt><dd>'+jsonobj[i].numshow+'</dd></dl><dl><dt>微信认证：</dt><dd>'+jsonobj[i].numauthentic+'</dd></dl><dl><dt>最近文章：</dt><dd><a href="'+jsonobj[i].numdocurl+'">'+jsonobj[i].numdoc+'</a><span>'+jsonobj[i].date+'</span></dd></dl></article>';}$("#show").html(temp);};});$("#keyword").blur(function(){if(this.value==''){$('#alert_').show();}else{$('#alert_').hide();}});});</script>

</body>
</html>