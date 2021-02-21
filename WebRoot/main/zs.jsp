<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/echarts.min.js"></script>
<title>Insert title here</title>
<script language="javascript">  
      
    var XMLHttpReq;  
        //创建XMLHttpRequest对象         
        function createXMLHttpRequest() {  
            if(window.XMLHttpRequest) { //Mozilla 浏览器  
                XMLHttpReq = new XMLHttpRequest();  
            }  
            else if (window.ActiveXObject) { // IE浏览器  
                try {  
                    XMLHttpReq = new ActiveXObject("Msxml2.XMLHTTP");  
                } catch (e) {  
                    try {  
                        XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");  
                    } catch (e) {}  
                }  
            }  
        }  
        //发送请求函数  
        function sendRequest() {  
            createXMLHttpRequest();  
            var url = "ajaxtest.jsp";  
            XMLHttpReq.open("GET", url, true);  
            XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
            XMLHttpReq.send(null);  // 发送请求  
        }  
        // 处理返回信息函数  
        function processResponse() {  
            if (XMLHttpReq.readyState == 4) { // 判断对象状态  
                if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息  
                    DisplayHot();  
                    setTimeout("sendRequest()", 5000);  
                } else { //页面不正常  
                    window.alert("您所请求的页面有异常。");  
                }  
            }  
        }  
        function DisplayHot() {  
            var name = XMLHttpReq.responseXML.getElementsByTagName("response"); 
          	var name2=name[0].childNodes[0].nodeValue;
          	document.getElementById("myId").innerHTML = name2;
            var resultData=parseInt(name2);
            // 基于准备好的dom，初始化echarts实例
             var myChart = echarts.init(document.getElementById('device'));
		
		        // 指定图表的配置项和数据
		        var option = {
		    title : {
		        text: '手持设备与非手持设备',
		        x:'center',
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
		                {value:resultData, name:'非手持终端'},
		                
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
                 
        }  
      
      
    </script>  
      
</head>
<body onload =sendRequest()>  
   <div id="device" style="width: 600px;height:400px"></div>
    <table style="BORDER-COLLAPSE: collapse" borderColor=#111111 cellSpacing=0 cellPadding=0 width=200    bgColor=#f5efe7 border=0>  
        
    <tr>  
       <td height="20"> 非手持终端数量：</td>  
       <td height="20" id="myId"> </td>  
    </tr>   
    </table>
   <script type="text/javascript">
		        
		       
    </script>
    </body>  
</html>