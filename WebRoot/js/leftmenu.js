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