package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.User;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

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
