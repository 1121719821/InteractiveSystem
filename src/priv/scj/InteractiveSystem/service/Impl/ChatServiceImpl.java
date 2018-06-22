package priv.scj.InteractiveSystem.service.Impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.beans.GroupList;
import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.ChatDAO;
import priv.scj.InteractiveSystem.service.ChatService;

@Service
public class ChatServiceImpl implements ChatService {

	@Autowired
	private ChatDAO chatDAO;

	public void setUserOnline(String username) {

		chatDAO.setUserOnline(username);
	}

	public void setUserOffline(String username) {

		chatDAO.setUserOffline(username);
	}

	public String getOnlineUsers() {

		List<User> userList = chatDAO.selectOnlineUsers();

		String onlineUsers = changeUserList(userList);
		return onlineUsers;

	}

	public String getAllUsers() {

		List<User> userList = chatDAO.selectAllUsers();

		String allusers = changeUserList(userList);
		return allusers;

	}

	public String createGroupChat(String groupChatName, List<User> groupChatUsers) {

		List<GroupList> allName = getAllGroupChatName();

		String result = "";
		boolean whether = true;

		for (int i = 0; i < allName.size(); i++) {

			if (groupChatName.equals(allName.get(i).getGroupName())) {
				whether = false;
				break;
			}
		}

		if (whether) {

			chatDAO.createGroupChat(groupChatName);
			chatDAO.addGroupChatDetails(groupChatUsers);
		} else {

			result = "群名被占用，请重新输入群名称！";
		}

		return result;
	}

	public String getGroupChatUserList(String groupChatName) {

		List<User> userList = chatDAO.groupChatUserList(groupChatName);
		String groupChatUserList = changeUserList(userList);

		return groupChatUserList;
	}

	public String getUserGroupChatList(String username) {

		List<GroupList> groupChatList = chatDAO.selectGroupList(username);

		String userGroupChatList = changeGroupList(groupChatList);
		return userGroupChatList;
	}

	public void exitGroupChat(String username, String groupChatName) {

		chatDAO.exitGroupChat(username, groupChatName);

		// int num = chatDAO.selectNumOfGroupChat(groupChatName);
		//
		// if (num < 3) {
		// chatDAO.deleteGroupChat(groupChatName);
		// }

	}

	public void addRecentContacts(String username, String contacts) {

		chatDAO.addRecentContacts(username, contacts);
	}

	public String getRecentContacts(String username) {

		List<User> contactsList = chatDAO.selectRecentContacts(username);

		String recentContacts = changeUserList(contactsList);

		return recentContacts;
	}

	public List<GroupList> getAllGroupChatName() {

		List<GroupList> allGroupChatNameList = chatDAO.selectAllGroupChatName();

		return allGroupChatNameList;
	}

	/**
	 * 将一个集合转换成一个字符串
	 * 
	 * @param lists
	 *            User类型的集合名
	 * @return
	 */
	public String changeUserList(List<User> lists) {

		String[] strings = new String[lists.size()];

		for (int i = 0; i < lists.size(); i++) {

			strings[i] = lists.get(i).getUserName();
		}

		String str = Arrays.toString(strings);

		String result = str.substring(1, str.length() - 1);

		return result;
	}

	/**
	 * 讲一个集合转换成字符串
	 * 
	 * @param lists
	 *            GroupList类型集合
	 * @return
	 */
	public String changeGroupList(List<GroupList> lists) {

		String[] strings = new String[lists.size()];

		for (int i = 0; i < lists.size(); i++) {

			strings[i] = lists.get(i).getGroupName();
		}

		String str = Arrays.toString(strings);

		String result = str.substring(1, str.length() - 1);

		return result;
	}

}
