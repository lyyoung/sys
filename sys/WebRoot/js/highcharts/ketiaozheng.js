Highcharts.setOptions({//@#用来汉化
    lang:{
       contextButtonTitle:"图表导出菜单",
       decimalPoint:".",
       downloadJPEG:"下载JPEG图片",
       downloadPDF:"下载PDF文件",
       downloadPNG:"下载PNG文件",
       downloadSVG:"下载SVG文件",
       drillUpText:"返回 {series.name}",
       loading:"加载中",
       months:["一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"],
       noData:"没有数据",
       numericSymbols: [ "千" , "兆" , "G" , "T" , "P" , "E"],
       printChart:"打印图表",
       resetZoom:"恢复缩放",
       resetZoomTitle:"恢复图表",
       shortMonths: [ "Jan" , "Feb" , "Mar" , "Apr" , "May" , "Jun" , "Jul" , "Aug" , "Sep" , "Oct" , "Nov" , "Dec"],
       thousandsSep:",",
	weekdays:["星期一","星期二","星期三","星期四","星期五","星期六","星期七",]
     }
//	,   
//     colors: ['#7cb5ec','#058DC7', '#50B432', '#ED561B', '#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263', '#6AF9C4']
    
});
       
var options1={
	        chart: {
	    type: 'column',
	    backgroundColor: 'rgba(0,0,0,0)',
	    margin: 75,
	   // renderTo: 'container',
	    options3d: {
	        enabled: true,
	        alpha: 5,
	        beta:0,//@#与x的夹角
	        depth: 50,
	        viewDistance: 5
	    }
	},
	title: {
	    text: '事故管线类型分布统计图'
	},
	subtitle: {
	    text: '这个图表可以调整视角'
	},
	plotOptions: {
		 series: {//@#设置柱状图颜色每个不同
	        colorByPoint: true
	    },
	    column: {
	        depth: 25,
	        
		    dataLabels: {
	            enabled: true,
	            color: "#7cb5ec",
	            style: {
	                fontWeight: 'bold'
	            },
	            formatter: function() {
	                return this.y;
	            }
	        }
	    }
	},
	xAxis: {//@#lang下包括一些可能常用到的数组比如月份，星期
	   // categories: Highcharts.getOptions().lang.shortMonths
	 //categories: Highcharts.getOptions().lang.weekdays
	},
	yAxis: {
	    opposite: true
	},
	credits:{
	text:"www.mpf.com",
	href:"www.mpf.com"	
	
	},
	series: [{
	    name: 'Num',//@#这里既是图表的名称，也是鼠标浮动上去后的显示的
	    data: [
	           //19, 3, null, 4, 0, 5,1
	           ]
	}]


};
﻿$(function(){    
  //@必须写成var chart=
//@#不能写成$('#container').highcharts({
	var colors = Highcharts.getOptions().colors;
     //var chart = new Highcharts.Chart(options1);
     var chart = $("#container0").highcharts(options1);

     $.ajax({
         //type:'post',
         //url:"/highcharts/servlet/chartServlet",//@#这里必须添加/highcharts/是因为post提交方式
         url:"accident/tongji1.html",//@#对应get提交方式
         async:false,
         data:{category:"category_big"},
         dataType: "json",//返回json格式的数据          
         success: function(data){//@这里的data是从服务器传回来的
        	 
        	 var s = new Array();
        	 var str = new Array();
	          $.each(data,function(i,n){
	        	  s[i]=new Array(2);
//	        	  alert(n.name);
	        	  s[i][0]=n.name;
	        	  s[i][1]=n.size;
//	        	  alert(s[i][0]+":"+s[i][1]);
	        	  str[i]=n.name;
	          });
	          options1.series[0].data=s;
	          chart = new Highcharts.Chart(options1);
	          chart.xAxis[0].setCategories(str);//@#用来重置x轴的类别
	          //@#选定那个分离出来，这里目前只能用函数，不能直接赋值
	         // chart.series[0].data[0].slice();

        },
    });//@#end of ajax
//     var chart1 = new Highcharts.Chart(options1);
     var chart1 = $("#container1").highcharts(options1);

     $.ajax({
         //type:'post',
         //url:"/highcharts/servlet/chartServlet",//@#这里必须添加/highcharts/是因为post提交方式
         url:"accident/tongji1.html",//@#对应get提交方式
         async:false,
         data:{category:"city"},
         dataType: "json",//返回json格式的数据          
         success: function(data){//@这里的data是从服务器传回来的
        	 
        	 var s = new Array();
        	 var str = new Array();
	          $.each(data,function(i,n){
	        	  s[i]=new Array(2);
//	        	  alert(n.name);
	        	  s[i][0]=n.name;
	        	  s[i][1]=n.size;
//	        	  alert(s[i][0]+":"+s[i][1]);
	        	  str[i]=n.name;
	          });
	          options1.title.text="事故地域分布图";
	          options1.series[0].data=s;
	          options1.chart.renderTo="container1";
	          chart1 = new Highcharts.Chart(options1);
	          chart1.xAxis[0].setCategories(str);//@#用来重置x轴的类别
	          //@#选定那个分离出来，这里目前只能用函数，不能直接赋值
	         // chart.series[0].data[0].slice();

        },
    });//@#end of ajax
     
     
     
         // Add mouse events for rotation@#用来绑定监听事件
    $(chart.container).bind('mousedown.hc touchstart.hc', function (e) {
        e = chart.pointer.normalize(e);
        
        var posX = e.pageX,
            posY = e.pageY,
            alpha = chart.options.chart.options3d.alpha,
            beta = chart.options.chart.options3d.beta,
            newAlpha,
            newBeta,
            sensitivity = 5; // lower is more sensitive

        $(document).bind({//@#这里的转换算法先没有深入追究
            'mousemove.hc touchdrag.hc': function (e) {
                // Run beta
                newBeta = beta + (posX - e.pageX) / sensitivity;
                newBeta = Math.min(100, Math.max(-100, newBeta));
                chart.options.chart.options3d.beta = newBeta;

                // Run alpha
                newAlpha = alpha + (e.pageY - posY) / sensitivity;
                newAlpha = Math.min(100, Math.max(-100, newAlpha));
                chart.options.chart.options3d.alpha = newAlpha;

                chart.redraw(false);
            },                            
            'mouseup touchend': function () { 
                $(document).unbind('.hc');
            }
        });
    });
});