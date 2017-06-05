<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<div class="widget">
	<h4 class="title">书摘推荐</h4>
	<div class="content tag-cloud">
		<c:forEach items="${rightpoetry}" var="rightpoetry">
			<a href="/qizaodian/readerShowPoetryTextById.do?id=${rightpoetry.id}" onclick="test1(${rightpoetry.id});">${rightpoetry.title}</a>
		</c:forEach>
		<a href="/qizaodian/readerPoetrys.do">...</a>
	</div>
</div>