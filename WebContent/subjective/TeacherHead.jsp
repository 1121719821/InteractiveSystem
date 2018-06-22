<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />

<link rel="stylesheet" type="text/css"
	href="/InteractiveSystem/other/reset.css" />
<script type="text/javascript"
	src="/InteractiveSystem/other/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="/InteractiveSystem/other/js_z.js"></script>
<link rel="stylesheet" type="text/css"
	href="/InteractiveSystem/other/thems.css">
<link rel="stylesheet" type="text/css"
	href="/InteractiveSystem/other/style.css" />
</head>

<style>
.head .logo img {
	width: 500px;
	height: 80px;
	vertical-align: top;
}
</style>

<!--头部-->
<div class="t_bg">
	<div class="top top_a login-header">
		<a href="/InteractiveSystem/selectAllFamily.action">首页</a>| <img
			alt="" src="/InteractiveSystem/other/image/image04.png">联系我们&nbsp;&nbsp;150
		4060 3013
	</div>
</div>
<div class="header">
	<div class="head head_a clearfix">
		<div class="logo">
			<a href=""><img src="/InteractiveSystem/other/image/image02.png"
				alt="image" /></a>
		</div>
		<ul class="nav clearfix">
			<li>
				<div class="li_m">
					<a href="/InteractiveSystem/selectAllFamily.action"> <span>信息管理</span>
						InformationManager
					</a>
				</div>
				<div class="er er_1">
					<div class="tabBox_t">
						<div class="tabBox">
							<ul class="tabNav">
								<li><a href="/InteractiveSystem/selectAllFamily.action">查看家庭信息</a></li>
								<li><a href="/InteractiveSystem/selectTeacher.action">修改个人信息
								</a></li>
							</ul>
							<div class="tabCont">
								<ul class="new_l clearfix">
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image03.jpg" alt="" /></a>
										<div class="title">
											<b>幼儿园</b>
										</div>
										<div class="des">6年来幼儿园面向城镇，辐射农村，精心办圆，务实创新，成功创响品牌，成为全县乡镇规模最大的幼儿园</div></li>
									<li>
										<div>

											<b>我们的宗旨 ： </b>动手启智，健康快乐<br> <b>我们的园风 ： </b>团结奉献，求实创新<br>
											<b>我们的教务风 ： </b>挚爱善道，无私奉献<br> <b>我们的教育目标 ： </b>让孩子开心,培养健康有爱心、文明有孝心、大方有诚信、勇敢有自信的二十一世纪小主人<br>
											<br> 倡导快乐学习教学方式, 除开设语言、计算、音乐、美术、科学、舞蹈、常识、手工等主题课程外，
											还开设听读游戏识字课程、经典古诗词朗诵课程、英语课程、艺术课程、运动课程,为幼儿未来发展奠定良好基础。
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="li_m">
					<a href="/InteractiveSystem/teaSelectEdu.action"> <span>教育管理</span>
						EducationalInterface
					</a>
				</div>
				<div class="er er_1">
					<div class="tabBox_t">
						<div class="tabBox">
							<ul class="tabNav">
								<li><a href="/InteractiveSystem/teaSelectEdu.action">查看教育案例</a></li>
								<li><a href="/InteractiveSystem/teaEnterEdu.action">添加教育案例
								</a></li>
							</ul>
							<div class="tabCont">
								<ul class="new_l clearfix">
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image05.jpg" alt="" /></a>

										<div class="des">
											<b>每个儿童</b>都应该有一个尽可能好的人生开端;<br> <b>每个儿童</b>都应该接受良好的基础教育;<br>
											<b>每个儿童</b>都应有机会充分发掘自身潜能，成长为一名有益于社会的人;

										</div></li>
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image06.jpg" alt="" /></a>
										<div class="des_a">
											无论是教育界或是家长现在都已经认可幼儿教育的重要性;<br> <br>
											教育幼儿是人类社会生活的重要组成部分;<br>幼儿教育的质量关系着人类社会的繁衍和延续;<br>

										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="li_m">
					<a href="/InteractiveSystem/teacherSelectMessage.action"> <span>通知管理</span>
						NotificationManager
					</a>
				</div>
				<div class="er er_1">
					<div class="tabBox_t">
						<div class="tabBox">
							<ul class="tabNav">
								<li><a
									href="/InteractiveSystem/teacherSelectMessage.action">查看家长留言</a></li>
								<li><a href="/InteractiveSystem/teacherMessage.action">发布班级消息
								</a></li>
							</ul>
							<div class="tabCont">
								<ul class="new_l clearfix">
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image07.jpg" alt="" /></a>

										<div class="des_a">

											幼儿园领导班子富有朝气、师资队伍素质较高、保教质量堪称一流、享有很好的社会声誉。幼儿园为了加快教师队伍的建设，提出力争近年内教师95%达本科学历，实现教师队伍本科化。</div></li>
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image08.jpg" alt="" /></a>
										<div class="des_a">
											园内环境净化、美化、儿童化浑然一体，全园有配套齐全，符合幼儿健康发展要求的活动室、午睡室、漱洗室；有培养幼儿兴趣与能力的舞蹈室、美术创意室；有功能多样的大型玩具，充满冒险的攀爬墙...先进、环保、卫生的设施设备、丰富适宜的活动空间、平等关爱的人文气氛，处处体现着以幼儿发展为本的教育理念。
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</li>
			<li>
				<div class="li_m">
					<a href="/InteractiveSystem/otherFunction.action"> <span>其他</span>
						Other
					</a>
				</div>
				<div class="er er_5">
					<div class="tabBox_t">
						<div class="tabBox">
							<ul class="tabNav">
								<li><a href="/InteractiveSystem/otherFunction.action">修改个人密码</a></li>
								
							</ul>
							<div class="tabCont">
								<ul class="new_l clearfix">
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image09.jpg" alt="" /></a>
										<div class="des">
											<b>爱：</b>爱事业，爱孩子，爱岗位。<br> <b>为：</b>为师德之本。<br> <b>勤：</b>勤事业，勤钻研，勤思考，勤为成功之路。
										</div></li>
									<li><a href=""><img
											src="/InteractiveSystem/other/image/image10.jpg" alt="" /></a>
										<div class="des_a">
											<b>爱心承诺：</b> 教育不能选择适合教育的孩子，只能选择适合孩子的教育，受教育的机会是平等的。<br>
											以关怀，接纳，尊重的态度与幼儿交往，耐心、倾听、努力理解幼儿的想法与感受，支持鼓励他们大胆探索与表达。
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>
