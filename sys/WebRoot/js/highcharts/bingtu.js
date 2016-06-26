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
});



var options = {
        chart: {
            type: 'pie',
            options3d: {
                enabled: true,
                alpha: 45,
                beta: 0
            },
            backgroundColor: 'rgba(0,0,0,0)'
           // renderTo:'mpf'
        },
        title: {
            text: '事故管线类型分布统计图'
        },
        tooltip: {//@这个是浮动显示
            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
        },
        plotOptions: {
            pie: {
                allowPointSelect: true,
                cursor: 'pointer',
                depth: 35,
                dataLabels: {
                    enabled: true,
                    format: '<b>{point.name}</b>: {point.percentage:.1f} %'//@#这个是本身显示
                },
             showInLegend: true//@#饼图中得这样加legend
            }
        },
        // legend:{//@#这种不能在饼图中适用
        //     enabled:true
        // },
        
        //@#水平的legend
//        legend:{
//        	layout:'horizontal',
//        	backgroundColor: '#FFFFFF',
//        	//floating: true,//@#因为是水平的显示，所以floating=true有可能和图表重叠
//        	verticalAlign: "bottom",
//        	 labelFormatter: function () {
//              return this.name + '('+this.percentage+'%)';
//        	}
//        },
//        
        
        //@#垂直的legend
        legend: {
                  layout: 'vertical',        	
//                backgroundColor: '#FFFFFF',
                 floating: true,
                 align: 'right',
                  verticalAlign: 'top',
                  x: 0,
                  y: 45,
                  labelFormatter: function () {
                                return this.name + '('+this.percentage.toFixed(1)+'%)';
                    }
          },
        credits:{
            text:"www.baidu.com",
            href:'www.baidu.com'
        },
        series: [{
            type: 'pie',
            name: 'Browser share',
            data: [
            ]
        }]
    };
function load(){
	
	var chart = $("#mpf0").highcharts(options);
	$.ajax({
          //type:'post',
          //url:"/highcharts/servlet/chartServlet",//@#这里必须添加/highcharts/是因为post提交方式
          url:"accident/tongji1.html",//@#对应get提交方式
          async:false,
          data:{category:"category_big"},
          dataType: "json",//返回json格式的数据          
          success: function(data){//@这里的data是从服务器传回来的
        	  var s = new Array();
	          $.each(data,function(i,n){
	        	  s[i]=new Array(2);
//	        	  alert(n.name);
	        	  s[i][0]=n.name;
	        	  s[i][1]=n.size;
//	        	  alert(s[i][0]+":"+s[i][1]);
	          });
//	          options1.chart.renderTo="container"+i;
	         // options.chart.renderTo = 'mpf';
	          options.series[0].data=s;
	         
	          
	          chart = new Highcharts.Chart(options);
//	          alert(chart.options.title.text);
	          //@#选定那个分离出来，这里目前只能用函数，不能直接赋值
	          chart.series[0].data[0].slice();

         },
     });//@#end of ajax
var chart1 = $("#mpf1").highcharts(options);
	
	$.ajax({
          //type:'post',
          //url:"/highcharts/servlet/chartServlet",//@#这里必须添加/highcharts/是因为post提交方式
          url:"accident/tongji1.html",//@#对应get提交方式
          async:false,
          data:{category:"city"},
          dataType: "json",//返回json格式的数据          
          success: function(data){//@这里的data是从服务器传回来的
//        	  alert(data);
        	  var s = new Array();
	          $.each(data,function(i,n){
	        	  s[i]=new Array(2);
//	        	  alert(n.name);
	        	  s[i][0]=n.name;
	        	  s[i][1]=n.size;
//	        	  alert(s[i][0]+":"+s[i][1]);
	          });
	         
	          
	          options.series[0].data=s;
	          options.title.text="事故地域分布图";
	          //options.chart[0].renderTo = "mpf1";
	          chart1 = new Highcharts.Chart(options);
	          
	          //@#选定那个分离出来，这里目前只能用函数，不能直接赋值
	          chart1.series[0].data[0].slice();

         },
     });//@#end of ajax
}
