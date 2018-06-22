<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>幼儿家长修改密码</title>

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

<script type="text/javascript">
	function prompt() {

		if ($("#original").val() == "") {

			layer.msg('请输入原始密码！', {
				time : 1000
			//1s后自动关闭
			});
			return;
		} else if ($("#newpass").val() == "") {

			layer.msg('请输入新密码！', {
				time : 1000
			});
			return;
		} else if ($("#passagain").val() == "") {

			layer.msg('请再次出入新密码！', {
				time : 1000
			});
			return;

		} else if ($("#original").val() != '${ userPassword }') {

			layer.msg('输入的原密码错误，请重新输入！', {
				time : 1000
			});
			return;
		}

		else if ($("#newpass").val() != $("#passagain").val()) {

			layer.msg('输入的两次新密码不一致，请重新输入！！', {
				time : 1000
			});
			return;

		} else {

			var form = $("#update");
			form.submit();
			window.top.location = '/InteractiveSystem/login.action';
		}

	}
</script>

</head>
<body>

	<jsp:include page="/subjective/ParentHead.jsp"></jsp:include>

	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
	</div>

	<form id="update" class="layui-form"
		action="/InteractiveSystem/modifyPassword.action" method="post">

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<fieldset class="layui-elem-field layui-field-title"
					style="margin-top: 20px;">
					<legend>修改个人密码</legend>
				</fieldset>
			</div>
			<div class="col-md-2"></div>
		</div>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-2">

				<button type="button" class="btn btn-default btn-sm"
					disabled="disabled">
					<span class="glyphicon glyphicon-user"
						style="text-shadow: black 5px 3px 3px;"></span> 个人账户 : <label>${ userAccount }</label>
				</button>

			</div>
			<div class="col-md-2">
				<button type="button" class="btn btn-default btn-sm"
					disabled="disabled">
					<span class="glyphicon glyphicon-user"
						style="text-shadow: black 5px 3px 3px;"></span> 用户名 : <label>${ username }</label>
				</button>

			</div>
			<div class="col-md-4"></div>
		</div>

		<br> <br>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<label for="original">请输入原密码 : </label><input type="password"
					id="original" name="original" class="form-control"
					placeholder=" 请输入原密码..."> <br>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<label for="new">请输入新密码 : </label><input type="password"
					id="newpass" name="newpass" class="form-control"
					placeholder="  请输入新密码..."> <br>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<label for="newagain">请再次输入新密码 : </label><input type="password"
					name="passagain" id="passagain" class="form-control"
					placeholder="  请再次输入新密码..."> <br>
			</div>
			<div class="col-md-4"></div>
		</div>

		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<button type="button" class="form-control" onclick="prompt();">确认修改密码</button>
			</div>
			<div class="col-md-4"></div>
		</div>
	</form>
	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>
</body>
</html>