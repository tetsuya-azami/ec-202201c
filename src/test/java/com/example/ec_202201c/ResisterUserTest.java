package com.example.ec_202201c;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.service.UserService;

@SpringBootTest
class ResisterUserTest {
	@Autowired
	private UserService userService;
	
	@Test
	public void testFindByEmail() {
		User userMail = userService.findByEmail("rakunoo@example.com");
		assertEquals("null",userMail,"nullではありません");
		assertEquals("rakunoo@example.com",userMail.getEmail(),"nullです");
		System.out.println(userMail.getEmail());
	}
	
	@Test
	public void testInsert() {
		User user = new User();
		user.setName("rakunoo");
		user.setEmail("rakunoo@example.com");
		user.setZipcode("rakunoo");
		user.setPassword("111-1111");
		user.setTelephone("011-1111-1111");
		user.setAddress("rakunoo住所");
		user.setRole(1);
		userService.insert(user);
	}
	
}
