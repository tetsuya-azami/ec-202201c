package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import com.example.ec_202201c.domain.User;

@SpringBootTest
class UserRepositoryTest {
	private final RowMapper<User> USER_TEST_ROWMAPPER = (rs, i) -> {
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
    private UserRepository userRepository;

    @Autowired
	private NamedParameterJdbcTemplate template;
    
    @Test
    public void UserInsert() {
    	//ユーザーidが3の情報をインサート処理
    	User user1 = new User("JunitTest","12345@sample.com","12345678","123-4567","住所テスト1","111-2222-3333",1);
    	userRepository.insert(user1);
    	
    	//ユーザーidが3の場合
		String sql = "SELECT * FROM users WHERE id = 3";
		SqlParameterSource param = new MapSqlParameterSource();
		User findUser = template.queryForObject(sql, param, USER_TEST_ROWMAPPER);
		assertEquals("JunitTest", findUser.getName(),"名前");
		assertEquals("12345@sample.com", findUser.getEmail(),"Email");
		assertEquals("12345678", findUser.getPassword(),"パスワード");
		assertEquals("123-4567", findUser.getZipcode(),"郵便番号");
		assertEquals("住所テスト1", findUser.getAddress(),"住所");
		assertEquals("111-2222-3333", findUser.getTelephone(),"電話番号");
		assertEquals(1, findUser.getRole(),"管理者識別番号");
	}
    
        
	@Test
	public void findByEmailTset() {
		//テストケース１
		User user1 = userRepository.findByEmail("test1@example.com");
		assertNotNull(user1,"test1@example.comの場合");
		//テストケース２
		User user2 = userRepository.findByEmail("test2@example.com");
		assertNotNull(user2,"test1@example.comの場合");
		//テストケース3
		User user3 = userRepository.findByEmail("noting@test.co.jp");
		assertNull(user3,"登録してあるメールアドレスが無い場合のケース");
	}
	
	
	
}
