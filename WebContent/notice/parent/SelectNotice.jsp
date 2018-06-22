<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>幼儿家长查看通知</title>

<link rel="stylesheet" href="/InteractiveSystem/other/bootstrap.min.css">
<script src="/InteractiveSystem/other/jquery.min.js"></script>
<script src="/InteractiveSystem/other/jquery-3.2.1.min.js"></script>
<script src="/InteractiveSystem/other/bootstrap.min.js"></script>
<link rel="stylesheet" href="/InteractiveSystem/other/thems.css">

<link rel="stylesheet" href="/InteractiveSystem/layui/css/layui.css">
<script src="/InteractiveSystem/layui/lay/dest/layui.all.js"></script>


</head>
<body>

	<jsp:include page="/subjective/ParentHead.jsp"></jsp:include>

	<div class="scd">
		<div class="scd_t">
			<span class="now"> <a onclick="admin01();"
				href="/InteractiveSystem/parentSelectMessage.action">查看家长留言</a></span> <span>
				<a href="/InteractiveSystem/parentMessage.action"> 添加消息通知 </a>
			</span>
		</div>
	</div>

	<div class="row">
		<div class="col-md-12"></div>
	</div>



	<c:forEach items="${ parentMessages }" var="parentMessages"
		varStatus="vs">

		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<fieldset class="layui-elem-field">
					<legend>${ parentMessages.messFrom }</legend>
					<div class="layui-field-box">${ parentMessages.content}</div>
				</fieldset>
			</div>
			<div class="col-md-2"></div>
		</div>

		<br>

	</c:forEach>

	<br>
	<br>
	<br>
	<jsp:include page="/subjective/Foot.jsp"></jsp:include>
</body>
</html>