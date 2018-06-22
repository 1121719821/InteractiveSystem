package priv.scj.InteractiveSystem.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.beans.ParentMessage;
import priv.scj.InteractiveSystem.beans.TeacherMessage;
import priv.scj.InteractiveSystem.dao.NoticeDAO;
import priv.scj.InteractiveSystem.service.NoticeService;

@Service
public class NoticeServiceImpl implements NoticeService {

	@Autowired
	private NoticeDAO noticeDAO;

	public List<ParentMessage> teacherGetMessage(String classroom) {

		List<ParentMessage> list = noticeDAO.teacherSelectMessage(classroom);

		return list;
	}

	public List<TeacherMessage> parentGetMessage(String classroom) {

		List<TeacherMessage> list = noticeDAO.parentSelectMessage(classroom);

		return list;
	}

	public String teaAddNotice(String messFrom, String classroom, String content) {

		Integer num = noticeDAO.teaAddNotice(messFrom, classroom, content);

		String result = "发布消息失败！";

		if (num == 1) {

			result = "";
		}

		return result;
	}

	public String parAddNotice(String messFrom, String classroom, String content) {

		int num = noticeDAO.parAddNotice(messFrom, classroom, content);

		String result = "留言失败！";

		if (num == 1) {

			result = "";
		}

		return result;
	}

}
