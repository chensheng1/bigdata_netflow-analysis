<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="business.terminal.Device" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"></meta>
</head>
<body>
<% 
response.setContentType("text/xml; charset=UTF-8");  
    response.setHeader("Cache-Control","no-cache"); 
    Device device=new Device();
    out.println("<handheldDevice>");
  	out.println(device.handheldDevice());
  	out.println("</handheldDevice>"); 
  	out.println("<nonHandheldDevice>");
  	out.println(device.nonHandheldDevice());
  	out.println("</nonHandheldDevice>");       
%>  
</body>
</html>