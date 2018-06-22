<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>幼儿园园长主界面</title>

<link rel="stylesheet" href="/InteractiveSystem/other/bootstrap.min.css">
<script src="/InteractiveSystem/other/jquery.min.js"></script>
<script src="/InteractiveSystem/other/jquery-3.2.1.min.js"></script>
<script src="/InteractiveSystem/other/bootstrap.min.js"></script>
<link rel="stylesheet" href="/InteractiveSystem/other/thems.css">

<link rel="stylesheet" href="/InteractiveSystem/layui/css/layui.css">
<script src="/InteractiveSystem/layui/lay/dest/layui.all.js"></script>

<style type="text/css">
.option {
	color: red;
	font-size: smaller;
	font-family: sans-serif;
	font-weight: bolder;
}
</style>

<script>
	;
	!function() {
		//当使用了 layui.all.js，无需再执行layui.use()方法
		var from = layui.form(), layer = layui.layer;

	}();
</script>
<script type="text/javascript">
	function admin01() {
		$('#select').fadeIn("slow");
		$('#admin').fadeOut("slow");
	}

	function admin02() {
		$('#select').fadeOut("slow");
		$('#admin').fadeIn("slow");
	}

	// 查看幼师详情
	function selectTeacherDetails(teacherName) {

		jQuery.ajax({

			url : '/InteractiveSystem/teacherDetails.action',
			type : 'post',
			data : {
				teacherName : teacherName
			},
			error : function() {

				alert('获取幼师详情出现异常！');
			},
			success : function(data) {

				var teacher = JSON.parse(data);

				var teacherDetails = "";
				teacherDetails += "<p>幼师名&nbsp;	&nbsp; :	&nbsp;	&nbsp;<b>"
						+ teacher.teacherName + "</b></p><br>";
				teacherDetails += "<p>幼师级别&nbsp;	&nbsp; :&nbsp;	&nbsp;<b>"
						+ teacher.teacherLevel + "</b></p>";
				teacherDetails += "<p>所在班级 &nbsp;	&nbsp;:&nbsp;	&nbsp;<b>"
						+ teacher.classroom + "</b></p><br>";
				teacherDetails += "<p>性别&nbsp;	&nbsp; :&nbsp;	&nbsp;<b>"
						+ teacher.sex + "</b></p>";
				teacherDetails += "<p>年龄&nbsp;	&nbsp; :&nbsp;	&nbsp;<b>"
						+ teacher.age + "</b></p><br>";
				teacherDetails += "<p>手机 &nbsp;	&nbsp;:&nbsp;	&nbsp;<b>"
						+ teacher.telephone + "</b></p>";
				teacherDetails += "<p>QQ &nbsp;	&nbsp;:&nbsp;	&nbsp;<b>"
						+ teacher.qq + "</b></p>";
				teacherDetails += "<p>微信 &nbsp;	&nbsp;:&nbsp;	&nbsp;<b>"
						+ teacher.weixin + "</b></p><br>";
				teacherDetails += "<p>毕业院校 &nbsp;	&nbsp;:&nbsp;	&nbsp;<b>"
						+ teacher.graduation + "</b></p><br>";
				teacherDetails += "<p>工作经验 &nbsp;	&nbsp;:&nbsp;	&nbsp;"
						+ teacher.experience + "</p><br>";
				teacherDetails += "<p>专业特长 &nbsp;	&nbsp;:&nbsp;	&nbsp;"
						+ teacher.specialty + "</p>";

				$("#teacherDetails").html(teacherDetails);
			}
		});
	}

	// 添加幼师用户
	function addTeacher() {

		var teacherName = $("#teacherName02").val();
		var useraccount = $("#useraccount").val();
		var teacherLevel = $("#teacherLevel").val();
		var classroom = $("#classroom").val();

		if (teacherName == "" || useraccount == "" || teacherLevel == ""
				|| classroom == "") {
			layer.msg('用户名、账户、级别以及所在班级都不可以为空！');
			return;
		}

		if (teacherName.length > 16) {

			layer.msg('用户名长度超出限定范围，请重新输入用户名！');
			return;
		}

		if (useraccount.length > 10) {

			layer.msg('用户账户长度超出规定范围，请重新输入用户名！');
			return;
		}

		jQuery.ajax({

			url : "/InteractiveSystem/addTeacher.action",
			type : "post",
			async : false,
			data : {
				teacherName : teacherName,
				useraccount : useraccount,
				teacherLevel : teacherLevel,
				classroom : classroom
			},
			error : function() {
				alert('添加教师用户失败 ！');
			},
			success : function(data) {

				if (data != "") {

					alert('用户账户已被占用！ ');
				}
			}
		});

		window.location.reload();
	}

	function deleteTeacher(teacherName) {

		layer.confirm('确定删除该用户？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {

			jQuery.ajax({

				url : '/InteractiveSystem/deleteTeacher.action',
				type : 'post',
				async : false,
				data : {
					teacherName : teacherName
				},
				error : function() {
					alert('删除幼师失败 ！');
				}
			});

			window.location.reload();
			/*
			layer.msg('的确很重要', {
				icon : 1
			});
			 */
		}, function() {
			layer.msg('取消删除', {
				time : 1000, //2s后自动关闭
			});
		});

	}

	function editClassroom() {

	}
</script>

</head>
<body>

	<div class="scd">
		<div class="scd_t">
			<span class="now"><a onclick="admin01();"
				href="/InteractiveSystem/principalSelectAllTeacher.action">
					查看幼师信息 </a></span> <span><a onclick="admin02();"> 管理幼师用户 </a></span><span><a
				href="/InteractiveSystem/prinEnterEdu.action"> 添加教育案例 </a></span> <span><a
				href="/InteractiveSystem/principalModifyPass.action"> 修改个人密码 </a></span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>

	<div id="admin" hidden="true">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<label>幼师名：</label><input type="text" id="teacherName02"
					class="form-control">
			</div>
			<div class="col-md-3">
				<label>用户账户：</label> <input type="text" id="useraccount"
					class="form-control">
			</div>
			<div class="col-md-3"></div>
		</div>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<label>用户级别：</label> <select class="form-control" id="teacherLevel">
					<option>主班</option>
					<option>副班</option>
				</select>
			</div>
			<div class="col-md-3">
				<label>所在班级</label> <select class="form-control" id="classroom">
					<option class="option" disabled="disabled">大班 :</option>
					<option>大(一)班</option>
					<option>大(二)班</option>
					<option class="option" disabled="disabled">中班 :</option>
					<option>中(一)班</option>
					<option>中(二)班</option>
					<option class="option" disabled="disabled">小班 :</option>
					<option>小(一)班</option>
					<option>小(二)班</option>
				</select>
			</div>
			<div class="col-md-3"></div>
		</div>

		<br>

		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-6">
				<button type="button" class="form-control" onclick="addTeacher();">添加教师</button>
			</div>
			<div class="col-md-3"></div>
		</div>
	</div>

	<br>

	<div class="row" id="select">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><label>#</label></th>
						<th><label>幼师名</label></th>
						<th><label>所属班级</label></th>
						<th><label>幼师级别</label></th>
						<th><label>详细信息</label></th>
						<th><label>删除用户</label></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${ allTeacher }" var="allTeacher" varStatus="vs">
						<tr class="info">
							<th scope="row">${ vs.index + 1 }</th>
							<td><label>${ allTeacher.teacherName }</label></td>
							<td><label onclick="editClassroom();">${ allTeacher.classroom }</label></td>
							<td><label>${ allTeacher.teacherLevel }</label></td>
							<td><button class="btn" data-toggle="modal"
									data-target="#myModal"
									onclick="selectTeacherDetails('${allTeacher.teacherName}');">查看详情</button></td>
							<td>
								<button class="layui-btn"
									onclick="deleteTeacher('${allTeacher.teacherName}');">
									<i class="layui-icon"></i>
								</button>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">幼师详细信息</h4>
				</div>
				<div class="modal-body" id="teacherDetails"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

</body>