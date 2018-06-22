package priv.scj.InteractiveSystem.websocket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

//   /默认为 项目目录
@ServerEndpoint("/chatSocket")
public class ChatSocket {

	// 当前登录的用户名
	private String username;
	// 当前在线的所有用户的session集合
	private static List<Session> sessions = new ArrayList<Session>();
	// 用于将 人名和session对象对应起来
	private static Map<String, Session> map = new HashMap<String, Session>();

	@OnOpen
	public void open(Session session) throws UnsupportedEncodingException {

		// 当前WebSocket中的session对象，不是servlet中的session
		// session.getQueryString是将？后面的所有的字符串全部打印出来

		String queryString = session.getQueryString();

		// session.getQueryString()打印出来的是 usernamer=Hello 这样的形式

		String str = URLDecoder.decode(queryString, "UTF-8");

		username = str.split("=")[1];

		sessions.add(session);
		map.put(username, session);

		String msg = "欢迎 " + this.username + " 进入系统 ！！<br/>";

		// 构建Message对象
		Message message = new Message();
		message.setWelcome(msg);

		// 把Message变成一个JSON格式的字符串，再发送给客户端，客户端再解析出来
		this.broadcast(sessions, message.toJson());
	}

	@OnClose
	public void close(Session session) {

		// 用户退出的时候要从在线用户的session集合中移除用户
		sessions.remove(session);

		String msg = this.username + "退出系统 ！！ <br/>";

		Message message = new Message();
		message.setWelcome(msg);
		message.setLeave(this.username);

		broadcast(sessions, message.toJson());

	}

	private static Gson gson = new Gson();

	@OnMessage
	public void message(Session session, String json) {

		// 利用.fromJson()， 参数是JSON串和转成对象的class 转成Java对象
		ContentVo vo = gson.fromJson(json, ContentVo.class);

		// 广播消息
		if (vo.getType() == 1) {

			Message message = new Message();
			message.setChatType(1);
			message.setMessageFrom(username);
			message.setContent(this.username, "<font color=red>广播 : " + vo.getMsg() + "</font>");

			broadcast(sessions, message.toJson());

		}

		// 私聊消息
		else if (vo.getType() == 2) {
			// 根据username 如果能找到对应的session对象，就可以实现单聊

			String to = vo.getTo();
			Session to_session = map.get(to);

			// 收消息人收到的message
			Message message = new Message();
			message.setChatType(2);
			message.setMessageFrom(username);
			message.setChangeTitle(vo.getChangeTitle());
			message.setContent(this.username, "<font color=red>私聊 : " + vo.getMsg() + "</font>");

			message.setTo(vo.getFrom());

			try {
				to_session.getBasicRemote().sendText(message.toJson());

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 群聊
		else if (vo.getType() == 3) {

			String[] groupChatUserList = vo.getGroupChatUserList();

			List<Session> groupChatSessions = new ArrayList<Session>();

			for (int i = 0; i < groupChatUserList.length; i++) {

				Session groupChatSession = map.get(groupChatUserList[i].trim());

				groupChatSessions.add(groupChatSession);
			}

			Message message = new Message();
			message.setChatType(3);
			message.setGroupChatUserList(groupChatUserList);
			message.setGroupChatName(vo.getGroupChatName());
			message.setContent(username, "<font color='red'>群聊-</font>" + vo.getMsg());
			message.setMessageFrom(username);

			broadcast(groupChatSessions, message.toJson());

		}

		// 退出群聊
		else if (vo.getType() == 4) {

			String[] groupChatUserList = vo.getGroupChatUserList();
			List<Session> groupChatSessions = new ArrayList<Session>();

			// 避免最后一个人退出群聊的时候，系统仍要发送下线消息导致异常出现
			if (groupChatUserList[0] != null && !"".equals(groupChatUserList[0])) {

				for (int i = 0; i < groupChatUserList.length; i++) {

					groupChatSessions.add(map.get((groupChatUserList)[i].trim()));
				}

				String msg = "用户 " + username + " 已经离开群聊 ！！" + "<br/>";

				Message message = new Message();
				message.setGroupChatUserList(groupChatUserList);
				message.setContent(msg);

				broadcast(groupChatSessions, message.toJson());

			}
		}

	}

	// 发送消息给指定的人
	public void broadcast(List<Session> ss, String msg) {

		/*
		 * 迭代器（Iterator） 迭代器是一种设计模式，是一个对象，可以遍历并选择序列中的对象，而开发人员不需要了解该序列的底层结构
		 * Java中Iterator功能比较简单，并只能单向移动
		 * 1、使用方法iterator()要求容器返回一个Iterator。第一次调用Iterator的next()方法时，它返回序列的第一个元素
		 * 2、使用next()获取序列中的下一个元素 3、使用hasNext()检查序列中是否还有元素
		 * 4、使用remove()将迭代器新返回的元素删除
		 */

		for (Iterator iterator = ss.iterator(); iterator.hasNext();) {

			Session session = (Session) iterator.next();
			Session currentUserSession = map.get(username);

			if (session != currentUserSession) {

				try {
					session.getBasicRemote().sendText(msg);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

}
