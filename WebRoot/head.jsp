<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
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
<script type="text/javascript">
	function search(){
		var name = document.getElementById("sname").value;
		if(name != '' && name != null)
			window.location.href = "search.action?n=" + name;
	}
</script>
<body>
	<div class="head" style="width: 100%">
		<div>
			<img alt="logo" src="image/logo.png">
			<input type="button" value="搜索" style="height:30px;width:50px;float: right;margin-top: 15px;margin-right: 400px;"
				onclick="search()">
			<input id="sname" type="text" value="<%=request.getAttribute("sname")==null?"":request.getAttribute("sname") %>"
				style="width:400px;height:30px;float: right;margin-top: 15px;padding-left: 10px">
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
