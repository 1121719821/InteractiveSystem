package priv.scj.InteractiveSystem.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import priv.scj.InteractiveSystem.beans.Family;
import priv.scj.InteractiveSystem.beans.Teacher;
import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.service.InformationService;

@Controller
public class InformationController {

	@Autowired
	private InformationService informationService;

	/**
	 * 根据幼师名，查询幼师详情
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/teacherDetails")
	public String teacherDetails(HttpServletRequest request) {

		String teacherName = request.getParameter("teacherName");

		Teacher teacher = informationService.getTeacherDetails(teacherName, "teacherName");

		Gson gson = new Gson();
		String json = gson.toJson(teacher);

		return json;

	}

	/**
	 * 根据幼师名、账户、身份、幼师级别、所在班级 创建新的用户
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addTeacher")
	public String addTeacher(HttpServletRequest request) {

		User user = new User();
		user.setUserName(request.getParameter("teacherName"));
		user.setUserAccount(request.getParameter("useraccount"));
		user.setUserRole(2);

		String teacherLevel = request.getParameter("teacherLevel");
		String classroom = request.getParameter("classroom");

		String result = informationService.addTeacher(user, teacherLevel, classroom);

		return result;
	}

	/**
	 * 根据信息，添加家庭信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addFamily")
	public String addFamily(HttpServletRequest request) {

		User user = new User();
		user.setUserName(request.getParameter("childName"));
		user.setUserAccount(request.getParameter("userAccount"));
		user.setUserRole(3);

		// 取出幼师的登录账户
		String teacherAccount = (String) request.getSession().getAttribute("userAccount");
		// 根据幼师的登录账户，获取幼师所在的班级
		String classroom = informationService.getTeacherClassroom(teacherAccount);
		// 在为家庭分配账户的时候，需要添加幼儿所在的班级
		String result = informationService.addFamily(user, request.getParameter("childName"), classroom);

		return result;
	}

	/**
	 * 根据幼师的登录账户查询幼师的信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectTeacher")
	public ModelAndView selectTeacher(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String teacherAccount = (String) request.getSession().getAttribute("userAccount");

		mav.addObject("teacher", informationService.getTeacherDetails(teacherAccount, "teacherAccount"));

		mav.setViewName("information/teacher/AdminInfo");

		return mav;
	}

	/**
	 * 根据修改的字段，更新幼师信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTeacher")
	public String updateTeacher(HttpServletRequest request) {

		Teacher teacher = new Teacher();

		teacher.setTeacherName(request.getParameter("teacherName"));
		teacher.setSex(request.getParameter("sex"));
		teacher.setAge(request.getParameter("age"));
		teacher.setTelephone(request.getParameter("telephone"));
		teacher.setGraduation(request.getParameter("graduation"));
		teacher.setQq(request.getParameter("qq"));
		teacher.setWeixin(request.getParameter("weixin"));
		teacher.setExperience(request.getParameter("experience"));
		teacher.setSpecialty(request.getParameter("specialty"));

		String result = informationService.updateTeacher(teacher);

		return result;
	}

	/**
	 * 幼师查询所有的家庭信息
	 * 
	 * @return
	 */
	@RequestMapping("/selectAllFamily")
	public ModelAndView selectAllFamily(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		// 取出幼师的登录账户
		String teacherAccount = (String) request.getSession().getAttribute("userAccount");
		// 根据幼师的登录账户，获取幼师所在的班级
		String classroom = informationService.getTeacherClassroom(teacherAccount);

		mav.addObject("allFamily", informationService.getAllFamily(classroom));
		mav.setViewName("information/teacher/SelectInfo");

		return mav;
	}

	/**
	 * 根据幼儿名查询幼儿详情
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/childDetails")
	public String childDetails(HttpServletRequest request) {

		Family family = informationService.getChildDetails(request.getParameter("childName"));

		Gson gson = new Gson();
		String json = gson.toJson(family);

		return json;
	}

	/**
	 * 根据幼儿名查询家庭信息详情
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/familyDetails")
	public String familyDetails(HttpServletRequest request) {

		Family family = informationService.getFamilyDetails(request.getParameter("childName"));

		Gson gson = new Gson();
		String json = gson.toJson(family);

		return json;
	}

	/**
	 * 根据家长登录的账户，查询该账户的幼儿以及家长信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/selectInfo")
	public ModelAndView selectInfo(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		String userAccount = (String) request.getSession().getAttribute("userAccount");

		Family family = informationService.getFamily(userAccount);

		mav.addObject("family", family);
		mav.setViewName("information/parent/AdminInfo");

		return mav;
	}

	/**
	 * 校长查询所有的幼师信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/principalSelectAllTeacher")
	public ModelAndView selectAllTeacher(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		mav.addObject("allTeacher", informationService.getAllTeacher(""));
		mav.setViewName("information/principal/SelectInfo");

		return mav;
	}

	/**
	 * 家长登录到系统，查询自己孩子所在的班级的所有教师信息
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/parentSelectTeacher")
	public ModelAndView parentSelectTeacher(HttpServletRequest request) {

		ModelAndView mav = new ModelAndView();

		// 取出幼师的登录账户
		String parentAccount = (String) request.getSession().getAttribute("userAccount");
		// 根据幼师的登录账户，获取幼师所在的班级
		String classroom = informationService.getChildClassroom(parentAccount);

		mav.addObject("allTeacher", informationService.getAllTeacher(classroom));
		mav.setViewName("information/parent/SelectInfo");

		return mav;
	}

	/**
	 * 家长登录系统，跟新家庭的信息
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateFamily")
	public String updateFamily(HttpServletRequest request) {

		Family family = new Family();

		family.setChildName(request.getParameter("childName"));

		family.setChildBirthday(request.getParameter("childBirthday"));
		family.setChildCard(request.getParameter("childCard"));
		family.setFamilySituation(request.getParameter("familySituation"));
		family.setPhysicalCondition(request.getParameter("physicalCondition"));
		family.setChildRemarks(request.getParameter("childRemarks"));

		family.setFatherName(request.getParameter("fatherName"));
		family.setFatherAge(request.getParameter("fatherAge"));
		family.setFatherTel(request.getParameter("fatherTel"));
		family.setFatherWork(request.getParameter("fatherWork"));

		family.setMotherName(request.getParameter("motherName"));
		family.setMotherAge(request.getParameter("motherAge"));
		family.setMotherTel(request.getParameter("motherTel"));
		family.setMotherWork(request.getParameter("motherWork"));

		family.setAddress(request.getParameter("address"));

		String result = informationService.updateFamily(family);
		
		System.out.println(request.getParameter("fatherAge"));

		return result;
	}

	/**
	 * 园长登录系统，删除幼师信息以及账户
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTeacher")
	public String deleteTeacher(HttpServletRequest request) {

		String teacherName = request.getParameter("teacherName");

		String result = informationService.deleteTeacher(teacherName);

		return result;
	}

	/**
	 * 幼师登录系统，删除家庭信息以及家庭账户
	 * 
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteFamily")
	public String deleteFamily(HttpServletRequest request) {

		String childName = request.getParameter("childName");

		String result = informationService.deleteFamily(childName);

		return result;
	}
}
