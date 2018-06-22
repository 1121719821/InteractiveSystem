package priv.scj.InteractiveSystem.dao;

import java.util.List;

import priv.scj.InteractiveSystem.beans.EduInter;

public interface EducationDAO {

	/**
	 * 园长登录系统，添加幼师教育案例
	 * 
	 * @param title
	 *            案例标题
	 * @param content
	 *            案例内容
	 * @return
	 */
	public Integer prinAddEdu(String title, String content);

	/**
	 * 幼师登录系统，添加家长教育案例
	 * 
	 * @param title
	 *            案例标题
	 * @param content
	 *            案例内容
	 * @return
	 */
	public Integer teaAddEdu(String title, String content);

	/**
	 * 幼师登录系统，查看教育案例
	 * 
	 * @return
	 */
	public List<EduInter> selectTeaEdu();

	/**
	 * 家长登录系统，查看教育案例
	 * 
	 * @return
	 */
	public List<EduInter> selectParEdu();

}
