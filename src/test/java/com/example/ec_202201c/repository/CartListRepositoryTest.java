package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.Order;
@SpringBootTest
class CartListRepositoryTest {

	@Autowired
	CartListRepository cartListRepository;

	@Test
	public void test() {
		Order order = new Order();
		order = cartListRepository.findShoppingCartByUserId(1);
		
		assertEquals(1, order, "オーダーID");
		assertEquals(0,order.getTotalPrice(),"合計金額");
		
	}
}
