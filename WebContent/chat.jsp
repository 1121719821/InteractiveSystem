<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE>
<html>
<head>
<link rel="stylesheet" href="other/bootstrap.min.css" />
<script type="text/javascript" src="other/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="other/jquery-form.js"></script>
<link rel="stylesheet" href="other/bootstrap.min.css">
<script src="other/jquery.min.js"></script>
<script src="other/bootstrap.min.js"></script>


<style type="text/css">
#content {
	border: 1px solid gray;
	width: 100%;
	overflow: auto;
}

#personList {
	border: 1px solid gray;
	width: 100%;
	overflow: auto;
}

#onlineUsers {
	border: 1px solid gray;
	width: 100%;
	overflow: auto;
}

#sendMessage:ACTIVE {
	background: yellow;
}
</style>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎使用聊天系统</title>

<script type="text/javascript">
	// 定义一个变量 用于创建一个WebSocket对象
	var websocket;

	window.onload = function() {

		/*
		//如果本地不存在messageQuantity变量或者这个变量不是数值类型，则重新赋值 
		if (undefined == sessionStorage.messageQuantity
				|| isNaN(sessionStorage.messageQuantity)) {

			sessionStorage.messageQuantity = 0;
		}
		 */

		//username相当于session里刚登陆的那个人
		var username = '${ username }';
		//进入聊天页面，就打开socket通道

		//在第一次连接的时候我们可以将用户名 传过去
		var target = "ws://localhost:8080/InteractiveSystem/chatSocket?username="
				+ username;

		if ('WebSocket' in window) {

			websocket = new WebSocket(target);
		} else if ('MozWebSocket' in window) {

			websocket = new MozWebSocket(target);
		} else {

			alert('您的浏览器不支持本系统提供的时事通信平台，请切换浏览器以避免出现错误 ！！');
			return;
		}

		// 判断浏览器是否支持SessionStorage
		if (typeof (Storage) == "undefined") {

			alert('您的浏览器不支持聊天记录的存储,请切换浏览器以免出现错误 ！！');
		}

		websocket.onmessage = function(event) {

			//even.data不能直接用，因为data是JSON格式
			//把JSON赋值给msg，执行完事我们的局部变量多了一个msg
			eval("DATA = " + event.data + ";");

			if (undefined != DATA.welcome) {

				$("#content").append(DATA.welcome);
			}

			if (undefined != DATA.content) {

				// 收到的消息是广播消息
				if (DATA.chatType == 1) {

					var thisChatFrom = '广播窗口';

					if (undefined == sessionStorage[thisChatFrom + 1]
							|| isNaN(sessionStorage[thisChatFrom + 1])) {

						sessionStorage[thisChatFrom + 1] = 0;
					}

					sessionStorage[thisChatFrom + 1
							+ sessionStorage[thisChatFrom + 1]++] = DATA.content;

				}

				//收到的消息的私聊消息
				else if (DATA.chatType == 2) {

					// 发消息的人
					var name = DATA.messageFrom;

					if (undefined == sessionStorage[name + 2]
							|| isNaN(sessionStorage[name + 2])) {

						sessionStorage[name + 2] = 0;
					}
					sessionStorage[name + 2 + sessionStorage[name + 2]++] = DATA.content;
				}

				// 收到的消息的群聊消息
				else if (DATA.chatType == 3) {

					// 获取群名称
					var groupChatName = DATA.groupChatName;

					if (undefined == sessionStorage[groupChatName + 3]
							|| isNaN(sessionStorage[groupChatName + 3])) {

						sessionStorage[groupChatName + 3] = 0;
					}
					sessionStorage[groupChatName + 3
							+ sessionStorage[groupChatName + 3]++] = DATA.content;
				}

				// 聊天框是否是展示的 
				if ($("#chat").is(":visible")) {

					$("#content").append(DATA.content);
					//sessionStorage.setItem(sessionStorage.messageQuantity++, DATA.content);
					$("#unreadMessage").hide();
				}

				else {

					//sessionStorage.setItem(sessionStorage.messageQuantity++, DATA.content);
					$("#unreadMessage").show();
				}

			}

			if (undefined != DATA.changeTitle) {

				$("#chatWindow").html(DATA.changeTitle + "(私聊中...)");
				getChatLog();
				$("#details").html("");
			}

			if (undefined != DATA.groupChatUserList
					&& undefined != DATA.groupChatName) {

				$("#chatWindow").html(DATA.groupChatName + "(群聊中...)");
				getChatLog();
				$("#details")
						.html(
								"<button type='button' onclick='checkGroupChat();'>查看群聊用户</button>"
										+ "<button type='button' onclick='exitGroupChat();'>退出群聊</button>");
			}
		}
	}

	window.onbeforeunload = function() {

		// 获取当前用户名 
		var username = '${ username }';

		//设置用户下线
		jQuery.ajax({

			url : "offline.action",
			type : "post",
			async : false,
			data : {
				username : username
			}
		});

		//关闭WebSocket通道
		websocket.close();
	}

	// 展示用户列表
	function showContacts() {

		//取出在线用户，并根据所有用户列表进行成员展示
		jQuery
				.ajax({

					url : "selectOnlineUsers.action",
					type : 'get',
					async : false,
					error : function() {
						alert('获取失败');
					},
					success : function(data) {

						var allUsers = '${ allUsers }'
						var onlineUsers = data.split(",");

						allUsers = allUsers.split(",");

						$("#personList")
								.html(
										"<input type='text' readonly style='width: 100%;color: red;'"
												+ "onclick='radioBroadcast();' value='广播聊天'></br>");

						for (var i = 0; i < allUsers.length; i++) {

							var result = whetherOnline(onlineUsers, allUsers[i]);

							if (result) {

								$("#personList")
										.append(
												"<input type='text' readonly style='width: 100%; color: red;' id='"
														+ jQuery
																.trim(allUsers[i])
														+ "' onclick='privateChat(this);' value='"
														+ jQuery
																.trim(allUsers[i])
														+ "'><br/>");
							} else {

								$("#personList").append(
										"<label>" + allUsers[i]
												+ "</label><br/>");
							}
						}
					}

				});

	}

	// 判断用户是否在线 arr:所有用户   target:某一用户
	function whetherOnline(arr, target) {

		for (var i = 0; i < arr.length; i++) {

			//console.log(encodeURIComponent(arr[i])); 
			//console.log(encodeURIComponent(jQuery.trim(target)));

			if (jQuery.trim(arr[i]) == jQuery.trim(target)) {
				return true;
			}
		}

		return false;
	}

	//点击发送按钮，发送消息
	function subSend() {

		//取出要发送的消息
		var val = $("#message").val();

		if (val == "") {

			return;
		}

		else {

			var chatTitle = $("#chatWindow").text();

			var chatForm = chatTitle.substring(chatTitle.length - 7,
					chatTitle.length - 5);
			var chatWith = chatTitle.substring(0, chatTitle.length - 8);

			// 当前用户发送的消息
			var messageTitle = "<font style='float: right;'>自己  "
					+ new Date().toLocaleString() + "</font></br>";
			var messageContent = "<font style='float: right; color: blue;'>"
					+ val + "</font></br>";
			var message = messageTitle + messageContent;

			// 定义客户端向后台发送的JSON数组
			var obj = null;

			if (chatForm == "广播") {

				obj = {
					msg : val,
					type : 1
				}

				if (undefined == sessionStorage['广播窗口' + 1]
						|| isNaN(sessionStorage['广播窗口' + 1])) {

					sessionStorage['广播窗口' + 1] = 0;
				}

				sessionStorage['广播窗口' + 1 + sessionStorage['广播窗口' + 1]++] = message;
			}

			//进行私聊
			else if (chatForm == "私聊") {

				var username = "${ username }";

				// 发送消息后，向当前用户添加最近联系人
				jQuery.ajax({

					url : "addRecentContacts.action",
					type : "post",
					data : {
						username : username,
						contacts : chatWith
					}
				});

				obj = {
					to : chatWith,
					from : username,
					msg : val,
					changeTitle : username,
					type : 2
				};

				if (undefined == sessionStorage[chatWith + 2]
						|| isNaN(sessionStorage[chatWith + 2])) {

					sessionStorage[chatWith + 2] = 0;
				}

				sessionStorage[chatWith + 2 + sessionStorage[chatWith + 2]++] = message;
			}

			//群聊
			else if (chatForm == "群聊") {

				//群聊发送消息时，取出所有的群成员，并向这些成员发送消息
				jQuery.ajax({
					url : "selectGroupChatUsers.action",
					type : "post",
					async : false,
					data : {
						groupChatName : chatWith
					},
					error : function() {
						alert('获取失败');
					},
					success : function(data) {

						var groupChatUserList = data.split(",");

						obj = {
							msg : val,
							groupChatUserList : groupChatUserList,
							groupChatName : chatWith,
							type : 3
						}
					}
				});

				if (undefined == sessionStorage[chatWith + 3]
						|| isNaN(sessionStorage[chatWith + 3])) {

					sessionStorage[chatWith + 3] = 0;
				}

				sessionStorage[chatWith + 3 + sessionStorage[chatWith + 3]++] = message;
			}

			$("#content").append(messageTitle + messageContent);

			// 把obj对象装换成字符串
			var str = JSON.stringify(obj);
			//fa给后端的Java
			websocket.send(str);

			$("#message").val("");
		}

	}

	//进入广播聊天中
	function radioBroadcast() {

		$("#chatWindow").html("广播窗口(广播中...)");
		getChatLog();

		if ($("#details").html() != "") {
			$("#details").html("");
		}
	}

	// 选择用户进行私聊 
	function privateChat(e) {

		var username = '${ username }';

		if (username == jQuery.trim(e.value)) {

			alert('不可以同自己进行私聊 ！！');
		}

		else {

			if ($("#details").html() != "") {
				$("#details").html("");
			}

			TO = jQuery.trim(e.value);

			$("#chatWindow").html(e.value + "(私聊中...)");
			getChatLog();

			$("#personList").html(
					"<h5>正在聊天.</h5><font color='green'>" + TO + "</font>");
		}
	}

	// 点击消息按钮，显示登录后聊过天的人
	function recentContacts() {

		var username = '${ username }';
		//查询当前用户最近联系过的人
		jQuery
				.ajax({

					url : "selectRecentContacts.action",

					type : "post",
					async : false,
					data : {
						username : username
					},
					error : function() {
						alert('获取失败');
					},
					success : function(data) {

						if (data.length == 0) {
							$("#personList").html("最近未与人聊天");
						}

						else {

							$("#personList").html("");
							var recentContants = data.split(",");

							jQuery
									.ajax({

										url : "selectOnlineUsers.action",
										type : "post",
										async : false,
										error : function() {
											alert('获取失败');
										},
										success : function(data) {

											var onlineUsers = data.split(",");

											for (var i = 0; i < recentContants.length; i++) {

												var whether = whetherOnline(
														onlineUsers,
														recentContants[i]);
												if (whether) {
													$("#personList")
															.append(
																	"<input type='text' readonly style='width: 100%; color: red;'"
																			+ "onclick='privateChat(this);' value='"
																			+ jQuery
																					.trim(recentContants[i])
																			+ "'/></br>");
												} else {
													$("#personList")
															.append(
																	"<input type='text' readonly style='width: 100%;' value='"
																			+ jQuery
																					.trim(recentContants[i])
																			+ "'></br>");
												}
											}
										}
									});
						}
					}
				});

	}

	// 显示用户所属的群列表
	function userGroupChat() {

		var username = '${ username }';

		//查询当前用户所在的所有群聊
		jQuery.ajax({

			url : "selectUserGroupList.action",
			type : "post",
			data : {
				username : username
			},
			error : function() {
				alert('error');
			},
			success : function(data) {

				if (data.length == 0) {

					$("#personList").html("暂未加入任何群组");
				}

				else {

					var userGroupChatList = data.split(",");

					$("#personList").html("");

					for (var i = 0; i < userGroupChatList.length; i++) {
						$("#personList").append(
								"<input type='text' readonly style='width: 100%; color: red;' "
										+ " onclick='groupChat(this);' value='"
										+ jQuery.trim(userGroupChatList[i])
										+ "'/>");
					}
				}
			}
		});
	}

	//选择群进行群聊
	function groupChat(arg) {

		var groupChatName = arg.value;

		$("#chatWindow").html(groupChatName + "(群聊中...)");
		getChatLog();

		$("#details")
				.html(
						"<button type='button' onclick='checkGroupChat();'>查看群聊用户</button>"
								+ "<button type='button' onclick='exitGroupChat();'>退出群聊</button>");

	}

	//点击  + 号按钮在弹窗中显示所有在线成员
	function findOnlineUsers() {

		//查询出所有的在线用户
		jQuery
				.ajax({

					url : "selectOnlineUsers.action",
					error : function() {
						alert('获取失败');
					},
					success : function(data) {

						var onlineUsers = data.split(",");

						$("#createGroupChat").html("");

						$("#createGroupChat").append(
								"群名称为： <input type='text' id='groupChatName' name='groupChatName'/>"
										+ "</br>请勾选群成员：</br>");

						for (var i = 0; i < onlineUsers.length; i++) {

							var user = jQuery.trim(onlineUsers[i]);

							$("#createGroupChat")
									.append(
											"<input id='groupChatUser' name='groupChatUser' type='checkbox' value='"+user+"'/>"
													+ user + "<br/>");
						}

					}
				});

	}

	//点击创建群聊按钮,创建群聊并向群成员发送通知消息
	function cerateGroupChat() {

		var groupChatName = $("#groupChatName").val();
		var groupChatUser = $("#createGroupChat :checked");

		if (groupChatName == "") {
			alert('群名称不能为空');
			return;
		}

		if (groupChatUser.length < 3) {
			alert('群成员的数量不能小于三人');
			return;
		}

		//判断用户是否在所选的群成员列表中
		var whether = false;
		var username = '${ username }';

		for (var i = 0; i < groupChatUser.length; i++) {
			if (username == groupChatUser[i].value) {
				whether = true;
				break;
			}
		}

		if (whether == false) {
			alert('创建群的时候，本人必须在群中');
		}

		if (whether == true) {

			var groupChatUserList = new Array();

			for (var i = 0; i < groupChatUser.length; i++) {

				groupChatUserList[i] = groupChatUser[i].value;
			}

			// 向后台传递两个参数：群组名和群组成员
			jQuery
					.ajax({

						url : "createGroupChat.action",
						type : "post",
						async : false,
						traditional : true, //ajax向后台传递数组需要添加该属性
						data : {
							groupChatName : groupChatName,
							groupChatUserList : groupChatUserList
						},
						error : function() {
							alert('获取失败');
						},
						success : function(data) {

							if (data != "") {

								alert(data);
							} else {

								$("#chatWindow").html(
										groupChatName + "(群聊中...)");
								getChatLog();

								$("#details")
										.html(
												"<button type='button' onclick='checkGroupChat();'>查看群聊用户</button>"
														+ "<button type='button' onclick='exitGroupChat();'>退出群聊</button>");

								var obj = {

									msg : "欢迎进入群聊~~",
									groupChatUserList : groupChatUserList,
									groupChatName : groupChatName,
									type : 3
								}

								var str = JSON.stringify(obj);
								websocket.send(str);
							}
						}
					});

		}
	}

	//点击查询群成员按钮，显示当前所在群里的所有成员
	function checkGroupChat() {

		var groupChatUserLists = "群成员如下 ：  \r";

		var chatTitle = $("#chatWindow").text();
		var chatWith = chatTitle.substring(0, chatTitle.length - 8);

		//查询出当前群的所有群成员
		jQuery.ajax({

			url : "selectGroupChatUsers.action",
			type : "post",
			data : {
				groupChatName : chatWith
			},
			error : function() {
				alert('获取失败');
			},
			success : function(data) {

				var users = data.split(",");

				for (var i = 0; i < users.length; i++) {

					groupChatUserLists += jQuery.trim(users[i]) + "\r";
				}

				alert(groupChatUserLists);
			}
		});

	}

	//点击退出按钮，退出当前所在的群聊
	function exitGroupChat() {

		if (confirm('确定要退出群聊吗？')) {

			var username = '${ username }';
			var chatTitle = $("#chatWindow").text();
			var groupChatName = chatTitle.substring(0, chatTitle.length - 8);

			// 当前用户退出当前的群聊 
			jQuery.ajax({

				url : "exitGroupChat.action",
				type : "post",
				data : {
					username : username,
					groupChatName : groupChatName
				},
				error : function() {
					alert('获取失败')
				},
				success : function(data) {
					$("#chatWindow").html("广播窗口(广播中...)");
					getChatLog();
					$("#details").html("");
					alert(data);

					jQuery.ajax({

						url : "selectGroupChatUsers.action",
						type : "post",
						async : false,
						data : {
							groupChatName : groupChatName
						},
						error : function() {
							alert('获取失败');
						},
						success : function(data) {

							var groupChatUsers = data.split(",");

							var obj = {
								groupChatUserList : groupChatUsers,
								type : 4
							}

							var str = JSON.stringify(obj);
							websocket.send(str);
						}
					});
				}
			});

		} else {

			alert('您选择了不退出群聊');
		}
	}

	/*
	//点击SwitchingFrame，显示和隐藏Chat聊天界面，如果显示界面则加载sessionStorage的缓存
	function switchFrame() {

		$("#chat").toggle();
		$("#unreadMessage").hide();

		// 当聊天界面显示出来的时候， 展示出聊天记录
		if ($("#chat").is(":visible")) {
			getChatLog();
		}
	}
	 */

	// 获取聊天记录
	function getChatLog() {

		$("#content").html("");

		var chatWindow = $("#chatWindow").html();
		var chatForm = chatWindow.substring(chatWindow.length - 7,
				chatWindow.length - 5); //广播、私聊、群聊
		var chatWith = chatWindow.substring(0, chatWindow.length - 8);

		if (chatForm == '广播') {

			for (var i = 0; i < sessionStorage[chatWith + 1]; i++) {

				$("#content").append(sessionStorage[chatWith + 1 + i]);
			}

		} else if (chatForm == '私聊') {

			for (var i = 0; i < sessionStorage[chatWith + 2]; i++) {

				$("#content").append(sessionStorage[chatWith + 2 + i]);
			}
		} else if (chatForm == '群聊') {

			for (var i = 0; i < sessionStorage[chatWith + 3]; i++) {

				$("#content").append(sessionStorage[chatWith + 3 + i]);
			}
		}
	}
</script>
</head>
<body>

	<div id="chat">

		<div id=""
			style="border: 1px solid black; width: 10%; height: 100%; float: left;">

			<h2>
				<img alt="" src="other/image/chat.jpg" height="70px" width="70px">
			</h2>
			<br>
			<button type="button" class="btn" onclick="showContacts();">联系人</button>
			<br> <br>
			<button type="button" class="btn" onclick="recentContacts();">会话</button>
			<br> <br>
			<button type="button" class="btn" onclick="userGroupChat();">群聊</button>
		</div>

		<div id=""
			style="border: 1px solid black; width: 20%; height: 100%; float: left;">
			<h4>
				<b>人员列表</b>
				<button type="button" class="btn" data-toggle="modal"
					data-target="#myModal" onclick="findOnlineUsers();">+</button>
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog"
					aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal"
									aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">在线成员</h4>
							</div>
							<div class="modal-body" id="onlineUsers">

								<form id="createGroupChat" name="createGroupChat"></form>

							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-default"
									data-dismiss="modal">关闭</button>
								<button type="submit" class="btn btn-primary"
									data-dismiss="modal" onclick="cerateGroupChat();">创建群组</button>
							</div>
						</div>
					</div>
				</div>
			</h4>

			<div id="personList"
				style="border: 1px solid black; width: 100%; height: 86%; float: left;"></div>
		</div>

		<div id=""
			style="border: 1px solid black; width: 70%; height: 100% x; float: left;">

			<div style="height: 7%;">
				<h4 style="text-align: center;">
					<label id="chatWindow">广播窗口(广播中...)</label>
				</h4>
				<div id="details" style="height: 5%;"></div>
			</div>
			<div id="content" style="height: 80%;"></div>

			<div style="border-top: 1px solid black; width: 100%; height: 12%">
				<div class="input-group">
					<input type="text" class="form-control" id="message"
						onkeyup="if(event.keyCode == 13){subSend();}"> <span
						class="input-group-btn">
						<button id="sendMessage" class="btn btn-success" type="button"
							onclick="subSend();">发 送</button>
					</span>
				</div>
			</div>
		</div>

	</div>

</body>
</html>