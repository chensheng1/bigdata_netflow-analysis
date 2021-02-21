<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="business.terminal.DataBaseTest" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>流量概况</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<script src="../js/echarts.min.js"></script>
  </head>
  
  <body>
  <br>
  <%response.setHeader("refresh","3");  %>
  	<script type="text/javascript">
function loadXMLDoc()
{
var xmlhttp;
if (window.XMLHttpRequest)
  {// code for IE7+, Firefox, Chrome, Opera, Safari
  xmlhttp=new XMLHttpRequest();
  }
else
  {// code for IE6, IE5
  xmlhttp=new ActiveXObject("Microsoft.XMLHTTP");
  }
xmlhttp.onreadystatechange=function()
  {
  if (xmlhttp.readyState==4 && xmlhttp.status==200)
    {
    document.getElementById("myDiv").innerHTML=xmlhttp.responseText;
    }
  },
xmlhttp.open("GET","/ajax/test1.txt",true);
xmlhttp.send();
}
</script>
    <div id="deviceC" style="width: 600px;height:400px;float:left"></div>

    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        
        var myChart = echarts.init(document.getElementById('deviceC'));
        // 指定图表的配置项和数据
        var option = {
    title : {
        text: '手持设备与非手持设备',
        x:'center'
    },
    tooltip : {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c} ({d}%)"
    },
    legend: {
    	top:30,
        orient: 'vertical',
        left: 'left',
        data: ['手持终端','非手持终端']
    },
    series : [
        {
            name: '连接数量',
            type: 'pie',
            radius : '70%',
            center: ['50%', '60%'],
            data:[
                {value:42, name:'手持终端'},
                {value:<%=(new DataBaseTest()).test()%>, name:'非手持终端'},],
            itemStyle: {
                emphasis: {
                    shadowBlur: 10,
                    shadowOffsetX: 0,
                    shadowColor: 'rgba(0, 0.5)'
                }
            }
        }
    ]
};


myChart.setOption(option);



    </script>
  </body>
</html>
