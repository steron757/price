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
	<s:if test="#request.pList.size > 0">
		<div class="product_list">
			<div class="">找到商品共<s:property value="#request.pListCount"/>条</div>
			<s:iterator value="#request.pList" var="pro">
				<div class="product_img">
					<a href="javascript:void(0)" onclick="opendetail('<s:property value="#pro.model"/>','<s:property value="#pro.productType"/>','<s:property value="#pro.brand"/>')">
					<img alt="" src="<s:property value="#pro.image"/>"/></a>
					<div style="margin-top: 10px;width: 150px;text-align: center;">
						<s:property value="#pro.brand"/>&nbsp;<s:property value="#pro.model"/>
					</div>
					<div style="text-align: center;color: red"><s:property value="#pro.retailer"/>&nbsp;&nbsp;&nbsp;&nbsp;
						<s:property value="#pro.price"/></div>
				</div>
			</s:iterator>
		</div>
		<div class="scott">
			<font color="4599e3">当前第&nbsp;<%=currentPage %>&nbsp;页,共<%=pageCount %>页</font>&nbsp;&nbsp;&nbsp;
			<a href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=1"> << </a>
			<a href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=Integer.parseInt(currentPage)>1?Integer.parseInt(currentPage)-1:1 %>"> < </a>
	<%  int i = 1;
		int j = 1;
		if(Integer.parseInt(currentPage) >= 6){
			i = Integer.parseInt(currentPage) - 4;
		}
		for(; i<=Integer.parseInt(pageCount);i++){
			j++;
			if(i == Integer.parseInt(currentPage)){
	 %> <a id="p<%=i%>" href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=i%>" class="current"><%=i %></a>
	 	<% }else{ %>
		<a id="p<%=i%>" href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=i%>"><%=i %></a>
		<% }
	 	if(j>6 && Integer.parseInt(pageCount) >=i+2){ %>
	 	...
	 	<a id="p<%=Integer.parseInt(pageCount)%>" href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=pageCount%>"><%=Integer.parseInt(pageCount) %></a>
	 <% break;
	 	}
	 } 
	 %>
			<a href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=Integer.parseInt(currentPage)<Integer.parseInt(pageCount)?Integer.parseInt(currentPage)+1:pageCount %>"> > </a>
			<a href="list.action?type1=<%=type1 %>&type2=<%=type2 %>&page=<%=pageCount %>"> >> </a>
		</div>
	</s:if>
	<s:else>
		<div class="product_list">没有找到记录</div>
	</s:else>
<script type="text/javascript">
	function opendetail(model, type, brand) {
		window.location.href = 'findSelected!find.action?'+'model=' + encodeURIComponent(model) + '&type=' + type + '&brand=' + encodeURIComponent(brand);
	};
</script>
</body>
</html>