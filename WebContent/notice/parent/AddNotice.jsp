<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿家长添加留言</title>

<link rel="stylesheet" href="/InteractiveSystem/other/bootstrap.min.css">
<script src="/InteractiveSystem/other/jquery.min.js"></script>
<script src="/InteractiveSystem/other/jquery-3.2.1.min.js"></script>
<script src="/InteractiveSystem/other/bootstrap.min.js"></script>
<link rel="stylesheet" href="/InteractiveSystem/other/thems.css">

<link rel="stylesheet" href="/InteractiveSystem/layui/css/layui.css">
<script src="/InteractiveSystem/layui/lay/dest/layui.all.js"></script>


<script>
	;
	!function() {
		//当使用了 layui.all.js，无需再执行layui.use()方法
		var from = layui.form(), layer = layui.layer;

	}();
</script>

</head>
<body>

	<jsp:include page="/subjective/ParentHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span> <a onclick="admin01();"
				href="/InteractiveSystem/parentSelectMessage.action">查看家长留言</a></span> <span
				class="now"> <a
				href="/InteractiveSystem/parentMessage.action"> 添加消息通知 </a>
			</span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">

			<textarea class="layui-textarea" id="LAY_demo1" style="display: none"></textarea>

			<div class="site-demo-button" style="margin-top: 20px;">
				<button class="layui-btn site-demo-layedit" data-type="content">添加新的消息通知</button>
				<button class="layui-btn site-demo-layedit" data-type="text">取消并清除编辑器</button>
			</div>

			<script>
				layui
						.use(
								'layedit',
								function() {
									var layedit = layui.layedit, $ = layui.jquery;

									//构建一个默认的编辑器
									var index = layedit.build('LAY_demo1', {
										tool : [ 'strong' //加粗
										, 'italic' //斜体
										, 'underline' //下划线
										, 'del' //删除线

										, '|' //分割线

										, 'left' //左对齐
										, 'center' //居中对齐
										, 'right' //右对齐

										, '|' //分割线

										, 'face' //表情
										]
									});

									//编辑器外部操作
									var active = {
										content : function() {

											jQuery
													.ajax({

														url : "/InteractiveSystem/parentAddMessage.action",
														type : "post",
														data : {
															content : layedit
																	.getContent(index)
														},
														error : function() {

															alert("发布通知失败！...");
														},
														success : function(data) {

															if (data != "") {

																alert(data);
															} else {

																alert('发布消息成功!');
															}
														}

													});

											window.location.reload();

										},
										text : function() {
											alert(layedit.getText(index)); //获取编辑器纯文本内容
										}
									};

									$('.site-demo-layedit')
											.on(
													'click',
													function() {
														var type = $(this)
																.data('type');
														active[type] ? active[type]
																.call(this)
																: '';
													});

								});
			</script>

		</div>
		<div class="col-md-2"></div>
	</div>

	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>
</body>
</html>