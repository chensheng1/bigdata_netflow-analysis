<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="business.terminal.ExecPython" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>流量概况</title>
    
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
  <%
    	ExecPython exepython = new ExecPython();
    	exepython.execsavefig("python F:\\tomcat\\apache-tomcat-8.0.23\\webapps\\HubuTraff\\python\\test.py");
 %>
    源IP流量统计：<br>
    <img src="images\origIp.png"  >
  </body>
</html>
