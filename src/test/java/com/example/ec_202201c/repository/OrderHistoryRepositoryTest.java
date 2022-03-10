package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.Order;

@SpringBootTest
class OrderHistoryRepositoryTest {
	
	
	
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;
	
	//商品を複数注文した場合のテスト
	@Test
	void testLoad_orders() { 
		List<Order> orderList = orderHistoryRepository.findHistoryByUserId(1); 
		assertEquals("川越とんこつ醤油",orderList.get(0).getOrderItemList().get(0).getItem().getName(), "注文履歴リストの0番目は川越とんこつ醤油です"); 
		assertEquals("元祖・白丸元味／元祖・赤丸新味", orderList.get(0).getOrderItemList().get(1).getItem().getName(), "注文履歴リストの1番目は元祖・白丸元味／元祖・赤丸新味です");
		assertEquals('L', orderList.get(0).getOrderItemList().get(1).getSize(), "注文履歴リストの1番目は元祖・白丸元味／元祖・赤丸新味です");
	 }
	
	//商品を1つだけ注文した場合のテスト
	 @Test 
	 void testLoad_order() { 
		 List<Order> orderList = orderHistoryRepository.findHistoryByUserId(2);
		 assertEquals("川越とんこつ醤油", orderList.get(0).getOrderItemList().get(0).getItem().getName(),  "注文履歴リストの0番目は川越とんこつ醤油です"); 
		 assertEquals("つみれ", orderList.get(0).getOrderItemList().get(0).getOrderToppingList().get(0).getTopping().getName(),  "川越とんこつ醤油につみれをトッピングしました"); 
	 }
	
	
	 //商品を1つも注文していない場合のテスト	  
	 @Test 
	 void testLoad_no() { 
		 List<Order> orderList = orderHistoryRepository.findHistoryByUserId(3); 
		 assertTrue(orderList.isEmpty(), "注文履歴リストに商品はありません"); 
		 }
	

}
