package priv.scj.InteractiveSystem.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import priv.scj.InteractiveSystem.beans.EduInter;
import priv.scj.InteractiveSystem.service.EducationService;

@Controller
public class EducationController {

	@Autowired
	private EducationService educationService;

	/**
	 * 园长进入添加教育案例界面
	 * 
	 * @return
	 */
	@RequestMapping("/prinEnterEdu")
	public ModelAndView prinEnterEdu() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("education/principal/AddEdu");

		return mav;
	}

	/**
	 * 幼师进入添加教育案例界面
	 * 
	 * @return
	 */
	@RequestMapping("/teaEnterEdu")
	public ModelAndView teaEnterEdu() {

		ModelAndView mav = new ModelAndView();

		mav.setViewName("education/teacher/AddEdu");

		return mav;
	}

	/**
	 * 园长为幼师添加教育案例
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/prinAddEdu")
	@ResponseBody
	public String prinAddEdu(HttpServletRequest request) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String result = educationService.prinAddEdu(title, content);

		return result;
	}

	/**
	 * 幼师为家长添加教育案例
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/teaAddEdu")
	@ResponseBody
	public String teaAddEdu(HttpServletRequest request) {

		String title = request.getParameter("title");
		String content = request.getParameter("content");

		String result = educationService.teaAddEdu(title, content);

		return result;
	}

	/**
	 * 幼师查看教育案例
	 * 
	 * @return
	 */
	@RequestMapping("/teaSelectEdu")
	public ModelAndView teaSelectEdu() {

		ModelAndView mav = new ModelAndView();

		List<EduInter> teaEduList = educationService.getTeaEdu();

		mav.addObject("teaEduList", teaEduList);
		
		mav.setViewName("education/teacher/SelectEdu");

		return mav;
	}

	/**
	 * 家长查看教育案例
	 * 
	 * @return
	 */
	@RequestMapping("/parSelectEdu")
	public ModelAndView parSelectEdu() {

		ModelAndView mav = new ModelAndView();

		List<EduInter> parEduList = educationService.getParEdu();

		mav.addObject("parEduList", parEduList);

		mav.setViewName("education/parent/SelectEdu");

		return mav;
	}

}
