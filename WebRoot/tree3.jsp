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
<script type="text/javascript"> 
var treeMenu = [
	{ level:1, name:"终端分析"},
	{ level:2, name:"终端流量",ico:"images/icon_default.gif"},
	{ level:2, name:"操作系统",ico:"images/icon_default.gif",link:"main/op.jsp"},
	{ level:2, name:"终端类型",ico:"images/icon_default.gif",link:"main/device.jsp"},
	{ level:1, name:"网站访问", ico:"images/folderopen.gif"},
	{ level:2, name:"host排名",ico:"images/icon_default.gif",link:"main/zs.jsp"},
	{ level:2, name:"网站分析",ico:"images/icon_default.gif",link:"main/Host.jsp"},
	{ level:2, name:"推荐系统",ico:"images/icon_default.gif"},
	{ level:1, name:"流量监控", ico:"images/folderopen.gif"},
	{ level:2, name:"告警策略",ico:"images/icon_default.gif"},
	{ level:2, name:"告警管理",ico:"images/icon_default.gif"},
	{ level:1, name:"IPv6", ico:"images/folderopen.gif"},
	{ level:2, name:"流量分析",ico:"images/icon_default.gif",link:"main/device.html"},
	{ level:2, name:"应用分析",ico:"images/icon_default.gif"},
	{ level:2, name:"对比分析",ico:"images/icon_default.gif"},
	
	
];
</script>

<!-- <div id="menuControll">
菜单控制:【<a href="#" onclick="tree.openAll();this.blur();return false;" style="color:#333333;text-decoration:none">展开</a>】
【<a href="#" onclick="tree.closeAll();this.blur();return false;" style="color:#333333;text-decoration:none">折叠</a>】
</div> -->
<div class="dtree" style="margin:10px;">
<script type="text/javascript"> 
//建立新树
tree = new dTree('tree');
tree.config.target = "mainFrame";
tree.config.useCookies = false;
var selNum = -1;
var link = "";
//根目录
tree.add(0,-1,'业务分析', null, null, null, '', '');
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