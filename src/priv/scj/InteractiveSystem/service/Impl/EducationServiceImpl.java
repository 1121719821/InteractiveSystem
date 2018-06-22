package priv.scj.InteractiveSystem.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.beans.EduInter;
import priv.scj.InteractiveSystem.dao.EducationDAO;
import priv.scj.InteractiveSystem.service.EducationService;

@Service
public class EducationServiceImpl implements EducationService {

	@Autowired
	private EducationDAO educationDAO;

	public String prinAddEdu(String title, String content) {

		int result = educationDAO.prinAddEdu(title, content);

		if (result != 1) {

			return "添加案例失败！";
		}

		return "添加案例成功！";
	}

	public String teaAddEdu(String title, String content) {

		int result = educationDAO.teaAddEdu(title, content);

		if (result != 1) {

			return "添加案例失败！";
		}

		return "添加案例成功！";
	}

	public List<EduInter> getTeaEdu() {

		return educationDAO.selectTeaEdu();
	}

	public List<EduInter> getParEdu() {

		return educationDAO.selectParEdu();
	}

}
