package priv.scj.InteractiveSystem.service;

import priv.scj.InteractiveSystem.beans.User;

public interface LoginService {

	/**
	 * �жϵ�¼���˻������ݿ����Ƿ����
	 * 
	 * @param useraccount
	 *            �û���¼�˻�
	 * @return
	 */
	public boolean getWhetherExist(String useraccount);

	/**
	 * ��ȡ���ݿ��е�User
	 * 
	 * @param user
	 * @return
	 */
	public String getUser(User user);

	/**
	 * 通过登录用户的账户，查询当前用户的用户名
	 * 
	 * @param userAccount
	 *            用户的登录账户
	 * @return
	 */
	public String getUserName(String userAccount);
}
