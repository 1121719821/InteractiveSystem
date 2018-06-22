<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿家长查看教育案例</title>

<link rel="stylesheet" href="/InteractiveSystem/other/bootstrap.min.css">
<script src="/InteractiveSystem/other/jquery.min.js"></script>
<script src="/InteractiveSystem/other/jquery-3.2.1.min.js"></script>
<script src="/InteractiveSystem/other/bootstrap.min.js"></script>
<link rel="stylesheet" href="/InteractiveSystem/other/thems.css">

<link rel="stylesheet" href="/InteractiveSystem/layui/css/layui.css">
<script src="/InteractiveSystem/layui/lay/dest/layui.all.js"></script>
<script type="text/javascript" src="/InteractiveSystem/layui/layui.js"></script>

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
			<span class="now"><a
				href="/InteractiveSystem/parSelectEdu.action"> 查看教育案例 </a></span>
		</div>
	</div>


	<div class="row">
		<div class="col-md-2"></div>
		<div class="col-md-8">

			<fieldset class="layui-elem-field layui-field-title"
				style="margin-top: 50px;">
				<legend>更好的与孩子相处</legend>
			</fieldset>

			<div class="layui-collapse" lay-accordion="">

				<c:forEach items="${ parEduList }" var="parEduList" varStatus="vs">

					<div class="layui-colla-item">
						<h2 class="layui-colla-title">${ parEduList.title }</h2>
						<div class="layui-colla-content">
							<p>${ parEduList.content }</p>
						</div>
					</div>

				</c:forEach>



			</div>

			<script>
				layui.use([ 'element', 'layer' ], function() {
					var element = layui.element();
					var layer = layui.layer;

					//监听折叠
					element.on('collapse(test)', function(data) {
						layer.msg('展开状态：' + data.show);
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