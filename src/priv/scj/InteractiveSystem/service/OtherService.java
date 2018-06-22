package priv.scj.InteractiveSystem.service;

public interface OtherService {

	/**
	 * 根据用户的登录账户，查询用户输入的原始密码是否在正确
	 * 
	 * @param userAccount
	 *            用户账户
	 * @return
	 */
	public Boolean getOriginalPassWhether(String userAccount, String originalPass);

	/**
	 * 用户修改个人密码
	 * 
	 * @param userAccount
	 *            用户登录账户
	 * @param newpass
	 *            用户新密码
	 * @return
	 */
	public String updatePass(String userAccount, String newpass);

}
