package priv.scj.InteractiveSystem.dao;

public interface OtherDAO {
	/**
	 * 用户修改个人密码界面，根据用户登录的账户，查询账户的密码
	 * 
	 * @param userAccount
	 *            用户账户
	 * @return
	 */
	public String selectOriginalPass(String userAccount);

	/**
	 * 修改用户密码，根据用户账户，将用户密码更新成新密码
	 * 
	 * @param userAccount
	 *            用户账户
	 * @param newPass
	 *            用户密码
	 * @return
	 */
	public Integer updatePass(String userAccount, String newPass);

}
