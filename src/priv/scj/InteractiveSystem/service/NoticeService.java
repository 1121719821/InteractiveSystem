package priv.scj.InteractiveSystem.service;

import java.util.List;

import priv.scj.InteractiveSystem.beans.ParentMessage;
import priv.scj.InteractiveSystem.beans.TeacherMessage;

public interface NoticeService {

	/**
	 * 幼师查询家长的留言
	 * 
	 * @param classroom
	 *            所在班级
	 * @return
	 */
	public List<ParentMessage> teacherGetMessage(String classroom);

	/**
	 * 家长查询幼师发布的消息
	 * 
	 * @param classroom
	 *            所在班级
	 * @return
	 */
	public List<TeacherMessage> parentGetMessage(String classroom);

	/**
	 * 根据发布消息的幼师、幼师所在班级、发布的消息内容
	 * 
	 * @param messFrom
	 *            幼师
	 * @param classroom
	 *            所在班级
	 * @param content
	 *            消息内容
	 * @return
	 */
	public String teaAddNotice(String messFrom, String classroom, String content);

	/**
	 * 根据留言消息的家长、家长所在的班级，发布的留言消息
	 * 
	 * @param messFrom
	 *            家长
	 * @param classroom
	 *            所在班级
	 * @param content
	 *            留言内容
	 * @return
	 */
	public String parAddNotice(String messFrom, String classroom, String content);
}
