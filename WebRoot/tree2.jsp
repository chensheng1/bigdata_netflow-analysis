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
	{ level:1, name:"系统管理"},
	{ level:2, name:"系统时间",ico:"images/icon_default.gif"},
	{ level:2, name:"密码修改",ico:"images/icon_default.gif"},
	{ level:2, name:"系统重启",ico:"images/icon_default.gif"},
	{ level:2, name:"系统关机",ico:"images/icon_default.gif"},
	{ level:2, name:"系统名称",ico:"images/icon_default.gif"},
	{ level:1, name:"配置管理", ico:"images/folderopen.gif"},
	{ level:2, name:"配置导入",ico:"images/icon_default.gif"},
	{ level:2, name:"配置导出",ico:"images/icon_default.gif"},
	{ level:2, name:"配置同步",ico:"images/icon_default.gif"},
	{ level:1, name:"日志管理", ico:"images/folderopen.gif"},
	{ level:2, name:"设备编号",ico:"images/icon_default.gif"},
	{ level:2, name:"流量日志",ico:"images/icon_default.gif"},
	{ level:2, name:"会话日志",ico:"images/icon_default.gif"},
	{ level:2, name:"URL日志",ico:"images/icon_default.gif"},
	{ level:2, name:"其他事件",ico:"images/icon_default.gif"},
	{ level:2, name:"清除流量日志",ico:"images/icon_default.gif"},
	{ level:1, name:"管理日志", ico:"images/folderopen.gif"},
	{ level:2, name:"今日日志",ico:"images/icon_default.gif"},
	{ level:2, name:"历史日志",ico:"images/icon_default.gif"},
	{ level:2, name:"在线用户",ico:"images/icon_default.gif"},
	
	
];
</script>

 <!--  <div id="menuControll">
菜单控制:【<a href="#" onclick="tree.openAll();this.blur();return false;" style="color:#333333;text-decoration:none">展开</a>】
【<a href="#" onclick="tree.closeAll();this.blur();return false;" style="color:#333333;text-decoration:none">折叠</a>】
</div> -->

<!-- <div class="dtree" style="margin:10px;"> -->

<div class="dtree" style="width:20%;margin:2px; height:360px; float:left">
<script type="text/javascript"> 
//建立新树
tree = new dTree('tree');
tree.config.target = "mainFrame";
tree.config.useCookies = false;
var selNum = -1;
var link = "";
//根目录
tree.add(0,-1,'系统管理', null, null, null, '', '');
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

<div class="maincontent" style="width:78%;margin:2px; height:360px; float:left">

</div>
</body>
</html>