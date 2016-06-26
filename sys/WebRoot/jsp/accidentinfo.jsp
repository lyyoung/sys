<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>My Test</title>
<link type="text/css" rel="stylesheet" href="css/main.css"/>
</head>
<body>
<form action="info.html" method="post" name="accidentForm" id="accidentForm">
<div class="search_div">
	            发生时间：<input type="text" id="accidentStart" name="accidentStart" value="<fmt:formatDate value="${accident.accidentStart}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()" readonly="readonly" style="width:70px;"/>
		<input type="text" name="accidentEnd" value="<fmt:formatDate value="${accident.accidentEnd}" pattern="yyyy-MM-dd"/>" onclick="WdatePicker()" readonly="readonly" style="width:70px;"/> 
		城市：<input type="text" name="city" value="${accident.city }" style="width:70px;"/>
		管线大类：<input type="text" name="category_big" value="${accident.category_big }" style="width:70px;"/>
		<a href="javascript:search();" class="myBtn"><em>查询</em></a>
	</div>
	<table width="100%" border="0" cellpadding="0" cellspacing="0" class="main_table">
		<tr class="main_head">
			<th><input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllAccident()"/></th>
			
			<th>发生时间</th>
			<th>城市</th>
			<th>管线大类</th>
			<th>事故描述</th>
			<th>事故原因</th>
			<th>操作</th>
		</tr>
		<c:choose>
			<c:when test="${not empty accidentList}">
				<c:forEach items="${accidentList}" var="accident" varStatus="vs">
				<tr class="main_info">
				<td><input type="checkbox" name="id" id="id${accident.id }" value="${accident.id }"/></td>
				<td><fmt:formatDate value="${accident.time}" pattern="yyyy-MM-dd"/></td>
				<td>${accident.city }</td>
				<td>${accident.category_big }</td>
				<td>${fn:substring(accident.accident_des,0,20)}</td>
				<td>${accident.accident_reason }</td>
				<td><a href="javascript:editAccident(${accident.id });">修改</a> | <a href="javascript:delAccident(${accident.id });">删除</a> </td>
				</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
				<tr class="main_info">
					<td colspan="7">没有相关数据</td>
				</tr>
			</c:otherwise>
		</c:choose>
	</table>
	<div class="page_and_btn">
		<div>
			<a href="javascript:addAccident();" class="myBtn"><em>新增</em></a>
			<a href="javascript:exportAccident();" class="myBtn"><em>导出</em></a>
		</div>
		${accident.page.pageStr }
	</div>
	</form>
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript" src="js/datePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/lhgdialog/lhgdialog.min.js?t=self&s=areo_blue"></script>
	<script type="text/javascript">
		$(document).ready(function(){
			$(".main_info:even").addClass("main_table_even");
		});
		function sltAllAccident(){
			if($("#sltAll").attr("checked")){
				$("input[name='id']").attr("checked",true);
			}else{
				$("input[name='id']").attr("checked",false);
			}
		}
		
		function addAccident(){
			//$(".shadow").show();
			var dg = new $.dialog({
				title:'新增事故',
				id:'accident_new',
				width:330,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				xButton:true,
				resize:false,
				page:'accident/add.html'
				});
    		dg.ShowDialog();
		}
		
		function editAccident(id){
			var dg = new $.dialog({
				title:'修改事故',
				id:'accident_edit',
				width:430,
				height:300,
				iconTitle:false,
				cover:true,
				maxBtn:false,
				resize:false,
				page:'accident/edit.html?id='+id
				});
    		dg.ShowDialog();
		}
		
		function delUser(id){
			if(confirm("确定要删除该记录？")){
				var url = "accident/delete.html?id="+id;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		
		function search(){
			$("#accidentForm").submit();
		}
		
		function exportUser(){
			document.location = "accident/excel.html";
		}
	</script>
</body>
</html>