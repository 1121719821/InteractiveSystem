<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>登 录 界 面</title>

<link rel="stylesheet" href="other/bootstrap.min.css">
<script type="text/javascript" src="other/bootstrap.min.js"></script>
<script type="text/javascript" src="other/jquery.min.js"></script>

<style type="text/css">
* {
	margin: 0px;
	padding: 0px;
}

.center {
	margin: auto;
}

.txtbox {
	border: 1px solid #C0C0C0;
	width: 243px;
	height: 40px;
	font-size: 16px;
	margin-top: 10px;
	margin-bottom: 20px;
	padding-top: 10px;
	position: relative;
	top: 1px;
	left: -5px;
}

.icon {
	border: 1px solid #C0C0C0;
	margin-bottom: 10px;
}

a {
	font-family: 宋体;
	font-size: 14px;
	color: #292929;
	text-decoration: none;
}

#utb {
	width: 870px;
}

#centerFrame {
	width: 100%;
	height: 530px;
	background: #66FF33;
}

#tableFrame {
	width: 1010px;
	height: 472px;
}

#innerFrame {
	background-color: white;
	border: 1px solid #C0C0C0;
	padding-left: 50px;
	padding-right: 50px;
}

#jdmember {
	font-family: 微软雅黑;
	font-size: 20px;
	color: #292929;
}

#Button1 {
	width: 290px;
	height: 40px;
	border-style: none;
	background-color: #E3393C;
	font-family: 黑体;
	font-size: 18px;
	color: #FFFFFF;
	margin-top: 15px;
	margin-bottom: 15px;
}
</style>

</head>
<body>

	<form action="login.action" method="get">

		<table id="utb" class="center">
			<tr>
				<td align="left"><img src="other/image/image011.png" /></td>
			</tr>
		</table>
		<div id="centerFrame">
			<table id="tableFrame" class="center">
				<tr>
					<td width="600px"><img alt="" src="other/image/image11.jpg">
					</td>
					<td width="390px">
						<div id="innerFrame">
							<table>
								<tr>
									<td height="40px"><br /> <label id="jdmember">
											用户登录 </label></td>
								</tr>
								<tr>
									<td colspan="2"><img class="icon"
										src="other/image/username.png"></img> <input
										name="useraccount" type="text" class="txtbox"
										placeholder="please input name" required="required" /></td>
								</tr>
								<tr>
									<td colspan="2"><img class="icon"
										src="other/image/password.png"></img> <input name="password"
										type="password" class="txtbox"
										placeholder="please input password" required="required" /></td>
								</tr>
								<tr>
									<td colspan="2"><label for="role"
										class="col-sm-4 control-label">选择身份</label> <input
										type="radio" name="role" value="1">园长 <input
										type="radio" name="role" value="2">幼师 <input
										type="radio" name="role" value="3" checked="checked">家长
									</td>
								</tr>
								<tr>
									<td colspan="2"><label class="col-sm-6 control-label">
											${ warning } </label></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="Button1"
										value="登  录" class="form-control" /></td>
								</tr>
								<tr>
									<td><br><br></td>
								</tr>
							</table>
						</div>
					</td>
				</tr>
			</table>
		</div>
	</form>

	<div style="text-align: center;">
		<b>版权所有 © 辽宁工程技术大学 软件13-6 宋长军</b>
	</div>

</body>
</html>