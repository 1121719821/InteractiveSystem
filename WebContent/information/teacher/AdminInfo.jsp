<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿教师信息管理</title>

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

<style type="text/css">
.textarea {
	display: block;
	width: 660px;
	height: 70px;
	padding: 6px 12px;
	font-size: 14px;
	line-height: 1.42857143;
	color: #555;
	background-color: #fff;
	background-image: none;
	border: 1px solid #ccc;
	border-radius: 4px;
	-webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075);
	-webkit-transition: border-color ease-in-out .15s, -webkit-box-shadow
		ease-in-out .15s;
	-o-transition: border-color ease-in-out .15s, box-shadow ease-in-out
		.15s;
	transition: border-color ease-in-out .15s, box-shadow ease-in-out .15s
}
</style>

<script type="text/javascript">
	function updateTeacher() {

		var teacherName = $("#teacherName").val();
		var sex = $("#sex").val();
		var age = $("#age").val();
		var telephone = $("#telephone").val();
		var graduation = $("#graduation").val();
		var qq = $("#qq").val();
		var weixin = $("#weixin").val();
		var experience = $("#experience").val();
		var specialty = $("#specialty").val();
		
		if(age < 20 || age > 60){
			
			layer.msg('年龄不在所属范围内，请重新填写年龄！', {
				time : 1000
			});
			return;
		}
		
		if(telephone.length != 11 || telephone.length > 11){
			
			layer.msg('手机号码不符合规定位数，请重新输入！', {
				time : 1000
			});
			return ;
		}
		
		if(graduation.length < 5 || graduation.length > 15){
			
			layer.msg('毕业学校的字数不在允许范围内，请重新输入！', {
				time : 1000
			});
			return ;
		}
		
		if(qq.length > 12 || qq.length < 5){
			
			layer.msg('QQ的位数不在允许范围内，请重新输入！', {
				time : 1000
			});
			return ;
		}
		
		if(weixin.length > 15 || weixin.length < 5){
			
			layer.msg('微信的位数不在允许范围内，请重新输入！', {
				time : 1000
			});
			return ;
		}
		
		if(experience.length > 400){
			
			layer.msg('工作经验字数限制200，请重新输入！', {
				time : 1000
			});
			return ;
		}
		
		if(specialty.length > 400){
			
			layer.msg('个人特长字数限制200，请重新输入！', {
				time : 1000
			});
			return ;
		}
		

		jQuery.ajax({

			url : "/InteractiveSystem/updateTeacher.action",
			type : "get",
			data : {

				teacherName : teacherName,
				sex : sex,
				age : age,
				telephone : telephone,
				graduation : graduation,
				qq : qq,
				weixin : weixin,
				experience : experience,
				specialty : specialty
			},
			error : function() {

				alert('更新信息失败 ！');
			},
			success : function(data) {

				alert(data);
			}
		});
	}
</script>

</head>
<body>

	<jsp:include page="/subjective/TeacherHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span><a href="/InteractiveSystem/selectAllFamily.action">
					查看家长信息 </a></span> <span class="now"><a
				href="/InteractiveSystem/selectTeacher.action"> 修改个人信息 </a></span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label>姓名 ： </label><input type="text" id="teacherName"
				readonly="readonly" class="form-control"
				value="${ teacher.teacherName }">
		</div>
		<div class="col-md-3"></div>
		<div class="col-md-3"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label>级别 ： </label><input type="text" id="teacherLevel"
				readonly="readonly" class="form-control"
				value="${ teacher.teacherLevel }">
		</div>
		<div class="col-md-3">
			<label>所属班级 ： </label><input type="text" readonly="readonly"
				class="form-control" value="${ teacher.classroom }">
		</div>
		<div class="col-md-3"></div>
	</div>
	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label>性别 ： </label> <select class="form-control" id="sex">
				<option>${ teacher.sex }</option>
				<option>女</option>
				<option>男</option>
			</select>
		</div>
		<div class="col-md-3">
			<label>年龄 ： </label> <input type="number" id="age"
				class="form-control" value="${ teacher.age }">
		</div>
		<div class="col-md-3"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label>电话号码 ： </label><input type="number" id="telephone"
				class="form-control" value="${ teacher.telephone }">
		</div>
		<div class="col-md-3">
			<label>毕业学校 ： </label><input type="text" id="graduation"
				class="form-control" value="${ teacher.graduation }">
		</div>
		<div class="col-md-3"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label> QQ： </label><input type="number" id="qq" class="form-control"
				value="${ teacher.qq }">
		</div>
		<div class="col-md-3">
			<label>微信 ： </label><input type="text" id="weixin"
				class="form-control" value="${ teacher.weixin }">
		</div>
		<div class="col-md-3"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label>工作经验 ： </label>
			<textarea id="experience" class="textarea"> ${ teacher.experience } </textarea>
		</div>
		<div class="col-md-6"></div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-3">
			<label> 特长： </label>
			<textarea id="specialty" class="textarea">${ teacher.specialty } </textarea>
		</div>
		<div class="col-md-3"></div>
		<div class="col-md-3"></div>
	</div>

	<div class="row">
		<div class="col-md-12">
			<br>
		</div>
	</div>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<button type="button" class="form-control" onclick="updateTeacher();">提交个人信息</button>
		</div>
		<div class="col-md-3"></div>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>

</body>
</html>