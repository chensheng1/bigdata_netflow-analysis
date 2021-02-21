<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<title>导航树</title>
	<meta http-equiv="content-type" content="text/html;charset=UTF-8">
	<style type="text/css">@import url('components/dtree/dtree.css');</style>
	<script type="text/javascript">var dtreeIconBasePath = "components/dtree";</script>
	<script type="text/javascript"  src="components/dtree/dtree.js"></script>
</head>
<body>
<script type="text/javascript"> //link后加需要跳转的界面,如:link:link:"main/1.html";
var treeMenu = [
	{ level:1, name:"系统概况"},
	{ level:2, name:"流量概况", ico:"images/icon_default.gif",link:"main/mainTraffic.html"},
	{ level:2, name:"应用详细", ico:"images/icon_default.gif",link:"main/icmp.html"},
	/*{ level:1, name:"应用协议"},
	{ level:2, name:"HTTP", ico:"images/icon_default.gif",link:"main/test.jsp"},
	{ level:2, name:"DNS", ico:"images/icon_default.gif"},
	{ level:2, name:"FTP", ico:"images/icon_default.gif"},
	{ level:2, name:"ICMP", ico:"images/icon_default.gif",link:"main/icmpDetail.jsp"},
	{ level:2, name:"DHCP", ico:"images/icon_default.gif"},
	{ level:2, name:"SIP", ico:"images/icon_default.gif"},
	{ level:2, name:"SMTP", ico:"images/icon_default.gif"},
	{ level:2, name:"SSH", ico:"images/icon_default.gif"},
	{ level:2, name:"SSL", ico:"images/icon_default.gif"},
	{ level:2, name:"TUNNEL", ico:"images/icon_default.gif"},
	{ level:2, name:"X.509", ico:"images/icon_default.gif",link:"main/3.html"},
	{ level:2, name:"SNMP", ico:"images/icon_default.gif"},
	{ level:2, name:"MySQL", ico:"images/icon_default.gif"},
	{ level:2, name:"RDP", ico:"images/icon_default.gif"},*/
	{ level:1, name:"监测统计"},
	{ level:2, name:"源IP统计", ico:"images/icon_default.gif",link:"main/origin_total.html"},
	{ level:2, name:"目标IP统计", ico:"images/icon_default.gif",link:"main/respIP_total.html"},
	{ level:2, name:"会话统计", ico:"images/icon_default.gif",link:"main/session.jsp"},
	];
</script>


<div class="dtree" style="margin:10px;">
<script type="text/javascript"> 
//建立新树
tree = new dTree('tree');
tree.config.target = "mainFrame";
tree.config.useCookies = false;
var selNum = -1;
var link = "";
//根目录
tree.add(0,-1,'监控统计', null, null, null, '', '');
var count = 0;
var pLevelIdArray = new Array();
pLevelIdArray[1] = 0;
var currLevel = 1;
for (var i=0; i<treeMenu.length; i++) {
	var item = treeMenu[i];
	var itemLevel = item.level;
	pLevelIdArray[itemLevel+1] = ++count;
	if (item.link!=null && item.link!="") {
		if (item.ico!=null) {
			tree.add(count, pLevelIdArray[itemLevel], item.name, item.link, null, null, item.ico, item.ico);
		} else {
			tree.add(count, pLevelIdArray[itemLevel], item.name, item.link);
		}
	} else {
		if (item.ico!=null) {
			tree.add(count, pLevelIdArray[itemLevel], item.name, null, null, null, item.ico, item.ico);
		} else {
			tree.add(count, pLevelIdArray[itemLevel], item.name);
		}
	}
	if (item.select) {
		selNum = count;
		link = item.link;
	}
}
document.write(tree);
tree.openAll();
if (selNum != -1) {
	tree.openTo(selNum,true);
	top.document.frames["mainFrame"].location.href=link;
}
</script>
</div>
</body>
</html>