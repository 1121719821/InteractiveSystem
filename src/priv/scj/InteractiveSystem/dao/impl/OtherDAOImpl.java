package priv.scj.InteractiveSystem.dao.impl;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.dao.OtherDAO;

@Repository
public class OtherDAOImpl implements OtherDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public String selectOriginalPass(String userAccount) {

		String sql = "SELECT user_password FROM users WHERE user_account = ?";

		String password = this.jdbcTemplate.queryForObject(sql, new Object[] { userAccount }, String.class);

		return password;
	}

	public Integer updatePass(String userAccount, String newPass) {

		String sql = "UPDATE users SET user_password = ? WHERE user_account = ?";

		int result = this.jdbcTemplate.update(sql, newPass, userAccount);

		return result;
	}

}
