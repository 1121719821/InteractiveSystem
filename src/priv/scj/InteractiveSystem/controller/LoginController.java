package priv.scj.InteractiveSystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.service.ChatService;
import priv.scj.InteractiveSystem.service.InformationService;
import priv.scj.InteractiveSystem.service.LoginService;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private InformationService informationService;

	@Autowired
	private ChatService chatService;

	@RequestMapping("/login")
	public ModelAndView login(String useraccount, String password, Integer role, HttpServletRequest request) {

		User user = new User();
		user.setUserAccount(useraccount);
		user.setUserPassword(password);
		user.setUserRole(role);

		// 获取系统所有用户
		String allUsers = chatService.getAllUsers();
		request.getSession().setAttribute("allUsers", allUsers);

		// 用户登录系统，将用户的账户存入session
		request.getSession().setAttribute("userAccount", useraccount);
		request.getSession().setAttribute("userPassword", password);

		ModelAndView mav = new ModelAndView();
		String result = loginService.getUser(user);

		if ("".equals(result)) {

			// 获取登录用户的用户名，用来在Chat中使用
			String username = loginService.getUserName(useraccount);

			// 设置用户在线标志
			chatService.setUserOnline(username);
			request.getSession().setAttribute("username", username);

			request.getSession().setAttribute("role", role);

			// 园长用登录
			if (role == 1) {

				// mav.addObject("allTeacher",
				// informationService.getAllTeacher(""));
				request.getSession().setAttribute("allTeacher", informationService.getAllTeacher(""));

				mav.setViewName("mainpage/PrincipalMainPage");
			}
			// 幼师用户登录
			if (role == 2) {

				// 根据幼师的登录账户，获取幼师所在的班级
				String classroom = informationService.getTeacherClassroom(useraccount);
				// 幼师所在班级的所有家长信息
				// mav.addObject("allFamily",
				// informationService.getAllFamily(classroom));
				request.getSession().setAttribute("allFamily", informationService.getAllFamily(classroom));

				mav.setViewName("mainpage/TeacherMainPage");
			}
			// 家长用户登录
			if (role == 3) {
				// 根据家长登录账户，查询幼儿所在班级
				String classroom = informationService.getChildClassroom(useraccount);
				// mav.addObject("allTeacher",
				// informationService.getAllTeacher(classroom));
				request.getSession().setAttribute("allTeacher", informationService.getAllTeacher(classroom));

				mav.setViewName("mainpage/ParentMainPage");
			}
		}

		else {
			mav.addObject("warning", result);
			mav.setViewName("Login");
		}

		return mav;

	}

}