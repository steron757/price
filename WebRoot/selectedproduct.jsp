<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%@ page import="com.model.enums.Retailer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<meta http-equiv="Content-Type" content="text/html; charset=GB18030">
<title></title>
<base href="<%=basePath%>">
<link media=screen href="<%=basePath%>/css/leftmenu.css" type=text/css rel=stylesheet>
<link media=screen href="<%=basePath%>/css/main.css" type=text/css rel=stylesheet>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />

<script src="<%=basePath%>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/js/leftmenu.js"></script>
<style type="text/css">

</style>
<%
	String currentPage = String.valueOf(request.getAttribute("currentPage") == null ? "1" : request.getAttribute("currentPage"));
	String type1 = String.valueOf(request.getAttribute("type1") == null ? "" : request.getAttribute("type1"));
	String type2 = String.valueOf(request.getAttribute("type2") == null ? "" : request.getAttribute("type2"));
	String pageCount = String.valueOf(request.getAttribute("pageCount") == null ? "0" : request.getAttribute("pageCount"));
%>
</head>
<body>
	<%@include file="head.jsp"%>
	<%@include file="leftmenu.jsp"%>
	<div class="product_class_title_list">
		<a href="javascript:history.go(-1);" >返回</a>
	</div>

	<s:if test="#request.similar.size > 0">
		<div class="product_list">
			<!-- Broadway -->
			<div><img src="<%=basePath%>/image/broadway_logo.png" width="100px"/></div>
			<s:iterator value="#request.similar" var="pro">
				<s:if test="#pro.retailer=='Broadway'">
					<div class="product_img">
						<a href="<s:property value="#pro.link"/>" target="blank">
							<img alt="" src="<s:property value="#pro.image"/>" ></a>
						<div style="margin-top: 10px;width: 150px;text-align: center;">
							<s:property value="#pro.brand"/>&nbsp;<s:property value="#pro.model"/>
						</div>
						<div style="text-align: center;color: red"><s:property value="#pro.price"/></div>
					</div>
				</s:if>
			</s:iterator>
			<hr>
			<!-- Suning -->
			<div><img src="<%=basePath%>/image/suning-logo.jpg" width="150px"/></div>
			<s:iterator value="#request.similar" var="pro">
				<s:if test="#pro.retailer=='SuningHK'">
					<div class="product_img">
						<a href="<s:property value="#pro.link"/>" target="blank">
							<img alt="" src="<s:property value="#pro.image"/>" ></a>
						<div style="margin-top: 10px;width: 150px;text-align: center;">
							<s:property value="#pro.brand"/>&nbsp;<s:property value="#pro.model"/>
						</div>
						<div style="text-align: center;color: red"><s:property value="#pro.price"/></div>
					</div>
				</s:if>
			</s:iterator>
			<hr>
			<!-- Fortress -->
			<div><img src="<%=basePath%>/image/fortress-logo.jpg" width="180px"/></div>
			<s:iterator value="#request.similar" var="pro">
				<s:if test="#pro.retailer=='Fortress'">
					<div class="product_img">
						<a href="<s:property value="#pro.link"/>" target="blank">
							<img alt="" src="<s:property value="#pro.image"/>" ></a>
						<div style="margin-top: 10px;width: 150px;text-align: center;">
							<s:property value="#pro.brand"/>&nbsp;<s:property value="#pro.model"/>
						</div>
						<div style="text-align: center;color: red"><s:property value="#pro.price"/></div>
					</div>
				</s:if>
			</s:iterator>
		</div>
	</s:if>
	<s:else>
		<div class="product_list">没有找到记录</div>
	</s:else>
</body>
</html>