<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<div class="widget">
	<h4 class="title">标签云</h4>
	<div class="content tag-cloud">
		<c:forEach items="${righttag}" var="tags">
			<a href="javascript:void(0)" onclick="test(${tags.id});">${tags.tagname}</a>
		</c:forEach>
		<a href="/qizaodian/readerShowTags.do">...</a>
	</div>
</div>

