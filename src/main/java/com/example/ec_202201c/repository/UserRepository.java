package com.example.ec_202201c.repository;

import java.util.List;
import com.example.ec_202201c.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	private final RowMapper<User> USER_ROWMAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		user.setRole(rs.getInt("role"));
		return user;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * ユーザをメールアドレスで検索
	 *
	 * @param email 入力されたメールアドレス
	 * @return ユーザ情報
	 * @author Tetsuya Azami
	 */
	public User findByEmail(String email) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT ");
		sql.append("id, name, email, password, zipcode, address, telephone, role ");
		sql.append("FROM users ");
		sql.append("WHERE email = :email;");

		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);
		List<User> userList = template.query(sql.toString(), param, USER_ROWMAPPER);
		if (userList.size() == 0) {
			return null;
		} else {
			return userList.get(0);
		}
	}

	/**
	 * ユーザ情報の登録
	 *
	 * @param user ユーザ情報
	 * @author Tetsuya Azami
	 */
	public void insert(User user) {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO users ");
		sql.append("(name, email, password, zipcode, address, telephone, role ) ");
		sql.append("VALUES (:name, :email,:password,:zipcode,:address,:telephone, :role);");
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);
		template.update(sql.toString(), param);
	}
}
