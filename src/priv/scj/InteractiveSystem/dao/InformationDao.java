package priv.scj.InteractiveSystem.dao;

import java.util.List;

import priv.scj.InteractiveSystem.beans.Family;
import priv.scj.InteractiveSystem.beans.Teacher;
import priv.scj.InteractiveSystem.beans.User;

public interface InformationDao {

	/**
	 * 园长查询所有的幼师信息
	 * 
	 * @return
	 */
	public List<Teacher> selectAllTeacher(String classroom);

	/**
	 * 根据幼师名或者幼师账户查询幼师详细信息
	 * 
	 * @param teacher
	 *            幼师名或幼师账户
	 * @param field
	 *            teacherName或teacherAccount
	 * @return
	 */
	public Teacher selectTeacherDetail(String teacher, String field);

	/**
	 * 添加用户信息
	 * 
	 * @param user
	 *            用户信息
	 */
	public Integer insertUser(User user);

	/**
	 * 添加教师信息
	 * 
	 * @param teacherLevel
	 *            幼师级别
	 * @param classroom
	 *            所在班级
	 * @return
	 */
	public Integer insertTacher(String teacherLevel, String classroom);

	/**
	 * 幼师登录系统，修改个人信息
	 * 
	 * @param teacher
	 *            幼师信息
	 */
	public Integer updateTeacher(Teacher teacher);

	/**
	 * 查询所有家庭信息
	 * 
	 * @return
	 */
	public List<Family> selectAllFamily(String classroom);

	/**
	 * 根据幼儿名，查询幼儿信息
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public Family selectChildDetails(String childName);

	/**
	 * 根据幼儿名，查询家庭详情
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public Family selectFamilyDetails(String childName);

	/**
	 * 根据幼师登录账户，查询幼师属于哪个班级
	 * 
	 * @param userAccount
	 *            幼师账户
	 * @return
	 */
	public String selectTeacherClassroom(String userAccount);

	/**
	 * 根据幼儿名字以及账户，为家庭分配登录账户
	 * 
	 * @param userAccount
	 *            用户账户
	 * @return
	 */
	public Integer insertFamily(String childName, String classroom);

	/**
	 * 根据家长登录的账户，查询孩子所在的班级
	 * 
	 * @param userAccount
	 *            家长账户
	 * @return
	 */
	public String selectChildClassroom(String userAccount);

	/**
	 * 家长登录系统，根据登录账户查询这个家庭的幼儿以及父母的全部信息
	 * 
	 * @param userAccount
	 *            家长的登录账户
	 * @return
	 */
	public Family selectFamily(String userAccount);

	/**
	 * 家长登录系统，跟新家庭信息
	 * 
	 * @param family
	 *            家庭信息
	 * @return
	 */
	public Integer updateFamily(Family family);

	/**
	 * 根据教师名，删除指定用户
	 * 
	 * @param teacherName
	 *            幼师名
	 * @return
	 */
	public Integer deleteTeacher(String teacherName);

	/**
	 * 根据幼儿名，删除家庭信息以及家庭账户
	 * 
	 * @param childName
	 *            幼儿名
	 * @return
	 */
	public Integer deleteFamily(String childName);
}
