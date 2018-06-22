package priv.scj.InteractiveSystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import priv.scj.InteractiveSystem.beans.ParentMessage;
import priv.scj.InteractiveSystem.beans.TeacherMessage;
import priv.scj.InteractiveSystem.service.InformationService;
import priv.scj.InteractiveSystem.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService noticeService;

	@Autowired
	private InformationService informationService;

	/**
	 * 幼师查询本班级家长给予的留言
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/teacherSelectMessage")
	public ModelAndView teacherSelectMessage(HttpServletRequest request) {

		// 通过用户账户，查询用户所在的班级
		String userAccount = (String) request.getSession().getAttribute("userAccount");
		String classroom = informationService.getTeacherClassroom(userAccount);

		// 根据用户所在的班级 ，查询幼师收到的留言
		List<ParentMessage> teacherMessages = noticeService.teacherGetMessage(classroom);

		ModelAndView mav = new ModelAndView();
		mav.addObject("teacherMessages", teacherMessages);

		mav.setViewName("notice/teacher/SelectNotice");

		return mav;
	}

	/**
	 * 家长查询本班幼师发布的通知消息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/parentSelectMessage")
	public ModelAndView parentSelectMessage(HttpServletRequest request) {

		// 通过用户账户，查询用户所在的班级
		String userAccount = (String) request.getSession().getAttribute("userAccount");
		String classroom = informationService.getChildClassroom(userAccount);

		// 根据用户所在的班级 ，查询幼师发布的消息
		List<TeacherMessage> parentMessages = noticeService.parentGetMessage(classroom);

		ModelAndView mav = new ModelAndView();
		mav.addObject("parentMessages", parentMessages);

		mav.setViewName("notice/parent/SelectNotice");

		return mav;
	}

	/**
	 * 用于跳转到幼师发布消息界面
	 * 
	 * @return
	 */
	@RequestMapping("/teacherMessage")
	public ModelAndView TeacherMessage() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/teacher/AddNotice");

		return mav;
	}

	/**
	 * 用于跳转到家长发布留言界面
	 * 
	 * @return
	 */
	@RequestMapping("/parentMessage")
	public ModelAndView ParentMessage() {

		ModelAndView mav = new ModelAndView();
		mav.setViewName("notice/parent/AddNotice");

		return mav;
	}

	/**
	 * 幼师向班级发布通知消息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/teacherAddMessage")
	public String teaAddMessage(HttpServletRequest request) {

		// 取出发布消息的幼师，并通过幼师登录的账户查询出幼师所在的班级
		String messFrom = (String) request.getSession().getAttribute("username");
		String userAccount = (String) request.getSession().getAttribute("userAccount");
		String classroom = informationService.getTeacherClassroom(userAccount);

		String content = request.getParameter("content");

		String result = noticeService.teaAddNotice(messFrom, classroom, content);

		return result;
	}

	@ResponseBody
	@RequestMapping("/parentAddMessage")
	public String parAddMessage(HttpServletRequest request) {

		// 取出发布消息的幼师，并通过幼师登录的账户查询出幼师所在的班级
		String messFrom = (String) request.getSession().getAttribute("username");
		String userAccount = (String) request.getSession().getAttribute("userAccount");
		String classroom = informationService.getChildClassroom(userAccount);

		String content = request.getParameter("content");

		String result = noticeService.parAddNotice(messFrom, classroom, content);

		return result;
	}
}
