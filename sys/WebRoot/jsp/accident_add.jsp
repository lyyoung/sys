<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String importMsg = "";
if(request.getSession().getAttribute("msg")!=null){
   importMsg = request.getSession().getAttribute("msg").toString();
}
request.getSession().setAttribute("msg", "");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'accident_add.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="accident/batchImport.html" method="post" enctype="multipart/form-data" name="batchAdd" onsubmit="return check()">
      <div>
        <input id="excel_file" type="file" name="filename" accept="xlsx" size="50"/>
        <input id="excel_btn" type="submit" value="导入excel">
        <input id="importMsg" type="hidden" value='<%=importMsg%>' />
      </div>
    </form>
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script>
      function check(){
         var excel_file = $("#excel_file").val();
         if(excel_file==null||excel_file.length==0){
            alert("请选择文件！");
            return false;
         }else{
            return true;
         }
      }
      $(function(){
        var msg = "";
        if($("#importMsg").val()!=null){
          msg = $("#importMsg").val();
        }
        if(msg!=""){
          alert(msg);
        }
      });
    </script>
  </body>
</html>
