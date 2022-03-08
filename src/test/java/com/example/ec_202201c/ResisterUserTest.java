package com.example.ec_202201c;



import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.example.ec_202201c.repository.UserRepository;

@SpringBootTest
class ResisterUserTest {
	@Autowired
    JdbcTemplate jdbctemplate;

    @Autowired
     UserRepository userRepository;
	
	@Test
	private void insertRepositoryTset() {
		//assertEquals(null, null,"");
	}
		
	
}
