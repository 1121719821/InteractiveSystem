package priv.scj.InteractiveSystem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.beans.ParentMessage;
import priv.scj.InteractiveSystem.beans.TeacherMessage;
import priv.scj.InteractiveSystem.dao.NoticeDAO;

@Repository
public class NoticeDAOImpl implements NoticeDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<ParentMessage> teacherSelectMessage(String classroom) {

		String sql = "SELECT mess_from, content FROM parent_message WHERE classroom = ? ORDER BY parmessage_id DESC";

		class ParentMessageMapper implements RowMapper<ParentMessage> {

			public ParentMessage mapRow(ResultSet rs, int rowNum) throws SQLException {

				ParentMessage parentMessage = new ParentMessage();
				parentMessage.setMessFrom(rs.getString(1));
				parentMessage.setContent(rs.getString(2));

				return parentMessage;
			}
		}

		List<ParentMessage> list = this.jdbcTemplate.query(sql, new ParentMessageMapper(), classroom);

		return list;
	}

	public List<TeacherMessage> parentSelectMessage(String classroom) {

		String sql = "SELECT mess_from, content FROM teacher_message WHERE classroom = ? ORDER BY teamessage_id DESC";

		class TeacherMessageMapper implements RowMapper<TeacherMessage> {

			public TeacherMessage mapRow(ResultSet rs, int rowNum) throws SQLException {

				TeacherMessage teacherMessage = new TeacherMessage();

				teacherMessage.setMessFrom(rs.getString(1));
				teacherMessage.setContent(rs.getString(2));

				return teacherMessage;
			}
		}

		List<TeacherMessage> list = this.jdbcTemplate.query(sql, new TeacherMessageMapper(), classroom);

		return list;
	}

	public Integer teaAddNotice(String messFrom, String classroom, String content) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO teacher_message(teamessage_id, mess_from, classroom, content)");
		sql.append(" VALUES (teamessage_id_seq.nextval, ?, ?, ?)");

		int result = this.jdbcTemplate.update(sql.toString(), messFrom, classroom, content);

		return result;
	}

	public Integer parAddNotice(String messFrom, String classroom, String content) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO parent_message(parmessage_id, mess_from, classroom, content)");
		sql.append(" VALUES (parmessage_id_seq.nextval, ?, ?, ?)");

		int result = this.jdbcTemplate.update(sql.toString(), messFrom, classroom, content);

		return result;
	}

}
