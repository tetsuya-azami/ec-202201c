package com.example.ec_202201c.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.example.ec_202201c.service.OrderConfirmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@SpringBootTest
public class OrderConfirmControllerTest {
	/** MockMvcオブジェクト */
	private MockMvc mockMvc;

	@MockBean
	OrderConfirmService orderConfirmService;

	@Autowired
	private OrderConfirmController orderConfirmController;

	@BeforeEach
	public void setUp() {
		mockMvc = MockMvcBuilders.standaloneSetup(orderConfirmController)
				.alwaysDo(MockMvcResultHandlers.log()).build();
	}

	/**
	 * ログインページ遷移時のテスト
	 *
	 * @throws Exception
	 */
	@Test
	@DisplayName("注文確認ページ遷移時のテスト")
	@WithMockUser(username = "admin", password = "pass1", roles = "ADMIN")
	void GETAccess() throws Exception {
		mockMvc.perform(get("/order/confirm")).andExpect(status().isOk());
	}

	@Test
	void testCheckIfTooEearlyDeliveryTime() {}

	@Test
	void testConfirm() {}

	@Test
	void testFinishing() {}
}
