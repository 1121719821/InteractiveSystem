<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>幼儿家长界面</title>
</head>

<link rel="stylesheet" href="/InteractiveSystem/layui/css/layui.css">
<script src="/InteractiveSystem/layui/lay/dest/layui.all.js"></script>
<script>
	;
	!function() {
		//当使用了 layui.all.js，无需再执行layui.use()方法
		var from = layui.form(), layer = layui.layer;

		layer.open({
			type : 2, //iframe层
			title : '欢迎使用聊天系统 ！',
			closeBtn : 0, //不显示关闭按钮
			shadeClose : true,
			shade : false,
			maxmin : true, //开启最大化最小化按钮
			area : [ '800px', '400px' ],
			content : '/InteractiveSystem/chat.jsp'
		});
	}();
</script>

<body>


	<iframe src="/InteractiveSystem/information/parent/SelectInfo.jsp"
		width="100%" height="646px"></iframe>

</body>
</html>