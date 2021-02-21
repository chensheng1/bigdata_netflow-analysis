<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@page import="business.terminal.MyChartDAO,business.terminal.ChartTest"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Chart.jsp' starting page</title>
    
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
   <table border="1">
      <tr>
          <td>date</td>
          <td>main_log</td>
          <td>main_conn</td>
          <td>main_traff</td>
          <td>orig_traff</td>
          <td>resp_traff</td>
      </tr>
        <%
           MyChartDAO dao=new MyChartDAO();
           List<ChartTest> list =dao.readFirstTitle();    
           for(ChartTest tl:list)
           {%>
          <tr>
              <td><%=tl.getDatetime() %></td>
              <td><%=tl.getMain_log() %></td>
              <td><%=tl.getMain_conn() %></td>
              <td><%=tl.getMain_traff() %></td>
              <td><%=tl.getOrig_traff() %></td>
              <td><%=tl.getResp_traff() %></td>
          </tr>
            <%}
       %>
  </table>
  </body>
</html>
