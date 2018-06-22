package priv.scj.InteractiveSystem.service;

import java.util.List;

import priv.scj.InteractiveSystem.beans.EduInter;

public interface EducationService {

	/**
	 * 园长登录系统，为幼师添加教育界面
	 * 
	 * @param title
	 *            教育案例标题
	 * @param content
	 *            案例内容
	 * @return
	 */
	public String prinAddEdu(String title, String content);

	/**
	 * 幼师登录系统，为家长添加驾驭界面
	 * 
	 * @param title
	 *            案例标题
	 * @param content
	 *            案例内容
	 * @return
	 */
	public String teaAddEdu(String title, String content);

	/**
	 * 幼师登录系统，查看教育案例
	 * 
	 * @return
	 */
	public List<EduInter> getTeaEdu();

	/**
	 * 家长登录系统，查看教育案例
	 * 
	 * @return
	 */
	public List<EduInter> getParEdu();
}
