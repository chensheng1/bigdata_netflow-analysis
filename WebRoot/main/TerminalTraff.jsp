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
    
    <title>终端分析</title>
    
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
    	exepython.execsavefig("python  C:\\apache-tomcat-8.0.23\\webapps\\HubuTraff\\python\\resByte.py");
 %>
     网站访问排名：<br>
     <img src="images\orig.jpg" width="1024" height="340">
  </body>
</html>
