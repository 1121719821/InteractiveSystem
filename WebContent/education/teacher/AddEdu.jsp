<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿教师添加教育案例</title>

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

	<jsp:include page="/subjective/TeacherHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span><a href="/InteractiveSystem/teaSelectEdu.action">
					查看教育案例 </a></span> <span class="now"><a
				href="/InteractiveSystem/teaEnterEdu.action"> 添加教育案例</a></span>
		</div>
	</div>

	<br>
	<br>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<label for="title">案例标题 ： </label><input type="text"
				class="form-control" id="title" placeholder="  请输入叫案例标题...">
		</div>
		<div class="col-md-2"></div>
	</div>

	<br>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">

			<textarea class="layui-textarea" id="LAY_demo1" style="display: none"></textarea>

			<div class="site-demo-button" style="margin-top: 20px;">
				<button class="layui-btn site-demo-layedit" data-type="content">添加新的消息通知</button>
			</div>

			<script>
				layui.use('layedit', function() {
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

							if ($("#title").val() == "") {

								layer.msg('请填写案例标题！', {
									time : 1000
								});
								return;
							}

							if ($("#title").val().length > 20) {

								layer.msg('案例标题不允许超出20个字，请重新输入！', {
									time : 1000
								});

								return;
							}

							if (layedit.getContent(index) == "") {

								layer.msg('请填写案例内容！', {
									time : 1000
								});
								return;
							}

							if (layedit.getContent(index).length < 20) {

								layer.msg('案例内容不允许低于20个字，请重新输入！', {
									time : 1000
								});
								return;
							}

							if (layedit.getContent(index).length > 1000) {

								layer.msg('案例内容不允许超过1000个字，请重新输入！', {
									time : 1000
								});
								return;
							}

							jQuery.ajax({

								url : "/InteractiveSystem/teaAddEdu.action",
								async : false,
								type : "post",
								data : {
									title : $("#title").val(),
									content : layedit.getContent(index)
								},
								error : function() {

									alert("发布通知失败！...");
								},
								success : function(data) {

									alert(data);
								}

							});
							window.location.reload();
						}
					};

					$('.site-demo-layedit').on('click', function() {
						var type = $(this).data('type');
						active[type] ? active[type].call(this) : '';
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