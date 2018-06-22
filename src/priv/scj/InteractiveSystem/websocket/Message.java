package priv.scj.InteractiveSystem.websocket;

import java.util.Date;

import com.google.gson.Gson;

public class Message {
	// 上下线提醒
	private String welcome;
	// 发送的消息
	private String content;
	// 下线用户
	private String leave;
	// 群聊所有成员列表
	private String[] groupChatUserList;
	// 当前群聊的群名称
	private String groupChatName;
	// 当用户收到聊天消息，聊天框的标题发生改变，变为与某用户正在聊天中
	private String changeTitle;
	// 用户收到私聊的时候，在发送消息的时候直接发送给to
	private String to;

	// 前端的onload事件中，需要将消息的发送者取出来
	private String messageFrom;
	// 前端的onload事件中，需要将消息的类型取出来，判断如何存储聊天记录
	private Integer chatType;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public void setContent(String name, String msg) {
		this.content = "<b>" + name + "</b> <small>"
				+ new Date().toLocaleString() + "</small>:<br/>   " + msg
				+ "<br/>";
	}

	public Message() {
		super();
	}

	public String getWelcome() {
		return welcome;
	}

	public void setWelcome(String welcome) {
		this.welcome = welcome;
	}

	public String getLeave() {
		return leave;
	}

	public void setLeave(String leave) {
		this.leave = leave;
	}

	public String[] getGroupChatUserList() {
		return groupChatUserList;
	}

	public void setGroupChatUserList(String groupChatUserlist[]) {
		this.groupChatUserList = groupChatUserlist;
	}

	public String getGroupChatName() {
		return groupChatName;
	}

	public void setGroupChatName(String groupChatName) {
		this.groupChatName = groupChatName;
	}

	public String getChangeTitle() {
		return changeTitle;
	}

	public void setChangeTitle(String changeTitle) {
		this.changeTitle = changeTitle;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getMessageFrom() {
		return messageFrom;
	}

	public void setMessageFrom(String messageFrom) {
		this.messageFrom = messageFrom;
	}

	public Integer getChatType() {
		return chatType;
	}

	public void setChatType(Integer chatType) {
		this.chatType = chatType;
	}

	private static Gson gson = new Gson();

	public String toJson() {
		return gson.toJson(this);
	}

}
