package priv.scj.InteractiveSystem.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import priv.scj.InteractiveSystem.beans.Family;
import priv.scj.InteractiveSystem.beans.Teacher;
import priv.scj.InteractiveSystem.beans.User;
import priv.scj.InteractiveSystem.dao.InformationDao;

@Repository
public class InformationDaoImpl implements InformationDao {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource dataSource) {

		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public List<Teacher> selectAllTeacher(String classroom) {

		StringBuffer sql = new StringBuffer("SELECT user_name, teacher_id, teacher_level, classroom, sex, age,");
		sql.append(" telephone, qq, weixin, graduation, experience, specialty");
		sql.append(" FROM users");
		sql.append(" JOIN teacher USING (user_id)");

		if (!"".equals(classroom)) {
			sql.append(" WHERE classroom = ?");
		}

		sql.append(" ORDER BY user_id");

		class TeacherMapper implements RowMapper<Teacher> {

			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

				Teacher teacher = new Teacher();

				teacher.setTeacherName(rs.getString(1));
				teacher.setTeacherId(rs.getInt(2));
				teacher.setTeacherLevel(rs.getString(3));
				teacher.setClassroom(rs.getString(4));
				teacher.setSex(rs.getString(5));
				teacher.setAge(rs.getString(6));
				teacher.setTelephone(rs.getString(7));
				teacher.setQq(rs.getString(8));
				teacher.setWeixin(rs.getString(9));
				teacher.setGraduation(rs.getString(10));
				teacher.setExperience(rs.getString(11));
				teacher.setSpecialty(rs.getString(12));

				return teacher;
			}

		}

		List<Teacher> teachers = null;

		if (!"".equals(classroom)) {

			teachers = this.jdbcTemplate.query(sql.toString(), new TeacherMapper(), classroom);
		} else {

			teachers = this.jdbcTemplate.query(sql.toString(), new TeacherMapper());
		}

		return teachers;
	}

	public Teacher selectTeacherDetail(String teacher, String field) {

		StringBuffer sql = new StringBuffer();

		if ("teacherName".equals(field)) {

			sql.append("SELECT user_name, teacher_id, teacher_level, classroom, sex, age,");
			sql.append(" telephone, qq, weixin, graduation, experience, specialty");
			sql.append(" FROM users");
			sql.append(" JOIN teacher USING (user_id)");
			sql.append(" WHERE user_name = ?");
		}

		else if ("teacherAccount".equals(field)) {

			sql.append("SELECT user_name, teacher_id, teacher_level, classroom, sex, age,");
			sql.append(" telephone, qq, weixin, graduation, experience, specialty");
			sql.append(" FROM users");
			sql.append(" JOIN teacher USING (user_id)");
			sql.append(" WHERE user_account = ?");
		}

		class TeacherMapper implements RowMapper<Teacher> {

			public Teacher mapRow(ResultSet rs, int rowNum) throws SQLException {

				Teacher teacher = new Teacher();

				teacher.setTeacherName(rs.getString(1));
				teacher.setTeacherId(rs.getInt(2));
				teacher.setTeacherLevel(rs.getString(3));
				teacher.setClassroom(rs.getString(4));
				teacher.setSex(rs.getString(5));
				teacher.setAge(rs.getString(6));
				teacher.setTelephone(rs.getString(7));
				teacher.setQq(rs.getString(8));
				teacher.setWeixin(rs.getString(9));
				teacher.setGraduation(rs.getString(10));
				teacher.setExperience(rs.getString(11));
				teacher.setSpecialty(rs.getString(12));

				return teacher;
			}
		}

		Teacher result = this.jdbcTemplate.queryForObject(sql.toString(), new TeacherMapper(), teacher);
		return result;
	}

	public Integer insertUser(User user) {

		// StringBuffer sql = new StringBuffer("INSERT INTO users (user_id,
		// user_name, user_account,");
		// sql.append(" user_password, user_role, user_state)");
		// sql.append(" VALUES (users_id_seq.nextval, ?, ?, '111', ?, 0 )");

		StringBuffer sql = new StringBuffer("MERGE INTO users aa");
		sql.append(" USING (SELECT COUNT(*) num FROM users WHERE user_account = ? ) bb");
		sql.append(" ON (bb.num > 0)");
		sql.append(" WHEN NOT MATCHED THEN");
		sql.append(" INSERT VALUES (users_id_seq.nextval, ?, ?, '111', ?, 0 )");

		int result = this.jdbcTemplate.update(sql.toString(), user.getUserAccount(), user.getUserName(),
				user.getUserAccount(), user.getUserRole());
		return result;
	}

	public Integer insertTacher(String teacherLevel, String classroom) {

		StringBuffer sql = new StringBuffer("INSERT INTO teacher (teacher_id, user_id, teacher_level, classroom)");
		sql.append("VALUES (teacher_id_seq.nextval, users_id_seq.currval, ?, ?)");

		int result = this.jdbcTemplate.update(sql.toString(), teacherLevel, classroom);
		return result;
	}

	public String selectTeacherClassroom(String userAccount) {

		String sql = "SELECT classroom FROM teacher JOIN users USING (user_id) WHERE user_account = ?";

		String classroom = this.jdbcTemplate.queryForObject(sql, new Object[] { userAccount }, String.class);

		return classroom;
	}

	public Integer insertFamily(String childName, String classroom) {

		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO family(family_id, user_id, child_name, classroom)");
		sql.append(" VALUES (family_id_seq.nextval, users_id_seq.currval, ?, ?)");

		int result = this.jdbcTemplate.update(sql.toString(), childName, classroom);

		return result;
	}

	public Integer updateTeacher(Teacher teacher) {

		StringBuffer sql = new StringBuffer();

		sql.append("UPDATE teacher SET SEX = ?, AGE = ?,");
		sql.append(" TELEPHONE = ?, QQ = ?, WEIXIN = ?, GRADUATION = ?,");
		sql.append(" EXPERIENCE = ?,  SPECIALTY = ?");
		sql.append(" WHERE user_id = ( ");
		sql.append(" SELECT user_id FROM users WHERE user_name = ? )");

		int result = this.jdbcTemplate.update(sql.toString(), teacher.getSex(), teacher.getAge(),
				teacher.getTelephone(), teacher.getQq(), teacher.getWeixin(), teacher.getGraduation(),
				teacher.getExperience(), teacher.getSpecialty(), teacher.getTeacherName());

		return result;
	}

	public List<Family> selectAllFamily(String classroom) {

		StringBuffer sql = new StringBuffer("");
		sql.append("SELECT family_id, child_name, child_birthday, child_card, family_situation,");
		sql.append(" physical_condition, child_remarks, father_name, father_age, father_tel,");
		sql.append(" father_work, mother_name,  mother_age,  mother_tel, mother_work, address");
		sql.append(" FROM family");
		sql.append(" WHERE classroom = ?");

		class FamilyMapper implements RowMapper<Family> {

			public Family mapRow(ResultSet rs, int rowNum) throws SQLException {

				Family family = new Family();
				family.setFamilyId(rs.getInt(1));
				family.setChildName(rs.getString(2));
				family.setChildBirthday(rs.getString(3));
				family.setChildCard(rs.getString(4));
				family.setFamilySituation(rs.getString(5));
				family.setPhysicalCondition(rs.getString(6));
				family.setChildRemarks(rs.getString(7));
				family.setFatherName(rs.getString(8));
				family.setFatherAge(rs.getString(9));
				family.setFatherTel(rs.getString(10));
				family.setFatherWork(rs.getString(11));
				family.setMotherName(rs.getString(12));
				family.setMotherAge(rs.getString(13));
				family.setMotherTel(rs.getString(14));
				family.setMotherWork(rs.getString(15));
				family.setAddress(rs.getString(16));

				return family;
			}

		}

		List<Family> families = this.jdbcTemplate.query(sql.toString(), new FamilyMapper(), classroom);

		return families;
	}

	public Family selectChildDetails(String childName) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT child_name, child_birthday, child_card, physical_condition, child_remarks");
		sql.append(" FROM family");
		sql.append(" WHERE child_name = ?");

		class FamilyMapper implements RowMapper<Family> {

			public Family mapRow(ResultSet rs, int arg1) throws SQLException {

				Family family = new Family();
				family.setChildName(rs.getString(1));
				family.setChildBirthday(rs.getString(2));
				family.setChildCard(rs.getString(3));
				family.setPhysicalCondition(rs.getString(4));
				family.setChildRemarks(rs.getString(5));

				return family;
			}
		}

		Family family = this.jdbcTemplate.queryForObject(sql.toString(), new FamilyMapper(), childName);

		return family;
	}

	public Family selectFamilyDetails(String childName) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT family_id, father_name, father_age, father_tel,");
		sql.append(" father_work, mother_name, mother_age, mother_tel, ");
		sql.append(" mother_work, family_situation, address");
		sql.append(" FROM family");
		sql.append(" WHERE child_name = ?");

		class FamilyMapper implements RowMapper<Family> {

			public Family mapRow(ResultSet rs, int arg1) throws SQLException {

				Family family = new Family();
				family.setFamilyId(rs.getInt(1));
				family.setFatherName(rs.getString(2));
				family.setFatherAge(rs.getString(3));
				family.setFatherTel(rs.getString(4));
				family.setFatherWork(rs.getString(5));
				family.setMotherName(rs.getString(6));
				family.setMotherAge(rs.getString(7));
				family.setMotherTel(rs.getString(8));
				family.setMotherWork(rs.getString(9));
				family.setFamilySituation(rs.getString(10));
				family.setAddress(rs.getString(11));

				return family;
			}
		}

		Family family = this.jdbcTemplate.queryForObject(sql.toString(), new FamilyMapper(), childName);

		return family;
	}

	public String selectChildClassroom(String userAccount) {

		String sql = "SELECT classroom FROM family JOIN users USING (user_id) WHERE user_account = ?";

		// String classroom = this.jdbcTemplate.queryForObject(sql,
		// String.class, userAccount);

		String classroom = this.jdbcTemplate.queryForObject(sql, new Object[] { userAccount }, String.class);

		return classroom;
	}

	public Family selectFamily(String userAccount) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT child_name, classroom, child_birthday, child_card, family_situation,");
		sql.append(" physical_condition, child_remarks, father_name, father_age, father_tel,");
		sql.append(" father_work, mother_name,  mother_age,  mother_tel, mother_work, address");
		sql.append(" FROM family");
		sql.append(" JOIN users USING (user_id)");
		sql.append(" WHERE user_account = ?");

		class FamilyMapper implements RowMapper<Family> {

			public Family mapRow(ResultSet rs, int rowNum) throws SQLException {

				Family family = new Family();
				family.setChildName(rs.getString(1));
				family.setClassroom(rs.getString(2));
				family.setChildBirthday(rs.getString(3));
				family.setChildCard(rs.getString(4));
				family.setFamilySituation(rs.getString(5));
				family.setPhysicalCondition(rs.getString(6));
				family.setChildRemarks(rs.getString(7));
				family.setFatherName(rs.getString(8));
				family.setFatherAge(rs.getString(9));
				family.setFatherTel(rs.getString(10));
				family.setFatherWork(rs.getString(11));
				family.setMotherName(rs.getString(12));
				family.setMotherAge(rs.getString(13));
				family.setMotherTel(rs.getString(14));
				family.setMotherWork(rs.getString(15));
				family.setAddress(rs.getString(16));

				return family;
			}

		}

		Family family = this.jdbcTemplate.queryForObject(sql.toString(), new FamilyMapper(), userAccount);

		return family;
	}

	public Integer updateFamily(Family family) {

		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE family SET child_birthday = ?, child_card = ?, family_situation =?,");
		sql.append(" physical_condition = ?, child_remarks = ?, father_name = ?, father_age = ?, father_tel = ?,");
		sql.append(" father_work = ?, mother_name = ?,  mother_age = ?,  mother_tel = ?, mother_work = ?, address = ?");
		sql.append(" WHERE child_name = ?");
		
		int result = this.jdbcTemplate.update(sql.toString(), family.getChildBirthday(), family.getChildCard(),
				family.getFamilySituation(), family.getPhysicalCondition(), family.getChildRemarks(),
				family.getFatherName(), family.getFatherAge(), family.getFatherTel(), family.getFatherWork(),
				family.getMotherName(), family.getMotherAge(), family.getMotherTel(), family.getMotherWork(),
				family.getAddress(), family.getChildName());

		return result;
	}

	public Integer deleteTeacher(String teacherName) {

		StringBuffer sql1 = new StringBuffer();
		sql1.append("DELETE FROM teacher WHERE user_id =");
		sql1.append(" ( SELECT user_id FROM users WHERE user_name = ?)");

		String sql2 = "DELETE FROM users WHERE user_name = ?";

		int num1 = this.jdbcTemplate.update(sql1.toString(), teacherName);
		int num2 = this.jdbcTemplate.update(sql2, teacherName);

		return num1 + num2;
	}

	public Integer deleteFamily(String childName) {

		StringBuffer sql1 = new StringBuffer();
		sql1.append("DELETE FROM family WHERE user_id =");
		sql1.append(" ( SELECT user_id FROM users WHERE user_name = ?)");

		String sql2 = "DELETE FROM users WHERE user_name = ?";

		int num1 = this.jdbcTemplate.update(sql1.toString(), childName);
		int num2 = this.jdbcTemplate.update(sql2, childName);

		return num1 + num2;
	}

}
