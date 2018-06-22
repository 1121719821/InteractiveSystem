<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿家长信息管理</title>

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
	function updateFamily() {

		var childName = $("#childName").val();
		var childBirthday = $("#childBirthday").val();
		var childCard = $("#childCard").val();
		var familySituation = $("#familySituation").val();
		var physicalCondition = $("#physicalCondition").val();
		var childRemarks = $("#childRemarks").val();
		var fatherName = $("#fatherName").val();
		var fatherAge = $("#fatherAge").val();
		var fatherTel = $("#fatherTel").val();
		var fatherWork = $("#fatherWork").val();
		var motherName = $("#motherName").val();
		var motherAge = $("#motherAge").val();
		var motherTel = $("#motherTel").val();
		var motherWork = $("#motherWork").val();
		var address = $("#address").val();

		if (childBirthday == "") {

			layer.msg('幼儿生日不能为空！', {
				time : 1000
			});
			return;
		}

		if (childCard.length != 18) {

			layer.msg('身份证位数不对，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (fatherName == "") {

			layer.msg('请输入幼儿父亲名字！', {
				time : 1000
			});
			return;
		}

		if (fatherTel == "") {

			layer.msg('请输入幼儿父亲联系方式！', {
				time : 1000
			});
			return;
		}

		if (motherName == "") {

			layer.msg('请输入幼儿母亲名字！', {
				time : 1000
			});
			return;
		}

		if (motherTel == "") {

			layer.msg('请输入幼儿母亲联系方式！', {
				time : 1000
			});
			return;
		}

		if (familySituation.length > 25) {

			layer.msg('填写家庭状态字数限定范围，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (physicalCondition.length > 50) {

			layer.msg('填写孩子身体状况字数限定范围，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (childRemarks.length > 50) {

			layer.msg('填写的孩子备注超出限定范围，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (fatherName.length > 10) {

			layer.msg('名字长度超出限制，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (fatherWork.length > 20) {

			layer.msg('工作内容超出限定范，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (motherName.length > 10) {

			layer.msg('名字长度超出限制，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (motherWork.length > 20) {

			layer.msg('工作内容超出限定范围，请重新输入！', {
				time : 1000
			});
			return;
		}

		if (address.length > 50) {

			layer.msg('家庭地址超出限定范围，请重新输入！', {
				time : 1000
			});
			return;
		}

		jQuery.ajax({

			url : '/InteractiveSystem/updateFamily.action',
			type : 'post',
			async : false,
			data : {

				childName : childName,

				childBirthday : childBirthday,
				childCard : childCard,
				familySituation : familySituation,
				physicalCondition : physicalCondition,
				childRemarks : childRemarks,

				fatherName : fatherName,
				fatherAge : fatherAge,
				fatherTel : fatherTel,
				fatherWork : fatherWork,

				motherName : motherName,
				motherAge : motherAge,
				motherTel : motherTel,
				motherWork : motherWork,

				address : address
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

	<jsp:include page="/subjective/ParentHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span><a href="/InteractiveSystem/parentSelectTeacher.action">查看幼师信息
			</a></span><span class="now"><a
				href="/InteractiveSystem/selectInfo.action"> 信息管理 </a></span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>


	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>幼儿名： </label><input type="text" id="childName"
				class="form-control" readonly="readonly"
				value="${ family.childName }">
		</div>
		<div class="col-md-4">
			<label>所在班级 ： </label><input type="text" class="form-control"
				readonly="readonly" value="${ family.classroom }">
		</div>
		<div class="col-md-2"></div>
	</div>

	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>幼儿生日：</label><input type="date" id="childBirthday"
				class="form-control" value="${ family.childBirthday }">
		</div>
		<div class="col-md-4">
			<label>身份证号：</label><input type="number" id="childCard"
				class="form-control" value="${ family.childCard }"
				placeholder="请输入幼儿身份证号...">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<label>家庭情况：</label>
			<textarea class="form-control" id="familySituation">${ family.familySituation }</textarea>
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<label>是否是过敏体质</label> <input type="radio" id="physicalCondition"
				name="whether" checked="checked" value="no">否 <input
				type="radio" id="physicalCondition" name="whether" value="yes">是
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<label>备注： </label>
			<textarea class="form-control" id="childRemarks">${ family.childRemarks }</textarea>
		</div>
		<div class="col-md-2"></div>
	</div>


	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>父亲名： </label><input type="text" id="fatherName"
				class="form-control" value="${ family.fatherName }"
				placeholder="请输入幼儿父亲名...">
		</div>
		<div class="col-md-4">
			<label>父亲年龄 ： </label><input type="number" id="fatherAge"
				class="form-control" value="${ family.fatherAge }">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>父亲联系电话： </label><input type="number" id="fatherTel"
				class="form-control" value="${ family.fatherTel }"
				placeholder="请输入幼儿父亲联系方式...">
		</div>
		<div class="col-md-4">
			<label>父亲工作 ： </label><input type="text" id="fatherWork"
				class="form-control" value="${ family.fatherWork }">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>母亲名： </label><input type="text" id="motherName"
				class="form-control" value="${ family.motherName }"
				placeholder="请输入幼儿母亲名...">
		</div>
		<div class="col-md-4">
			<label>母亲年龄 ： </label><input type="number" id="motherAge"
				class="form-control" value="${ family.motherAge }">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-4">
			<label>母亲联系电话： </label><input type="number" id="motherTel"
				class="form-control" value="${ family.motherTel }"
				placeholder="请输入幼儿母亲里联系方式...">
		</div>
		<div class="col-md-4">
			<label>母亲工作 ： </label><input type="text" id="motherWork"
				class="form-control" value="${ family.motherWork }">
		</div>
		<div class="col-md-2"></div>
	</div>
	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">
			<label>家庭地址： </label>
			<textarea class="form-control" id="address">${ family.address }</textarea>
		</div>
		<div class="col-md-2"></div>
	</div>
	<br>

	<div class="row">
		<div class="col-md-3"></div>
		<div class="col-md-6">
			<button type="button" class="form-control" onclick="updateFamily();">提交个人信息</button>
		</div>
		<div class="col-md-3"></div>
	</div>
	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>
</body>
</html>