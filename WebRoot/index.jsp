<%@page import="com.model.enums.Subcategory"%>
<%@page import="com.model.enums.ProductType"%>
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
		//var mod_menu = $(".mod-menu");//����ģ����
		menu();//ִ��չ�������˵���
		//setTimeout(menu(),1000)
	}
	var menu = function() {
		menuItem = $(".menu-item li");//ѡ�񵼺��б�
		menuItem.each(menu_each);/*�����˵�*/
		menuItem.mouseleave(menu1_leave);/*�����˵�*/
		$(".mod-menu").mouseleave(menu2_mouse_leave);
	};//չ�������˵�
	var menu_each = function() {
		$(this).mouseenter(menu_mouse_enter);
	};
	var menu_mouse_enter = function() {
		l = $(this);//��ȡ��ǰ��껬�����б�Ķ�������
		_index = $(this).index();//��ȡ��ǰѡ��˵��б������
		menu_mouse_enter_show(_index);
	};
	function menu_mouse_enter_show(_index) {
		var y = l.position().top + 1;//��ȡ��ǰ��껬�����б�Ķ�������
		$(".menu-cont").show();
		$(".menu-cont").css("top", y);//��Ҫ��ʾ�Ķ�Ӧ��������
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
					<li class="current"><a href="#"><span>��ҳ</span></a></li>
					<li><a href="#"><span>���υR</span></a></li>
					<li><a href="#"><span>�K�����</span></a></li>
					<li><a href="#"><span>�S��</span></a></li>
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
						%>		<li class=""><a href="#"><%=p.getDescription() %></a></li>
						<% } %>
					</ul>
					<!--һ���˵��б�-->
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
				<!--�����˵�����-->
			</div>
		</div>
		<div class="rightcontent"></div>
	</div>
	<div class="bottom"></div>
</body>
</html>
