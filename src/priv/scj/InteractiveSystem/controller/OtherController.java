package priv.scj.InteractiveSystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import priv.scj.InteractiveSystem.service.LoginService;
import priv.scj.InteractiveSystem.service.OtherService;

@Controller
public class OtherController {

	@Autowired
	private LoginService loginService;

	@Autowired
	private OtherService otherService;

	@RequestMapping("/otherFunction")
	public ModelAndView otherFunction(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String userAccount = (String) request.getSession().getAttribute("userAccount");

		String userName = loginService.getUserName(userAccount);

		mav.addObject("userAccount", userAccount);
		mav.addObject("userName", userName);

		int role = (Integer) request.getSession().getAttribute("role");

		if (role == 2) {

			mav.setViewName("otherFunction/teacher/ModifyPassword");
		} else if (role == 3) {

			mav.setViewName("otherFunction/parent/ModifyPassword");
		}

		return mav;
	}

	/**
	 * 用户修改个人密码
	 * 
	 * @param newpass
	 *            新密码
	 * @param request
	 * @return
	 */
	@RequestMapping("/modifyPassword")
	public ModelAndView modifyPassword(String newpass, HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		// 用户扥估账户
		String userAccount = (String) request.getSession().getAttribute("userAccount");

		// 对密码进行修改
		otherService.updatePass(userAccount, newpass);

		mav.setViewName("Login");

		return mav;
	}

	/**
	 * 校长进入修改密码界面
	 * 
	 * @return
	 */
	@RequestMapping("/principalModifyPass")
	public ModelAndView principalModifyPass() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("otherFunction/principal/AdminPass");

		return mav;
	}

}
