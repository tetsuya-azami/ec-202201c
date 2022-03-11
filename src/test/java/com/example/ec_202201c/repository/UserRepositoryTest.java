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
//    	テストケース1
//    	ユーザー(JunitTest)の情報をインサート処理
    	User testUser1 = new User("JunitTest","12345@sample.com","12345678","123-4567","住所テスト1","111-2222-3333",1);
    	userRepository.insert(testUser1);
//		select (JunitTest)
		String sql1 = "SELECT * FROM users WHERE name = 'JunitTest';";
		SqlParameterSource param1 = new MapSqlParameterSource();
		User findUser1 = template.queryForObject(sql1, param1, USER_TEST_ROWMAPPER);
		assertEquals("JunitTest", findUser1.getName(),"名前1");
		assertEquals("12345@sample.com", findUser1.getEmail(),"Email1");
		assertEquals("12345678", findUser1.getPassword(),"パスワード1");
		assertEquals("123-4567", findUser1.getZipcode(),"郵便番号1");
		assertEquals("住所テスト1", findUser1.getAddress(),"住所1");
		assertEquals("111-2222-3333", findUser1.getTelephone(),"電話番号1");
		assertEquals(1, findUser1.getRole(),"管理者識別番号1");
		
//    	テストケース2
//      ユーザー(akita)の情報をインサート処理
		User testUser2 = new User("akita","akita@sample.com","akatakazuki","123-4567","住所テスト2","000-9999-8888",2);
    	userRepository.insert(testUser2);
//		select (akita)
    	String sql2 = "SELECT * FROM users WHERE name = 'akita';";
		SqlParameterSource param2 = new MapSqlParameterSource();
		User findUser2 = template.queryForObject(sql2, param2, USER_TEST_ROWMAPPER);
		
		assertEquals("akita", findUser2.getName(),"名前2");
		assertEquals("akita@sample.com", findUser2.getEmail(),"Email2");
		assertEquals("akatakazuki", findUser2.getPassword(),"パスワード2");
		assertEquals("123-4567", findUser2.getZipcode(),"郵便番号2");
		assertEquals("住所テスト2", findUser2.getAddress(),"住所2");
		assertEquals("000-9999-8888", findUser2.getTelephone(),"電話番号2");
		assertEquals(2, findUser2.getRole(),"管理者識別番号2");
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
