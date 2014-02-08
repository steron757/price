<%@page import="com.model.enums.Subcategory"%>
<%@page import="com.model.enums.ProductType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"> 
<base href="<%=basePath%>">
<link media=screen href="<%=basePath%>/css/leftmenu.css" type=text/css rel=stylesheet>
<link media=screen href="<%=basePath%>/css/main.css" type=text/css rel=stylesheet>
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/demo.css" />
<link rel="stylesheet" type="text/css" href="<%=basePath%>/css/style.css" />

<script src="<%=basePath%>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>/js/jquery.gallery.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/modernizr.custom.53451.js"></script>
<script type="text/javascript" src="<%=basePath%>/js/leftmenu.js"></script>

</head>

<body>
	<% 
		String r = request.getParameter("r");  
		String tt = request.getParameter("t");  
		if(tt != null) r = tt; 
		if(r == null || "".equals(r)) r = "0"; 
	%>
	<%@include file="head.jsp"%>
	<script>
		for(var i=0;i<=3;i++){
			document.getElementById('r'+i).className='';
		}
		document.getElementById('r'+'<%=r%>').className='current';
	</script>
	<div class="content">
		<%@include file="leftmenu.jsp"%>
		<div class="rightcontent">
	        <div class="container">
				<section id="dg-container" class="dg-container">
					<div class="dg-wrapper">
						<a href="#"><img src="image/product/1.jpg" alt="Slide 1"></a>
						<a href="#"><img src="image/product/2.jpg" alt="Slide 2"></a>
						<a href="#"><img src="image/product/3.jpg" alt="Slide 3"></a>
						<a href="#"><img src="image/product/4.png" alt="Slide 4"></a>
						<a href="#"><img src="image/product/5.jpg" alt="Slide 5"></a>
						<a href="#"><img src="image/product/6.jpg" alt="Slide 6"></a>
						<a href="#"><img src="image/product/7.jpg" alt="Slide 7"></a>
					</div>
					<nav>	
						<span class="dg-prev">&lt;</span>
						<span class="dg-next">&gt;</span>
					</nav>
				</section>
			</div>
			<iframe src="hotpic.jsp" frameborder="0" scrolling="no" width="80%" height="300px" style="float: left;"></iframe>
		</div>
	</div>
	<div class="bottom">
		<%@include file="foot.jsp"%>
	</div>
	<script type="text/javascript">
		/** 顶部图片滚动 */
		$(function() {
			$('#dg-container').gallery();
		});
	</script>
</body>
</html>
