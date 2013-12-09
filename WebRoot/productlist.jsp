<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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

</head>
<body>
	<div class="head" style="width: 100%">
		<div class="headmenu">
			<img alt="logo" src="image/logo.png">
		</div>
		<div class="headcontent_" style="background-image: url('image/headbg.png');float: left;width: 1%;"></div>
		<div class="headcontent" style="background-image: url('image/headbg2.png');width: 98%;background-repeat: repeat-x;float: left;">
			<div class="menu_nav">
				<ul class="nav_content">
					<li class="current"><a href="#"><span>首頁</span></a></li>
					<li><a href="#"><span>百老匯</span></a></li>
					<li><a href="#"><span>蘇寧香港</span></a></li>
					<li><a href="#"><span>豐澤</span></a></li>
				</ul>
				<div class="menu_nav_right"></div>
			</div>
		</div>
		<div class="headcontent_" style="background-image: url('image/headbg3.png');float: right;width: 1%"></div>
	</div>
	<%@include file="leftmenu.jsp"%>
	<div class="product_class_title_list">
		<s:property value="#request.type"/>
	</div>
	<div class="product_class_list">
		<s:iterator value="#request.classlist" var="name">
			<s:if test="#name.description == #request.subtype">
				<font style="display: inline;background-color: #6bb6fc;color: #FFFFFF">
					<s:property value="#name.description"/>
				</font>&nbsp;&nbsp;&nbsp;
			</s:if><s:else>
				<a href="list.action?type2=<s:property value="#name.name"/>"><s:property value="#name.description"/></a>&nbsp;&nbsp;&nbsp;
			</s:else>
		</s:iterator>
	</div>
	<div class="product_list">
		<s:iterator value="#request.pList" var="pro">
		<div class="product_img">
			<img alt="" src="<s:property value="#pro.image"/>" onclick="opendetail('')">
			<div style="margin-top: 10px">
<%--			<a href="#">--%>
				<s:property value="#pro.brand"/>&nbsp;<s:property value="#pro.model"/>
<%--			</a>--%>
			</div>
		</div>
		</s:iterator>
	</div>
	
<script type="text/javascript">
	function opendetail(id) {
		
	}
</script>
</body>
</html>