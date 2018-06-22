package priv.scj.InteractiveSystem.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.beans.GroupList;
import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.ChatDAO;

@Repository
public class ChatDAOImpl implements ChatDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public void setUserOnline(String username) {

		String sql = "update users set user_state = 1 where user_name = ?";

		this.jdbcTemplate.update(sql, username);

	}

	public void setUserOffline(String username) {

		String sql = "update users set user_state = 0 where user_name = ?";

		jdbcTemplate.update(sql, username);
	}

	public List<User> selectAllUsers() {
		String sql = "SELECT user_name FROM users ORDER BY user_state DESC ,user_id";

		class UserMapper implements RowMapper<User> {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUserName(rs.getString("user_name"));

				return user;
			}
		}

		List<User> userList = this.jdbcTemplate.query(sql, new UserMapper());

		return userList;
	}

	public List<User> selectOnlineUsers() {

		String sql = "SELECT user_name FROM users WHERE user_state = 1 ORDER BY user_state DESC ,user_id";
		class UserMapper implements RowMapper<User> {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUserName(rs.getString("user_name"));

				return user;
			}
		}

		List<User> userList = this.jdbcTemplate.query(sql, new UserMapper());

		return userList;
	}

	public int createGroupChat(String groupChatName) {

		String sql = "INSERT INTO group_list (group_id, group_name) VALUES (group_id_seq.NEXTVAL, ?)";

		int result = 0;

		result += jdbcTemplate.update(sql, groupChatName);

		return result;
	}

	public int[] addGroupChatDetails(final List<User> groupChatUsers) {

		StringBuffer sql = new StringBuffer("INSERT INTO group_users (groupDetails_id, group_id, group_user)");
		sql.append(" VALUES (groupDetails_id_seq.NEXTVAL, group_id_seq.CURRVAL, ?)");

		int[] insertCount = jdbcTemplate.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {

				ps.setString(1, groupChatUsers.get(i).getUserName());
			}

			public int getBatchSize() {

				return groupChatUsers.size();
			}
		});

		return insertCount;
	}

	public List<User> groupChatUserList(String groupChatName) {

		StringBuffer sql = new StringBuffer("SELECT group_user FROM group_users");
		sql.append(" WHERE group_id = (SELECT group_id FROM group_list WHERE group_name = ?)");

		class UserMapper implements RowMapper<User> {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUserName(rs.getString("group_user"));

				return user;
			}
		}

		List<User> groupChatUserList = this.jdbcTemplate.query(sql.toString(), new Object[] { groupChatName },
				new UserMapper());

		return groupChatUserList;
	}

	public List<GroupList> selectGroupList(String username) {

		StringBuffer sql = new StringBuffer("SELECT group_name FROM group_list WHERE");
		sql.append(" group_id IN (SELECT group_id FROM group_users");
		sql.append(" WHERE group_user = ?)");

		class GroupListMapper implements RowMapper<GroupList> {
			public GroupList mapRow(ResultSet rs, int rowNum) throws SQLException {

				GroupList groupList = new GroupList();
				groupList.setGroupName(rs.getString("group_name"));
				return groupList;
			}
		}

		List<GroupList> grouLists = this.jdbcTemplate.query(sql.toString(), new Object[] { username },
				new GroupListMapper());

		return grouLists;
	}

	public void addRecentContacts(String username, String contacts) {

		// StringBuffer sql = new StringBuffer("INSERT INTO");
		// sql.append(" recent_contacts(recentContactsDetails_id, user_name,
		// contacts)");
		// sql.append(" VALUES(recentContactsDetails_id_seq.NEXTVAL, ?, ?)");

		StringBuffer sql = new StringBuffer("MERGE INTO recent_contacts aa");
		sql.append(" USING (SELECT COUNT(*) num FROM recent_contacts WHERE user_name = ? AND contacts = ?) bb ");
		sql.append(" ON (bb.num > 0)");
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("INSERT VALUES (recentContactsDetails_id_seq.NEXTVAL, ?, ?)");

		this.jdbcTemplate.update(sql.toString(), username, contacts, username, contacts);
	}

	public List<User> selectRecentContacts(String username) {

		StringBuffer sql = new StringBuffer("SELECT contacts FROM recent_contacts");
		sql.append(" WHERE user_name = ? ORDER BY recentContactsDetails_id");

		class UserMapper implements RowMapper<User> {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setUserName(rs.getString("contacts"));

				return user;
			}
		}

		List<User> recentGroupChat = this.jdbcTemplate.query(sql.toString(), new Object[] { username },
				new UserMapper());

		return recentGroupChat;
	}

	public void exitGroupChat(String username, String groupChatName) {

		StringBuffer sql = new StringBuffer("DELETE FROM group_users");
		sql.append(" WHERE group_user = ?");
		sql.append(" AND group_id =");
		sql.append(" (SELECT group_id FROM group_list WHERE group_name = ?)");

		this.jdbcTemplate.update(sql.toString(), username, groupChatName);

		int numOfGroupChat = selectNumOfGroupChat(groupChatName);

		if (numOfGroupChat < 1) {

			deleteGroupChat(groupChatName);
		}
	}

	public List<GroupList> selectAllGroupChatName() {

		String sql = "SELECT group_name FROM group_list";

		class GroupListMapper implements RowMapper<GroupList> {

			public GroupList mapRow(ResultSet rs, int rowNum) throws SQLException {

				GroupList groupList = new GroupList();
				groupList.setGroupName(rs.getString("group_name"));

				return groupList;
			}
		}

		List<GroupList> groupLists = this.jdbcTemplate.query(sql, new GroupListMapper());

		return groupLists;
	}

	public Integer selectNumOfGroupChat(String groupChatName) {

		StringBuffer sql = new StringBuffer("SELECT COUNT(*) FROM group_users WHERE group_id =");
		sql.append(" (SELECT group_id FROM group_list WHERE group_name = ?)");

		Integer num = this.jdbcTemplate.queryForObject(sql.toString(), new Object[] { groupChatName }, Integer.class);

		return num;
	}

	public void deleteGroupChat(String groupChatName) {

		// 根据群名称删除群成员中的所有当前群名的群聊天
		StringBuffer sql01 = new StringBuffer("DELETE FROM group_users WHERE group_id = ");
		sql01.append("(SELECT group_id FROM group_list WHERE group_name = ?)");

		// 根据群名称删除群列表的中的当前群聊
		String sql02 = "DELETE FROM group_list WHERE group_name = ?";

		this.jdbcTemplate.update(sql02, groupChatName);
		this.jdbcTemplate.update(sql01.toString(), groupChatName);
	}
}
