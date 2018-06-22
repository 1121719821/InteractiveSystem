package priv.scj.InteractiveSystem.service;

import java.util.List;

import priv.scj.InteractiveSystem.beans.Family;
import priv.scj.InteractiveSystem.beans.Teacher;
import priv.scj.InteractiveSystem.beans.User;

public interface InformationService {

	/**
	 * 园长查询所有的幼师信息
	 * 
	 * @return
	 */
	public List<Teacher> getAllTeacher(String classroom);

	/**
	 * 根据幼师名或者幼师账户查询幼师详细信息
	 * 
	 * @param teacher
	 *            幼师名或幼师账户
	 * @param field
	 *            teacherName或teacherAccount
	 * @return
	 */
	public Teacher getTeacherDetails(String teacher, String field);

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 *            用户信息
	 */
	public String addTeacher(User user, String teacherLevel, String classroom);

	/**
	 * 幼师登录系统，更改个人信息
	 * 
	 * @param teacher
	 *            幼师更新信息
	 * @return
	 */
	public String updateTeacher(Teacher teacher);

	/**
	 * 幼师查看家庭信息，获取所有的家庭信息
	 * 
	 * @return
	 */
	public List<Family> getAllFamily(String classroom);

	/**
	 * 根据幼儿名，查询幼儿详细信息
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public Family getChildDetails(String childName);

	/**
	 * 根据幼儿名， 查询家庭详细信息
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public Family getFamilyDetails(String childName);

	/**
	 * 根据幼儿名，添加家庭信息账户
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public String addFamily(User user, String childName, String classroom);

	/**
	 * 根据幼师账户，查询幼师所在班级
	 * 
	 * @param userAccount
	 *            幼师账户
	 * @return
	 */
	public String getTeacherClassroom(String userAccount);

	/**
	 * 根据家长账户，查询幼儿所在班级
	 * 
	 * @param userAccount
	 *            家长账户
	 * @return
	 */
	public String getChildClassroom(String userAccount);

	/**
	 * 家长登录系统，根据登录账户，查询幼儿已经家长的信息
	 * 
	 * @param userAccount
	 *            家长登录的账户
	 * @return
	 */
	public Family getFamily(String userAccount);

	/**
	 * 家长进入系统 ，修改家庭信息
	 * 
	 * @param family
	 *            家庭信息
	 * @return
	 */
	public String updateFamily(Family family);

	/**
	 * 根据幼师名，删除幼师信息以及幼师用户
	 * 
	 * @param teacherName
	 *            幼师名
	 * @return
	 */
	public String deleteTeacher(String teacherName);

	/**
	 * 根据幼儿名， 删除家庭信息以及家庭账户
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public String deleteFamily(String childName);
}
