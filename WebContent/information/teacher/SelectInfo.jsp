<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿教师信息查询</title>

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
	function admin01() {
		$('#select').fadeIn("slow");
		$('#admin').fadeOut("slow");
	}

	function admin02() {
		$('#select').fadeOut("slow");
		$('#admin').fadeIn("slow");
	}

	function selectChildDetails(childName) {

		jQuery.ajax({

			url : "/InteractiveSystem/childDetails.action",
			data : {
				childName : childName
			},
			error : function() {

				alert('获取幼儿详情失败！');
			},
			success : function(data) {

				var family = JSON.parse(data);

				var childDetails = "";
				childDetails += "<p>幼儿名&nbsp;&nbsp; ：&nbsp;&nbsp;<b> "
						+ family.childName + "</b></p><br>";
				childDetails += "<p>幼儿生日 &nbsp;&nbsp;：&nbsp;&nbsp; <b>"
						+ family.childBirthday + "</b></p><br>";
				childDetails += "<p>幼儿身份证号&nbsp;&nbsp; ：&nbsp;&nbsp; <b>"
						+ family.childCard + "</b></p><br>";
				childDetails += "<p>幼儿身体状况 &nbsp;&nbsp;： &nbsp;&nbsp;<b>"
						+ family.physicalCondition + "</b></p><br>";
				childDetails += "<p>备注 &nbsp;&nbsp;：&nbsp;&nbsp; <b>"
						+ family.childRemarks + "</b></p><br>";

				$("#childDetails").html(childDetails);
			}

		});
	}

	function selectFamilyDetails(childName) {

		jQuery.ajax({

			url : "/InteractiveSystem/familyDetails.action",
			data : {
				childName : childName
			},
			error : function() {

				alert('获取家庭详情失败！');
			},
			success : function(data) {

				var family = JSON.parse(data);

				var childDetails = "";
				childDetails += "<p>家庭ID &nbsp;&nbsp;：&nbsp;&nbsp; "
						+ family.familyId + "</p>";
				childDetails += "<br><p>父亲名&nbsp;&nbsp; ：&nbsp;&nbsp; <b>"
						+ family.fatherName + "</b></p>";
				childDetails += "<p>父亲年龄 &nbsp;&nbsp;：&nbsp;&nbsp; "
						+ family.fatherAge + "</p>";
				childDetails += "<p>父亲联系方式 &nbsp;&nbsp;：&nbsp;&nbsp; <b>"
						+ family.fatherTel + "</b></p>";
				childDetails += "<p>父亲工作&nbsp;&nbsp; ： &nbsp;&nbsp;"
						+ family.fatherWork + "</p>";
				childDetails += "<br><p>母亲名&nbsp;&nbsp; ：&nbsp;&nbsp; <b>"
						+ family.motherName + "</b></p>";
				childDetails += "<p>母亲年龄 &nbsp;&nbsp;：&nbsp;&nbsp;"
						+ family.motherAge + "</p>";
				childDetails += "<p>母亲联系方式 &nbsp;&nbsp;：&nbsp;&nbsp; <b>"
						+ family.motherTel + "</b></p>";
				childDetails += "<p>母亲工作 &nbsp;&nbsp;：&nbsp;&nbsp;"
						+ family.motherWork + "</p>";
				childDetails += "<br><p>家庭状态&nbsp;&nbsp; ：&nbsp;&nbsp; "
						+ family.familySituation + "</p>";
				childDetails += "<p>家庭地址 &nbsp;&nbsp;：&nbsp;&nbsp; "
						+ family.address + "</p>";

				$("#familyDetails").html(childDetails);
			}

		});
	}

	function addFamily() {

		var childName = $("#childName").val();
		var userAccount = $("#userAccount").val();

		if (childName == "" || userAccount == "") {

			layer.msg('幼儿名、用户账户都不可以为空 ！');
			return;
		}
		
		if (childName.length > 16) {

			layer.msg('用户名长度超出限定范围，请重新输入用户名！');
			return;
		}

		if (userAccount.length > 10) {

			layer.msg('用户账户长度超出规定范围，请重新输入用户名！');
			return;
		}

		jQuery.ajax({

			url : "/InteractiveSystem/addFamily.action",
			async : false,
			data : {
				childName : childName,
				userAccount : userAccount
			},
			error : function() {

				alert('添加用户是失败！');
			},
			success : function(data) {

				if (data != "") {

					alert('用户账户已被占用！ ');
				}
			}
		});

		//window.location.reload();
		//window.location.replace(location);
		window.location = location;
	}

	function deleteFamily(childName) {

		layer.confirm('确定要删除该家庭信息以及家庭账户吗？', {
			btn : [ '删除', '取消' ]
		//按钮
		}, function() {

			jQuery.ajax({

				url : '/InteractiveSystem/deleteFamily.action',
				type : 'post',
				async : false,
				data : {
					childName : childName
				},
				error : function() {
					alert('删除家庭信息以及账户失败！');
				}

			});

			window.location = location;

		}, function() {
			layer.msg('取消删除', {
				time : 1000, //1s后自动关闭
			});
		});

	}
</script>

</head>
<body>

	<jsp:include page="/subjective/TeacherHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span class="now"> <a onclick="admin01();"
				href="/InteractiveSystem/selectAllFamily.action"> 查看家庭信息 </a></span> <span>
				<a onclick="admin02();"> 管理家庭用户 </a>
			</span> <span><a href="/InteractiveSystem/selectTeacher.action">
					修改个人信息 </a></span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>

	<div id="admin" hidden="true">
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3">
				<label>孩子名：</label><input type="text" id="childName"
					class="form-control">
			</div>
			<div class="col-md-3">
				<label>用户账户：</label> <input type="text" id="userAccount"
					class="form-control">
			</div>
			<div class="col-md-3"></div>
		</div>
		<br />
		<div class="row">
			<div class="col-md-4"></div>
			<div class="col-md-4">
				<button class="form-control" onclick="addFamily();">添加用户</button>
			</div>
			<div class="col-md-4"></div>
		</div>
	</div>

	<br>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><label>#</label></th>
						<th><label>孩子名</label></th>
						<th><label>父亲名</label></th>
						<th><label>母亲名</label></th>
						<th><label>幼儿详细</label></th>
						<th><label>家庭详细</label></th>
						<th><label>删除用户</label></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${ allFamily }" var="allFamily" varStatus="vs">
						<tr class="info">
							<th scope="row">${ vs.index + 1 }</th>
							<td><label>${ allFamily.childName }</label></td>
							<td><label>${ allFamily.fatherName }</label></td>
							<td><label>${ allFamily.motherName }</label></td>

							<td><button class="btn" data-toggle="modal"
									data-target="#myModal01"
									onclick="selectChildDetails('${ allFamily.childName }');">查看幼儿详情</button></td>
							<td><button class="btn" data-toggle="modal"
									data-target="#myModal02"
									onclick="selectFamilyDetails('${ allFamily.childName }');">查看家庭信息</button></td>
							<td><button class="layui-btn"
									onclick="deleteFamily('${ allFamily.childName }');">
									<i class="layui-icon"></i>
								</button></td>
						</tr>
					</c:forEach>


				</tbody>
			</table>
		</div>
	</div>

	<div class="modal fade" id="myModal01" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">幼儿信息详情</h4>
				</div>
				<div class="modal-body" id="childDetails"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>


	<div class="modal fade" id="myModal02" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">家庭信息详情</h4>
				</div>
				<div class="modal-body" id="familyDetails"></div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭
					</button>
				</div>
			</div>
		</div>
	</div>

	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>

</body>
</html>