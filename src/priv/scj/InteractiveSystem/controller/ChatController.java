package priv.scj.InteractiveSystem.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.service.ChatService;

@Controller
public class ChatController {

	@Autowired
	private ChatService chatService;

	/**
	 * 输入用户名，点击登录进入系统
	 * 
	 * @param username
	 *            用户名
	 * @return
	 
	@RequestMapping("/login")
	public ModelAndView login(String username, HttpServletRequest request) {

		chatService.setUserOnline(username);

		ModelAndView mav = new ModelAndView();

		// 获取系统所有用户
		String allUsers = chatService.getAllUsers();

		// mav.addObject("allUsers", allUsers);
		// mav.addObject("username", username);
		request.getSession().setAttribute("allUsers", allUsers);
		request.getSession().setAttribute("username", username);

		mav.setViewName("MainPage");

		return mav;
	}
	
	*/

	@RequestMapping("/offline")
	public void offline(HttpServletRequest request) {

		chatService.setUserOffline(request.getParameter("username"));
	}

	/**
	 * 查询所有在线的用户
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectOnlineUsers")
	public String selectOnlineUsers() {
		
		String onlineUsers = chatService.getOnlineUsers();

		return onlineUsers;
	}

	/**
	 * 通过全台ajax传过来的群名和群成员，创建新的群聊
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/createGroupChat")
	public String createGroupChat(HttpServletRequest request) {

		// 取出创建群的群成员
		String[] groupChatUsers = request.getParameterValues("groupChatUserList");

		List<User> groupChatUserList = new ArrayList<User>();

		for (int i = 0; i < groupChatUsers.length; i++) {

			User user = new User();
			user.setUserName(groupChatUsers[i]);

			groupChatUserList.add(user);
		}

		String result = chatService.createGroupChat(request.getParameter("groupChatName"), groupChatUserList);

		return result;
	}

	/**
	 * 取出所属当前群的所有群成员
	 * 
	 * @param request
	 * @param groupChatName
	 *            群名称
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectGroupChatUsers")
	public String selectGroupChatUsers(HttpServletRequest request) {

		String groupChatUsers = chatService.getGroupChatUserList(request.getParameter("groupChatName"));

		return groupChatUsers;
	}

	/**
	 * 取出当前用户所属的群聊列表
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectUserGroupList")
	public String selectUserGroupChatList(HttpServletRequest request) {

		String userGroupList = chatService.getUserGroupChatList(request.getParameter("username"));

		return userGroupList;
	}

	/**
	 * 当前用户退出当前群聊天
	 * 
	 * @param request
	 */
	@ResponseBody
	@RequestMapping("/exitGroupChat")
	public String exitGroupChat(HttpServletRequest request) {

		chatService.exitGroupChat(request.getParameter("username"), request.getParameter("groupChatName"));

		return "退出群聊 :" + request.getParameter("groupChatName") + " 成功 !!";
	}

	/**
	 * 为当前用户添加最近联系人
	 * 
	 * @param request
	 */
	@RequestMapping("/addRecentContacts")
	public void addRecentContacts(HttpServletRequest request) {

		chatService.addRecentContacts(request.getParameter("username"), request.getParameter("contacts"));

		chatService.addRecentContacts(request.getParameter("contacts"), request.getParameter("username"));
	}

	/**
	 * 查询当前用户最近联系人
	 * 
	 * @param username
	 *            当前用户名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/selectRecentContacts")
	public String selectRecentContacts(HttpServletRequest request) {

		String result = chatService.getRecentContacts(request.getParameter("username"));

		return result;
	}

}
