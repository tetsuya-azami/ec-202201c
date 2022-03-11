package com.example.ec_202201c.controller;

import com.example.ec_202201c.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class AdminInsertControllerTest {
	/** MockMvcオブジェクト */
	private MockMvc mockMvc;

	@MockBean
	AdminService adminService;

	@Autowired
	private AdminInsertController adminInsertController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(adminInsertController)
				.alwaysDo(MockMvcResultHandlers.log()).build();
	}



}
