<%@page import="com.model.enums.Subcategory"%>
<%@page import="com.model.enums.ProductType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
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


<script type="text/javascript">

	var l;
	var t;
	var menuItem;
	var _index;
	$(document).ready(menu_init);
	function menu_init() {
		//var mod_menu = $(".mod-menu");//导航模块区
		menu();//执行展开二级菜单函
		//setTimeout(menu(),1000)
	}
	var menu = function() {
		menuItem = $(".menu-item li");//选择导航列表
		menuItem.each(menu_each);/*导航菜单*/
		menuItem.mouseleave(menu1_leave);/*导航菜单*/
		$(".mod-menu").mouseleave(menu2_mouse_leave);
	};//展开二级菜单
	var menu_each = function() {
		$(this).mouseenter(menu_mouse_enter);
	};
	var menu_mouse_enter = function() {
		l = $(this);//获取当前鼠标滑过的列表的顶部坐标
		_index = $(this).index();//获取当前选择菜单列表的索引
		menu_mouse_enter_show(_index);
	};
	function menu_mouse_enter_show(_index) {
		var y = l.position().top + 1;//获取当前鼠标滑过的列表的顶部坐标
		$(".menu-cont").show();
		$(".menu-cont").css("top", y);//需要显示的对应索引内容
		l.addClass("mouse-bg").siblings().removeClass("mouse-bg");
		$(".menu-cont>div").eq(_index).show().siblings().hide();
	}
	var menu1_leave = function() {
		clearTimeout(t);
	};
	var menu2_mouse_leave = function() {
		$(".menu-cont").hide();
		menuItem.removeClass("mouse-bg");
	};
</script>

</head>

<body>
	<div class="head" style="width: 100%">
		<div class="headmenu">
			<img alt="logo" src="image/logo.png">
			<div style="float: right;">
				<input type="text" id="name"><input type="text" id="password">
			</div>
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
	<div class="content">
		<div class="leftmenu">
			<div class="mod-menu f-l">
				<div id="column-left">
					<ul class="menu-item">
						<% for(ProductType p : ProductType.values()){
								if(p == ProductType.NULL)
									break;
						%>
						<li class=""><a href="#"><%=p.getDescription() %></a></li>
						<% } %>
					</ul>
					<!--一级菜单列表-->
					<div class="menu-cont hide" style="display: none; top: 241px;">
						<% for(ProductType p : ProductType.values()){
								if(p == ProductType.NULL) break; %>
						<div class="menu-cont-list" style="display: none;">
							<ul>
								<% List<Subcategory> slist = ProductType.getSubcategories(p);
									for(Subcategory s : slist){ 
										if(s == Subcategory.NULL)break;%>
									<li><%=s.getDescription() %></li>
								<% } %>
							</ul>
						</div>
						<% } %>
					</div>
				</div>
				<!--二级菜单内容-->
			</div>
		</div>
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
			<iframe src="hotpic.jsp" frameborder="0" scrolling="no" width="80%" height="600px" style="float: left;"></iframe>
		</div>

	</div>
	<div class="bottom"></div>
	<script type="text/javascript">
		/** 顶部图片滚动 */
		$(function() {
			$('#dg-container').gallery();
		});
	</script>
</body>
</html>
