<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'acciendent_info.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rel="stylesheet" href="css/main.css"/>
<style type="text/css">
body{width:100%;height:100%;background-color: #FFFFFF;text-align: center;}
.input_txt{width:200px;height:20px;line-height:20px;}
.info{height:40px;line-height:40px;}
.info th{text-align: right;width:95px;color: #4f4f4f;padding-right:5px;font-size: 13px;}
.info td{text-align:left;}
</style>
  </head>
  
  <body>
   <form action="accident/save.html" method="post" name="accidentForm" id="accidentForm" target="result" method="post" onsubmit="return checkInfo();">
   <input type="hidden" name="id" id="id" value="${accident.id }"/>
	<table border="0" cellpadding="0" cellspacing="0">
		<tr class="info">
			<th>发生时间</th>
			<td><input type="text" name="time" id="time" class="input_txt" value="<fmt:formatDate value="${accident.time}" pattern="yyyy-MM-dd"/>"/></td>
	    </tr>
	    <tr class="info">
			<th>省份</th>
			<td><input type="text" name="province" id="province" class="input_txt" value="${accident.province }"/></td>
	    </tr>
	    <tr class="info">
			<th>城市</th>
			<td><input type="text" name="city" id="city" class="input_txt" value="${accident.city }"/></td>
	    </tr>
	    <tr class="info">
			<th>管线大类</th>
			<td><input type="text" name="category_big" id="category_big" class="input_txt" value="${accident.category_big }"/></td>
	    </tr>
	    <tr class="info">
			<th>管线小类</th>
			<td><input type="text" name="category_small" id="category_small" class="input_txt" value="${accident.category_small }"/></td>
	    </tr>
	    <tr class="info">
			<th>管线材质</th>
			<td><input type="text" name="material" id="material" class="input_txt" value="${accident.material }"/></td>
	    </tr>
	    <tr class="info">
			<th>事故类型</th>
			<td><input type="text" name="reason_type" id="reason_type" class="input_txt" value="${accident.reason_type }"/></td>
	    </tr>
	    <tr class="info">
			<th>事故类型类别</th>
			<td><input type="text" name="result_type" id="result_type" class="input_txt" value="${accident.result_type }"/></td>
	    </tr>
	    <tr class="info">
			<th>事故描述</th>
			<td><input type="text" name="accident_des" id="accident_des" class="input_txt" value="${accident.accident_des }"/></td>
	    </tr>
		<tr class="info">
			<th>事故原因</th>
			<td><input type="text" name="accident_reason" id="accident_reason" class="input_txt" value="${accident.accident_reason }"/></td>
	    </tr>
	    <tr class="info">
			<th>事故原因描述</th>
			<td><input type="text" name="accident_reason_des" id="accident_reason_des" class="input_txt" value="${accident.accident_reason_des }"/></td>
	    </tr>
	    <tr class="info">
			<th>供应中断</th>
			<td><input type="text" name="casualties" id="casualties" class="input_txt" value="${accident.casualties }"/></td>
	    </tr>
	    <tr class="info">
			<th>其他后果</th>
			<td><input type="text" name="other_results" id="other_results" class="input_txt" value="${accident.other_results }"/></td>
	    </tr>
	    <tr class="info">
			<th>事故损失描述</th>
			<td><input type="text" name="loss_des" id="loss_des" class="input_txt" value="${accident.loss_des }"/></td>
	    </tr>	
		<tr class="info">
			<th>信息来源</th>
			<td><input type="text" name="information_sources" id="information_sources" class="input_txt" value="${accident.information_sources }"/></td>
	    </tr>
	    <tr class="info">
			<th>图片</th>
			<td><input type="text" name="image_url" id="image_url" class="input_txt" value="${accident.image_url }"/></td>
	    </tr>
	    <tr class="info">
			<th>备注</th>
			<td><input type="text" name="note" id="note" class="input_txt" value="${accident.note }"/></td>
	    </tr>		
			
	</table>
	</form>
	<iframe name="result" id="result" src="about:blank" frameborder="0" width="0" height="0"></iframe>
	<script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
	<script type="text/javascript">
		var dg;
		$(document).ready(function(){
			dg = frameElement.lhgDG;
			dg.addBtn('ok','保存',function(){
				$("#accidentForm").submit();
			});
			
		});
		
		function checkInfo(){
			
			return true;
		}
		
		function success(){
			if(dg.curWin.document.forms[0]){
				dg.curWin.document.forms[0].action = dg.curWin.location+"";
				dg.curWin.document.forms[0].submit();
			}else{
				dg.curWin.location.reload();
			}
			dg.cancel();
		}
		
		function failed(){
			alert("新增失败，该事故已存在！");
			$("#loginname").select();
			$("#loginname").focus();
		}
	</script>
  </body>
</html>
