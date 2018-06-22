package priv.scj.InteractiveSystem.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.dao.OtherDAO;
import priv.scj.InteractiveSystem.service.OtherService;

@Service
public class OtherServiceImpl implements OtherService {

	@Autowired
	private OtherDAO otherDAO;

	public Boolean getOriginalPassWhether(String userAccount, String originalPass) {

		String password = otherDAO.selectOriginalPass(userAccount);

		if (originalPass.equals(password))

			return true;

		else

			return false;
	}

	public String updatePass(String userAccount, String newpass) {

		int result = otherDAO.updatePass(userAccount, newpass);

		if (result == 1)

			return "修改密码成功！";
		else
			return "修改密码失败!";

	}

}
