<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿家长查询幼师信息</title>

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
	//查看幼师详情
	function selectTeacherDetails(teacherName) {

		jQuery.ajax({

			url : '/InteractiveSystem/teacherDetails.action',
			type : 'get',
			data : {
				teacherName : teacherName
			},
			error : function() {

				alert('获取幼师详情出现异常！');
			},
			success : function(data) {

				var teacher = JSON.parse(data);

				var teacherDetails = "";
				teacherDetails += "<p>幼师名 &nbsp;&nbsp;:&nbsp;&nbsp;<b>"
						+ teacher.teacherName + "</b></p><br>";
				teacherDetails += "<p>所在班级 &nbsp;&nbsp;:&nbsp;&nbsp;<b>"
						+ teacher.classroom + "</b></p><br>";
				teacherDetails += "<p>手机号码&nbsp;&nbsp; :&nbsp;&nbsp;<b>"
						+ teacher.telephone + "</b></p><br>";
				teacherDetails += "<p>QQ &nbsp;&nbsp;:&nbsp;&nbsp;<b>"
						+ teacher.qq + "</b></p>";
				teacherDetails += "<p>微信&nbsp;&nbsp; :&nbsp;&nbsp;<b>"
						+ teacher.weixin + "</b></p>";

				$("#teacherDetails").html(teacherDetails);
			}
		});
	}
</script>

</head>
<body>

	<jsp:include page="/subjective/ParentHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span class="now"><a
				href="/InteractiveSystem/parentSelectTeacher.action">查看幼师信息 </a></span><span><a
				href="/InteractiveSystem/selectInfo.action"> 信息管理 </a></span>
		</div>
	</div>


	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<table class="table table-hover">
				<thead>
					<tr>
						<th><label>#</label></th>
						<th><label>幼师名</label></th>
						<th><label>幼师级别</label></th>
						<th><label>性别</label></th>
						<th><label>年龄</label></th>
						<th><label>详细信息</label></th>
					</tr>
				</thead>
				<tbody>

					<c:forEach items="${ allTeacher }" var="allTeacher" varStatus="vs">
						<tr class="info">
							<th scope="row">${ vs.index + 1 }</th>
							<td><label>${ allTeacher.teacherName }</label></td>
							<td><label>${ allTeacher.teacherLevel }</label></td>
							<td><label>${ allTeacher.sex }</label></td>
							<td><label>${ allTeacher.age }</label></td>
							<td><button class="btn" data-toggle="modal"
									data-target="#myModal"
									onclick="selectTeacherDetails('${allTeacher.teacherName}');">查看详情</button></td>
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

	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>

</body>
</html>