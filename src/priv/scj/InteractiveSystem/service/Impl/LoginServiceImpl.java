package priv.scj.InteractiveSystem.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.LoginDao;
import priv.scj.InteractiveSystem.service.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private LoginDao loginDao;

	public boolean getWhetherExist(String useraccount) {

		int result = loginDao.selectWhetherExist(useraccount);

		if (result == 1)
			return true;
		else
			return false;

	}

	public String getUser(User user) {

		boolean whetherExist = getWhetherExist(user.getUserAccount());

		if (whetherExist) {

			User dbUser = loginDao.selectUser(user.getUserAccount());

			if (user.getUserPassword().equals(dbUser.getUserPassword())) {

				if (user.getUserRole().equals(dbUser.getUserRole())) {

					return "";
				} else {

					return "用户角色不正确，请选择正确的角色！";
				}
			} else {

				return "用户密码错误，请重新输入！";
			}
		} else {

			return "用户账户不存在，请确认账户重新登录！";
		}

	}

	public String getUserName(String userAccount) {

		String userName = loginDao.selectUserName(userAccount);

		return userName;
	}

}