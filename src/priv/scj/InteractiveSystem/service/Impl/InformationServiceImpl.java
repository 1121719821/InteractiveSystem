package priv.scj.InteractiveSystem.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.beans.Family;
import priv.scj.InteractiveSystem.beans.Teacher;
import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.InformationDao;
import priv.scj.InteractiveSystem.service.InformationService;

@Service
public class InformationServiceImpl implements InformationService {

	@Autowired
	private InformationDao informationDao;

	public List<Teacher> getAllTeacher(String classroom) {

		List<Teacher> teachers = informationDao.selectAllTeacher(classroom);

		return teachers;
	}

	public Teacher getTeacherDetails(String teacher, String field) {

		Teacher result = informationDao.selectTeacherDetail(teacher, field);

		return result;
	}

	public String addTeacher(User user, String teacherLevel, String classroom) {

		int result1 = informationDao.insertUser(user);

		String result = "添加用户失败 ！";

		if (result1 == 1) {

			int result2 = informationDao.insertTacher(teacherLevel, classroom);

			if (result2 == 1) {

				result = "";
			}
		}

		return result;
	}

	public String addFamily(User user, String childName, String classroom) {

		int result1 = informationDao.insertUser(user);

		String result = "添加用户失败 ！";

		if (result1 == 1) {

			int result2 = informationDao.insertFamily(childName, classroom);

			if (result2 == 1) {

				result = "";
			}
		}

		return result;
	}

	public String updateTeacher(Teacher teacher) {

		int num = informationDao.updateTeacher(teacher);

		String result = "更新个人信息失败 !";

		if (num == 1) {
			result = "更新个人信息成功 !";
		}

		return result;
	}

	public List<Family> getAllFamily(String classroom) {

		List<Family> families = informationDao.selectAllFamily(classroom);

		return families;
	}

	public Family getChildDetails(String childName) {

		Family family = informationDao.selectChildDetails(childName);

		return family;
	}

	public Family getFamilyDetails(String childName) {

		Family family = informationDao.selectFamilyDetails(childName);

		return family;
	}

	public String getTeacherClassroom(String userAccount) {

		String classroom = informationDao.selectTeacherClassroom(userAccount);

		return classroom;
	}

	public String getChildClassroom(String userAccount) {

		String classroom = informationDao.selectChildClassroom(userAccount);
		return classroom;
	}

	public Family getFamily(String userAccount) {

		Family family = informationDao.selectFamily(userAccount);

		return family;
	}

	public String updateFamily(Family family) {

		int num = informationDao.updateFamily(family);

		String result = "更新信息失败 !";

		if (num == 1)
			result = "更新信息成功 !";

		return result;
	}

	public String deleteTeacher(String teacherName) {

		int num = informationDao.deleteTeacher(teacherName);

		String result = "删除幼师用户失败 !";

		if (num == 2) {
			result = "删除幼师用户成功 !";
		}

		return result;
	}

	public String deleteFamily(String childName) {

		int num = informationDao.deleteFamily(childName);

		String result = "delete teacher error !";

		if (num == 2) {
			result = "delete teacher success !";
		}

		return result;
	}

}
