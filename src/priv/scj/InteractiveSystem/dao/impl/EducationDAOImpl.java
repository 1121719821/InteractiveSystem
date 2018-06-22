package priv.scj.InteractiveSystem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.beans.EduInter;
import priv.scj.InteractiveSystem.dao.EducationDAO;

@Repository
public class EducationDAOImpl implements EducationDAO {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Integer prinAddEdu(String title, String content) {

		String sql = "INSERT INTO edu_inter (edu_id, belong, title, content) VALUES (edu_id_seq.nextval, 'teacher', ?, ?)";

		int result = this.jdbcTemplate.update(sql, title, content);

		return result;
	}

	public Integer teaAddEdu(String title, String content) {

		String sql = "INSERT INTO edu_inter (edu_id, belong, title, content) VALUES (edu_id_seq.nextval, 'parent', ?, ?)";

		int result = this.jdbcTemplate.update(sql, title, content);

		return result;
	}

	public List<EduInter> selectTeaEdu() {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT belong, title, content FROM");
		sql.append(" (SELECT * FROM edu_inter WHERE belong = 'teacher' ORDER BY edu_id desc)");
		sql.append(" WHERE ROWNUM <= 16");

		class EduInterMapper implements RowMapper<EduInter> {

			public EduInter mapRow(ResultSet rs, int rowNum) throws SQLException {

				EduInter eduInter = new EduInter();

				eduInter.setBelong(rs.getString(1));
				eduInter.setTitle(rs.getString(2));
				eduInter.setContent(rs.getString(3));

				return eduInter;
			}

		}

		List<EduInter> list = this.jdbcTemplate.query(sql.toString(), new EduInterMapper());

		return list;
	}

	public List<EduInter> selectParEdu() {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT belong, title, content FROM");
		sql.append(" (SELECT * FROM edu_inter WHERE belong = 'parent' ORDER BY edu_id desc)");
		sql.append(" WHERE ROWNUM <= 16");

		class EduInterMapper implements RowMapper<EduInter> {

			public EduInter mapRow(ResultSet rs, int rowNum) throws SQLException {

				EduInter eduInter = new EduInter();

				eduInter.setBelong(rs.getString(1));
				eduInter.setTitle(rs.getString(2));
				eduInter.setContent(rs.getString(3));

				return eduInter;
			}

		}

		List<EduInter> list = this.jdbcTemplate.query(sql.toString(), new EduInterMapper());

		return list;
	}

}
