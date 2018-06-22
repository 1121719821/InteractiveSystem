package priv.scj.InteractiveSystem.dao;

import priv.scj.InteractiveSystem.beans.User;

public interface LoginDao {

	/**
	 * 查询当前登录的账户在数据库中是否存在
	 * 
	 * @param useraccount
	 *            用户名
	 * @return
	 */
	public Integer selectWhetherExist(String useraccount);

	/**
	 * 根据用户账户，查询用户
	 * 
	 * @param useraccount
	 *            用户账户
	 * @return
	 */
	public User selectUser(String useraccount);

	/**
	 * 根据用户登录的账户，查询用户名
	 * 
	 * @param useraccount
	 *            用户账户
	 * @return
	 */
	public String selectUserName(String useraccount);

}
