package priv.scj.InteractiveSystem.service;

import java.util.List;

import priv.scj.InteractiveSystem.beans.GroupList;
import priv.scj.InteractiveSystem.beans.User;

public interface ChatService {

	/**
	 * 设置用户在线
	 * 
	 * @param username
	 */
	void setUserOnline(String username);

	/**
	 * 设置用户下线
	 * 
	 * @param username
	 */
	void setUserOffline(String username);

	/**
	 * 获取所有用户
	 * 
	 * @return
	 */
	String getAllUsers();

	/**
	 * 查询所有在线用户
	 * 
	 * @return
	 */
	String getOnlineUsers();

	/**
	 * 创建群聊天
	 * 
	 * @param groupChatName
	 *            群名称
	 * @param groupChatUsers
	 *            群成员
	 */
	String createGroupChat(String groupChatName, List<User> groupChatUsers);

	/**
	 * 查询某个群里面的群成员
	 * 
	 * @param groupChatName
	 *            群名称
	 * @return
	 */
	String getGroupChatUserList(String groupChatName);

	/**
	 * 获取当前用户的所属群聊列表
	 * 
	 * @param username
	 *            当前用户名
	 * @return
	 */
	String getUserGroupChatList(String username);

	/**
	 * 给当前用户添加最近联系人
	 * 
	 * @param username
	 *            当前用户名
	 * @param contacts
	 *            最近联系人
	 */
	void addRecentContacts(String username, String contacts);

	/**
	 * 获取当前用户最近联系人
	 * 
	 * @param username
	 *            当前用户名
	 * @return
	 */
	String getRecentContacts(String username);

	/**
	 * 当前用户退出当前群聊
	 * 
	 * @param username
	 *            当前用户名
	 * @param groupChatName
	 *            当前群名称
	 */
	void exitGroupChat(String username, String groupChatName);

	/**
	 * 取出数据库中所有群的群名称
	 * 
	 * @return
	 */
	List<GroupList> getAllGroupChatName();
}
