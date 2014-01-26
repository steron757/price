<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path1 = request.getContextPath();
	String basePath1 = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path1+ "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath1%>">

<link media=screen href="<%=basePath1%>/css/leftmenu.css" type=text/css rel=stylesheet>
<link media=screen href="<%=basePath1%>/css/main.css" type=text/css rel=stylesheet>
<link rel="stylesheet" type="text/css" href="<%=basePath1%>/css/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath1%>/css/style.css" />
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
					<li id="r0" class="current"><a href="#"><span>首頁</span></a></li>
					<li id="r1"><a href="list!ret.action?r=1"><span>百老匯</span></a></li>
					<li id="r2"><a href="list!ret.action?r=2"><span>蘇寧香港</span></a></li>
					<li id="r3"><a href="list!ret.action?r=3"><span>豐澤</span></a></li>
				</ul>
				<div class="menu_nav_right"></div>
			</div>
		</div>
		<div class="headcontent_" style="background-image: url('image/headbg3.png');float: right;width: 1%"></div>
	</div>
</body>
</html>
