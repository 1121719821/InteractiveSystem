package priv.scj.InteractiveSystem.dao;

import java.util.List;

import priv.scj.InteractiveSystem.beans.GroupList;
import priv.scj.InteractiveSystem.beans.User;

public interface ChatDAO {

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
	 * 查询所有用户
	 * 
	 * @return
	 */
	List<User> selectAllUsers();

	/**
	 * 查询所有在线用户
	 * 
	 * @return
	 */
	List<User> selectOnlineUsers();

	/**
	 * 创建群聊天
	 * 
	 * @param groupChatName
	 *            群名称
	 * @param groupChatUsers
	 *            群成员
	 * @return
	 */
	int createGroupChat(String groupChatName);

	/**
	 * 创建群聊天的同时，需要向群详情表中插入群成员列表
	 * 
	 * @param groupChatUsers
	 *            群成员集合
	 * @return
	 */
	int[] addGroupChatDetails(List<User> groupChatUsers);

	/**
	 * 查询群聊里面的群成员
	 * 
	 * @return
	 */
	List<User> groupChatUserList(String gourpChatName);

	/**
	 * 查询当前用户所属的所有群名称
	 * 
	 * @param username
	 *            当前用户名
	 * @return
	 */
	List<GroupList> selectGroupList(String username);

	/**
	 * 添加用户的最近联系人
	 * 
	 * @param username
	 *            当前用户名
	 * @param contacts
	 *            联系人名
	 */
	void addRecentContacts(String username, String contacts);

	/**
	 * 查询当前用户最近联系人
	 * 
	 * @param username
	 *            当前用户
	 * @return
	 */
	List<User> selectRecentContacts(String username);

	/**
	 * 设置用户退出当前所在群
	 * 
	 * @param username
	 *            用户名
	 * @param groupChatName
	 *            群名称
	 */
	void exitGroupChat(String username, String groupChatName);

	/**
	 * 查询所有群聊的名字
	 * 
	 * @return
	 */
	List<GroupList> selectAllGroupChatName();

	/**
	 * 查询某群聊里面的群成员人数
	 * 
	 * @param groupChatName
	 * @return
	 */
	Integer selectNumOfGroupChat(String groupChatName);

	/**
	 * 如果当前群聊的成员人数少于三人，不构成群聊的人数，则删除群
	 * 
	 * @param groupChatName
	 */
	void deleteGroupChat(String groupChatName);
}
