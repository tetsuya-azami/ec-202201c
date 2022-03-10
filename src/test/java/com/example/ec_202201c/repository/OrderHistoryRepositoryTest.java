package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.ec_202201c.domain.Order;

@SpringBootTest
class OrderHistoryRepositoryTest {
	
	
	//商品を複数注文した場合のテスト
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	/*
	 * @Test void testLoad_orders() { List<Order> orderList =
	 * orderHistoryRepository.findHistoryByUserId(1); assertEquals("川越とんこつ醤油",
	 * orderList.get(0).getOrderItemList().get(0).getItem().getName(),
	 * "注文履歴リストの0番目は川越とんこつ醤油です"); assertEquals("元祖・白丸元味／元祖・赤丸新味",
	 * orderList.get(0).getOrderItemList().get(1).getItem().getName(),
	 * "注文履歴リストの1番目は元祖・白丸元味／元祖・赤丸新味です");
	 * 
	 * //assertEquals(1000,
	 * orderList.get(0).getOrderItemList().get(1).getItem().getPriceL(),
	 * "元祖・白丸元味／元祖・赤丸新味の値段は1000円です。"); //assertEquals("車麩",
	 * orderList.get(0).getOrderItemList().get(1).getOrderToppingList().get(0).
	 * getTopping().getName(), "追い鰹チャーシューのトッピングに車麩を頼みました");
	 * 
	 * //assertEquals("追い鰹チャーシュー",
	 * orderList.get(0).getOrderItemList().get(2).getItem().getName(),
	 * "注文履歴リストの2番目は追い鰹チャーシューです"); //assertEquals(1,
	 * orderList.get(0).getOrderItemList().get(2).getQuantity(),
	 * "追い鰹チャーシューは1つ頼みました");
	 * //assertTrue("川越とんこつ醤油".equals(orderList.get(0).getOrderItemList().get(0).
	 * getItem().getName()));
	 * 
	 * //assertEquals("鶏とんこつ麺",
	 * orderList.get(0).getOrderItemList().get(3).getItem().getName(),
	 * "注文履歴リストの3番目は鶏とんこつ麺です");
	 * 
	 * //assertEquals("辛味噌ちゃあしゅう麺",
	 * orderList.get(0).getOrderItemList().get(4).getItem().getName(),
	 * "注文履歴リストの4番目は辛味噌ちゃあしゅう麺です"); }
	 */
	
	//商品を1つだけ注文した場合のテスト
	/*
	 * @Test void testLoad_order() { List<Order> orderList =
	 * orderHistoryRepository.findHistoryByUserId(1); assertEquals("川越とんこつ醤油",
	 * orderList.get(0).getOrderItemList().get(0).getItem().getName(),
	 * "注文履歴リストの0番目は川越とんこつ醤油です"); }
	 */
	
	
	 //商品を1つも注文していない場合のテスト
	  
	 @Test 
	 void testLoad_no() { 
		 List<Order> orderList = orderHistoryRepository.findHistoryByUserId(1); assertEquals(null, orderList.get(0), "注文履歴リストに商品はありません"); 
		 }
	

}
