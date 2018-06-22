package priv.scj.InteractiveSystem.dao;

import java.util.List;

import priv.scj.InteractiveSystem.beans.ParentMessage;
import priv.scj.InteractiveSystem.beans.TeacherMessage;

public interface NoticeDAO {

	/**
	 * 幼师查询家长的留言
	 * 
	 * @param classroom
	 *            所在班级
	 * @return
	 */
	public List<ParentMessage> teacherSelectMessage(String classroom);

	/**
	 * 家长查询幼师发布的消息
	 * 
	 * @param classroom
	 *            班级
	 * @return
	 */
	public List<TeacherMessage> parentSelectMessage(String classroom);

	/**
	 * 根据幼师填写的内容，向家长发布通知
	 * 
	 * @param content
	 *            通知内容
	 * @return
	 */
	public Integer teaAddNotice(String messFrom, String classroom, String content);

	/**
	 * 根据家长填写的内容，给教师留言
	 * 
	 * @param messFrom
	 *            留言者
	 * @param classroom
	 *            所在班级
	 * @param content
	 *            留言内容
	 * @return
	 */
	public Integer parAddNotice(String messFrom, String classroom, String content);
}
