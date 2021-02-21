<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="../js/echarts.min.js"></script>
<title>Insert title here</title>
<script language="javascript"> 
	var connresult; 
    var traffresult;
    var logresult; 
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
            var url = "Main_ajax.jsp";  
            XMLHttpReq.open("GET", url, true);  
            XMLHttpReq.onreadystatechange = processResponse;//指定响应函数  
            XMLHttpReq.send(null);  // 发送请求  
        }  
        // 处理返回信息函数  
        function processResponse() {  
            if (XMLHttpReq.readyState == 4) { // 判断对象状态  
                if (XMLHttpReq.status == 200) { // 信息已经成功返回，开始处理信息  
                    DisplayHot();  
                    setTimeout("sendRequest()", 10000);  
                } else { //页面不正常  
                    window.alert("您所请求的页面有异常。");  
                }  
            }  
        }  
        function DisplayHot() {  
            //读取Main_ajax.jsp里面的连接数的值
            var conn1 = XMLHttpReq.responseXML.getElementsByTagName("Main_conn");  
            var conn2=conn1[0].childNodes[0].nodeValue.replace("]","");
          		conn2=conn2.replace("[","");
          	var conn3=conn2.split(",");
          	var result1=Number(conn3[1])-Number(conn3[0]);
          	//读取Main_ajax.jsp里面流量的大小
          	var traff1 = XMLHttpReq.responseXML.getElementsByTagName("Main_traff");  
            var traff2=traff1[0].childNodes[0].nodeValue.replace("]","");
          		traff2=traff2.replace("[","");
          	var traff3=traff2.split(",");
          	var result2=Number(traff3[1])-Number(traff3[0]);
          	//读取Main_ajax.jsp里面的文件大小数据
          	var log1 = XMLHttpReq.responseXML.getElementsByTagName("Main_log");  
            var log2=log1[0].childNodes[0].nodeValue.replace("]","");
          		log2=log2.replace("[","");
          	var log3=log2.split(",");
          	var result3=Number(log3[1])-Number(log3[0]);
            
            //计算连接数，流量，日志大小
            connresult=(result1)/10;
            traffresult=(result2)/81920;
            logresult=(result3)/81920;
            //window.alert(connresult);
        }
        //window.alert(connresult);
    </script>  
      
</head>
<body onload =sendRequest()>  
   <div id="main" style="width: 800px;height:400px"></div>
<script type="text/javascript">
		var myChart = echarts.init(document.getElementById('main'));
		var	option = {
			    title: {
			        text: '实时分析图'
			    },
			    tooltip: {
			        trigger: 'axis'
			    },
			    toolbox: {
			        show : true,
			        feature : {
			            mark : {show: true},
			            dataView : {show: true, readOnly: false},
			            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
			            restore : {show: true},
			            saveAsImage : {show: true}
			        }
			    },
			    calculable : true,

			    legend: {
			        data:['日志文件(单位:KBps)','流量大小(单位:kbps)','连接数(单位:条数/s)']
			    },
			    grid: {
			        left: '3%',
			        right: '4%',
			        bottom: '3%',
			        containLabel: true
			    },
			    xAxis: {
			        type: 'category',
			        boundaryGap: false,
			        data: (function (){  
		                var now = new Date();  
		                var res = [];  
		                var len = 10;  
		                while (len--) {  
		                    res.unshift(now.toLocaleTimeString().replace(/^\D*/,''));  
		                    now = new Date(now - 10000);  
		                }  
		                return res;  
		            })()  
			    },
			    yAxis: {
			       type: 'value'
			      
			    },
			    series: [
			        {
			            name:'日志文件(单位:KBps)',
			            type:'line',
			            stack: '总量',
			            data:(function (){  
			                var res = [];  
			                var len = 0;  
			                while (len < 10) {  
			                    res.push(0);  
			                    len++;  
			                }  
			                return res;  
			            })(),  
			            },
			        {
			            name:'流量大小(单位:kbps)',
			            type:'line',
			            stack: '总量',
			            data:(function (){  
			                var res = [];  
			                var len = 0;  
			                while (len < 10) {  
			                    res.push(0);  
			                    len++;  
			                }  
			                return res;  
			            })(),  
			            },
			        {
			            name:'连接数(单位:条数/s)',
			            type:'line',
			            stack: '总量',
			            data:(function (){  
			                var res = [];  
			                var len = 0;  
			                while (len < 10) {  
			                    res.push(0);  
			                    len++;  
			                }  
			                return res;  
			            })(), 
			     },
			    ]
			 };  
			 
			 
			setInterval(function (){  
			    axisData = (new Date()).toLocaleTimeString().replace(/^\D*/,'');  
			  
			    var data0 = option.series[0].data;  
			    data0.shift();  
			    data0.push(connresult);  
			    
			    var data1=option.series[1].data;
			    data1.shift();
			    data1.push(traffresult);
			    
			    var data2=option.series[2].data;
			    data2.shift();
			    data2.push(logresult);
			
			  
			    option.xAxis.data.shift();  
			    option.xAxis.data.push(axisData);  
			      
			    
			    myChart.setOption(option);  
			}, 10000);
					myChart.setOption(option);
</script>
    </body>  
</html>