package priv.scj.InteractiveSystem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.LoginDao;

@Repository
public class LoginDaoImpl implements LoginDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer selectWhetherExist(String useraccount) {

		String sql = "SELECT COUNT(*) FROM users WHERE user_account = ?";

		int result = this.jdbcTemplate.queryForObject(sql, Integer.class, useraccount);

		return result;
	}

	public User selectUser(String useraccount) {

		String sql = "SELECT user_name, user_password, user_role FROM users WHERE user_account = ?";

		class UserMapper implements RowMapper<User> {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUserName(rs.getString("user_name"));
				user.setUserPassword(rs.getString("user_password"));
				user.setUserRole(rs.getInt("user_role"));

				return user;
			}

		}

		return this.jdbcTemplate.queryForObject(sql, new UserMapper(), useraccount);
	}

	public String selectUserName(String useraccount) {

		String sql = "SELECT user_name FROM users WHERE user_account = ?";

		String result = this.jdbcTemplate.queryForObject(sql, new Object[] { useraccount }, String.class);

		return result;
	}

}
