<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="business.terminal.DataBaseConfig" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'testDB.jsp' starting page</title>
    
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
    <div id="device" style="width: 600px;height:400px"></div>
    <script type="text/javascript">
        // 基于准备好的dom，初始化echarts实例
        var myChart = echarts.init(document.getElementById('device'));
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
                {value:74224, name:'手持终端'},
                {value:<%=(new DataBaseConfig()).test()%>, name:'非手持终端'},
                
            ],
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


        // 使用刚指定的配置项和数据显示图表。
        myChart.setOption(option);
    </script>
  </body>
</html>
