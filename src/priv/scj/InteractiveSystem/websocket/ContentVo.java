package priv.scj.InteractiveSystem.websocket;

public class ContentVo {

	// 私聊时要把消息发送给某人
	private String to;
	// 私聊时，发送消息的人
	private String from;
	// 发送的消息
	private String msg;
	// 发送消息的类型
	private Integer type;
	// 创建群聊的群用户列表
	private String[] groupChatUserList;
	// 群聊的群名称
	private String groupChatName;
	// 当用户收到聊天消息，聊天框的标题发生改变，变为与某用户正在聊天中
	private String changeTitle;

	public ContentVo() {
		super();
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String[] getGroupChatUserList() {
		return groupChatUserList;
	}

	public void setGroupChatUserList(String[] groupChatUserList) {
		this.groupChatUserList = groupChatUserList;
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

}
