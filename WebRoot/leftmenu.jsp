<%@page import="com.model.enums.Subcategory"%>
<%@page import="com.model.enums.ProductType"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String t = request.getParameter("r");	//Get retailer
	if(t == null) t="";
%>
		<div class="leftmenu">
			<div class="mod-menu f-l">
				<div id="column-left">
					<ul class="menu-item">
						<% for(ProductType p : ProductType.values()){
								if(p == ProductType.NULL)
									break;
						%>
						<li class=""><a href="list.action?type1=<%=p.getName() %>&t=<%=t%>"><%=p.getDescription() %></a></li>
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
									<li><a href="list.action?type2=<%=s.getName() %>&t=<%=t%>"><%=s.getDescription() %></a></li>
								<% } %>
							</ul>
						</div>
						<% } %>
					</div>
				</div>
				<!--二级菜单内容-->
			</div>
		</div>