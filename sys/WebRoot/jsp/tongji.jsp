<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'tongji.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
    <script type="text/javascript" src="js/jquery-1.5.1.min.js"></script>
    <script type="text/javascript" src="js/highcharts/highcharts.js"></script>
    <script type="text/javascript" src="js/highcharts/bingtu.js"></script>
    <script type="text/javascript" src="js/highcharts/highcharts-3d.js"></script>
    <script type="text/javascript" src="js/highcharts/ketiaozheng.js"></script>
<script type="text/javascript" >
 $(function (){
   var  flag=true;
   /* $("#change").click(function (){
    		$("#container").toggle();
    		$("#mpf").toggle();
    		if(flag==true){
    			$(this).text("切换到饼图");
    			flag=false;
    		
    		}else{
    			$(this).text("切换到柱状图");
    			flag=true;
    		}
    		
    
    });*/
    $(".btn-success").click(function(){
    	
    	var i= $(".btn-success").index(this);
    	$("#container"+i).toggle();
    		$("#mpf"+i).toggle();
    		if(flag==true){
    			$(this).text("切换到饼图");
    			flag=false;
    		
    		}else{
    			$(this).text("切换到柱状图");
    			flag=true;
    		}
    });
 });
 
 </script> 


</head>
<body onload="load();">
     
    <!--5.导入容器用于显示图表
    
   
    --> 

    <!--5.导入容器用于显示图表-->
    <div id="tubiao1" style="float:left;"> 
    <div id="mpf0" style="width: 800px;border:0px solid #ccc;margin: 30px auto;"> 
    </div> 
    <div id="container0" style="width:800px;height:400px;display:none;margin: 30px auto;"></div>
    
    <div style="margin:10px auto;width:120px;">
       <button class="btn btn-success " id="change" >切换成柱状图</button>
   </div>
   </div>
   <div id="tubiao2" style="float: left">
   <div id="mpf1" style="width: 800px;border:0px solid #ccc;margin: 30px auto;"> 
    </div>
   <div id="container1" style="width:800px;height:400px;display:none;margin: 30px auto;"></div>
    
    <div style="margin:10px auto;width:120px;">
       <button class="btn btn-success " id="change1" >切换成柱状图</button>
   </div>
  </div>
       
    	
    
  
   
</body>
</html>
