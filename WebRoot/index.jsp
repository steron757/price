<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">
<link media=screen href="<%=basePath%>/css/leftmenu.css" type=text/css rel=stylesheet>
<link media=screen href="<%=basePath%>/css/main.css" type=text/css rel=stylesheet>
<script src="<%=basePath%>/js/jquery-1.10.2.min.js" type="text/javascript"></script>
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
		<div class="headcontent" style="background-image: url('image/headbg2.png');width: 98%;background-repeat: repeat-x;float: left;"></div>
		<div class="headcontent_" style="background-image: url('image/headbg3.png');float: right;width: 1%"></div>
	</div>
	<div class="content">
		<div class="leftmenu">
			<div class="mod-menu f-l">
				<div id="column-left">
					<ul class="menu-item">
						<li class=""><a href="#">Wedding</a></li>
						<li class=""><a href="#">Women's Shoes</a></li>
						<li class=""><a href="#">Accessories</a></li>
					</ul>
					<!--一级菜单列表-->
					<div class="menu-cont hide" style="display: none; top: 241px;">
						<div class="menu-cont-list" style="display: none;">
							<ul>
								<li><h3><a href="#">Wedding Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Bridesmaid Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Wedding Party Dresses</a></h3>
									<div class="menu-list-link">
										<a title="" href="#">Mother of the Brides Dresses</a> <span
											class="long-string">|</span> <a title="" href="#">Flower
											Girl Dresses</a><span class="long-string">|</span> <a title=""
											href="#">Wedding Guest Dresses</a>
									</div>
								</li>
								<li><h3><a href="#">Wedding Accessories</a></h3>
									<div class="menu-list-link">
										<a title="#">Fabric Swatch</a> <span class="long-string">|</span>
										<a title="" href="#">Bridal Lingerie</a> <span
											class="long-string">|</span> <a title="" href="#">Wedding
											Flowers</a> <span class="long-string">|</span> <a title=""
											href="#">Wedding Petticoats</a>
									</div>
								</li>
							</ul>
						</div>
						
						<div class="menu-cont-list" style="display: block;">
							<ul><li><h3><a href="#">Prom Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Military Ball Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Evening Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Cocktail Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Ball Gowns</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Homecoming Dresses</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Little Black Dresses </a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Quinceanera Dresses</a></h3><div class="menu-list-link"></div></li>
							</ul>
						</div>
						
						<div class="menu-cont-list" style="display: none;">
							<ul><li><h3><a href="#">Wigs and Hair extensions</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Fascinators</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Makeup Tools</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Spas and Massagers</a></h3><div class="menu-list-link"></div></li>
								<li><h3><a href="#">Health and Fitness</a></h3><div class="menu-list-link"></div></li>
							</ul>
						</div>
						
					</div>
				</div>
				<!--二级菜单内容-->
			</div>
		</div>
		<div class="rightcontent"></div>
	</div>
	<div class="bottom"></div>
</body>
</html>
