<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<!--[if lt IE 7]><html class="no-js lt-ie9 lt-ie8 lt-ie7"><![endif]-->
	<!--[if IE 7]><html class="no-js lt-ie9 lt-ie8"><![endif]-->
	<!--[if IE 8]><html class="no-js lt-ie9"><![endif]-->
	<!--[if gt IE 8]><!--><html class="no-js"><!--<![endif]-->
    <head>
        <title>Elastislide - A Responsive Image Carousel</title>
		<meta charset="UTF-8" />
		<link rel="shortcut icon" href="../favicon.ico"> 
        <link rel="stylesheet" type="text/css" href="css/demo.css" />
		<link rel="stylesheet" type="text/css" href="css/scroll/elastislide.css" />
		<link rel="stylesheet" type="text/css" href="css/scroll/custom.css" />
		<script src="js/modernizr.custom.17475.js"></script>
		<script type="text/javascript" src="js/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="js/jquerypp.custom.js"></script>
		<script type="text/javascript" src="js/jquery.elastislide.js"></script>
		<style type="text/css">
			body{ padding:0;font:12px "宋体";  }
			#lib_zzjs_1{width:500px;margin:0px;padding:0px;margin-bottom:15px;}		/*选项卡1*/
			#lib_zzjs_2{width:100%;margin:0px;padding:0px;margin-bottom:15px; }	/*选项卡2*/
			.lib_border_zzjs{border:1px solid #95C9E1;}		/*菜单class*/
			.lib_menu_zzjsnet {height:28px;line-height:28px;position:relative;}
			.lib_menu_zzjsnet ul{margin:0px;padding:0px;list-style:none; position:absolute; top:3px; left:0; margin-left:10px; height:25px;text-align:center;}
			.lib_menu_zzjsnet li{float:left;display:block;cursor:pointer;width:114px;color:#949694;font-weight:bold; margin-right:2px;height:25px;line-height:25px; background-color:#E4F2FD}
			.lib_menu_zzjsnet li.hover{padding:0px;background:#fff;width:116px;border-left:1px solid #95C9E1;border-top:1px solid #95C9E1;border-right:1px solid #95C9E1;color:#739242;height:25px;line-height:25px;}
			.lib_Contentbox_zzjs{clear:both;margin-top:0px; border-top:none;height:200px; text-align:center;padding-top:8px;}
		</style>
    </head>
    <body>
    
    <div id="lib_zzjs_2">
<div class="lib_menu_zzjsnet lib_border_zzjs">
<ul>
   <li id="zzjs_21" onmouseover="settab_zzjsnet('zzjs_2',1,4)" class="hover">站长特效一号</li>
   <li id="zzjs_22" onmouseover="settab_zzjsnet('zzjs_2',2,4)">站长特效二号</li>
   <li id="zzjs_23" onmouseover="settab_zzjsnet('zzjs_2',3,4)">站长特效三号</li>
</ul>
</div>
 <div class="lib_Contentbox_zzjs lib_border_zzjs">
   <div id="zzjs_zzjs_2_1">
	<div class="tab">
		<div class="container demo-1">
            <div class="main">
				<ul id="carousel" class="elastislide-list">
					<li><a href="#"><img src="images/small/1.jpg" alt="image01" /></a></li>
					<li><a href="#"><img src="images/small/2.jpg" alt="image02" /></a></li>
					<li><a href="#"><img src="images/small/3.jpg" alt="image03" /></a></li>
					<li><a href="#"><img src="images/small/4.jpg" alt="image04" /></a></li>
					<li><a href="#"><img src="images/small/5.jpg" alt="image05" /></a></li>
					<li><a href="#"><img src="images/small/6.jpg" alt="image06" /></a></li>
					<li><a href="#"><img src="images/small/7.jpg" alt="image07" /></a></li>
					<li><a href="#"><img src="images/small/8.jpg" alt="image08" /></a></li>
					<li><a href="#"><img src="images/small/9.jpg" alt="image09" /></a></li>
				</ul>
			</div>
		</div>
		</div>
	</div>
   <div id="zzjs_zzjs_2_2" style="visibility: hidden">
   		<div class="container demo-1">
            <div class="main">
				<ul id="carousel3" class="elastislide-list">
					<li><a href="#"><img src="images/small/10.jpg" alt="image10" /></a></li>
					<li><a href="#"><img src="images/small/11.jpg" alt="image11" /></a></li>
					<li><a href="#"><img src="images/small/12.jpg" alt="image12" /></a></li>
					<li><a href="#"><img src="images/small/13.jpg" alt="image13" /></a></li>
					<li><a href="#"><img src="images/small/14.jpg" alt="image14" /></a></li>
				</ul>
			</div>
		</div>
	</div>
   <div id="zzjs_zzjs_2_3" style="visibility: hidden;">
   		<div class="container demo-1">
            <div class="main">
				<ul id="carousel2" class="elastislide-list">
					<li><a href="#"><img src="images/small/10.jpg" alt="image10" /></a></li>
					<li><a href="#"><img src="images/small/11.jpg" alt="image11" /></a></li>
					<li><a href="#"><img src="images/small/12.jpg" alt="image12" /></a></li>
					<li><a href="#"><img src="images/small/13.jpg" alt="image13" /></a></li>
					<li><a href="#"><img src="images/small/14.jpg" alt="image14" /></a></li>
					<li><a href="#"><img src="images/small/15.jpg" alt="image15" /></a></li>
					<li><a href="#"><img src="images/small/16.jpg" alt="image16" /></a></li>
					<li><a href="#"><img src="images/small/17.jpg" alt="image17" /></a></li>
					<li><a href="#"><img src="images/small/18.jpg" alt="image18" /></a></li>
					<li><a href="#"><img src="images/small/19.jpg" alt="image19" /></a></li>
				</ul>
			</div>
		</div>
	</div>
 </div>
</div>


	<script type="text/javascript">
		$( '#carousel' ).elastislide();
		$( '#carousel2' ).elastislide();
		$( '#carousel3' ).elastislide();

		function settab_zzjsnet(name, cursel, n) {
			for (var i = 1; i <= n; i++) {
				var menu = document.getElementById(name + i);
				var zzjs = document.getElementById("zzjs_" + name + "_" + i);
				menu.className = i == cursel ? "hover" : "";
				zzjs.style.display = i == cursel ? "block" : "none";
				zzjs.style.visibility = i == cursel ? "visible" : "hidden";
			}
		}
	</script>
	</body>
</html>
