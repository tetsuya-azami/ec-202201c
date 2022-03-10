package com.example.ec_202201c.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.stream.Collectors;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OrderConfirmRepositoryTest {
	private Order order = null;
	private List<String> orderItemNames = null;

	@Autowired
	private OrderConfirmRepository orderConfirmRepository;

	@Nested
	class findShoppingCartByUserId_ログインユーザidが1の場合 {
		@BeforeEach
		void setUpOrder() {
			// idが1であるユーザのショッピングカートを取得する
			order = orderConfirmRepository.findShoppingCartByUserId(1);

			// 取得したorderItemの商品名のリストを作成
			orderItemNames = order.getOrderItemList().stream()
					.map(orderItem -> orderItem.getItem().getName()).collect(Collectors.toList());
		}

		@Test
		void Order型のインスタンスを返すこと() {
			assertTrue(order instanceof Order);
		}

		@Test
		void orderの中のorderItemListに5件の商品が入っていること() {
			assertEquals(5, order.getOrderItemList().size());
		}

		@Test
		void とんこつラーメンが正しく取得できていること() {
			// 注文商品の中にとんこつラーメンが入っていること
			assertTrue(orderItemNames.contains("とんこつラーメン"));
			// とんこつラーメンのオブジェクトを取得
			OrderItem tonkotu = order.getOrderItemList().stream()
					.filter(orderItem -> "とんこつラーメン".equals(orderItem.getItem().getName()))
					.collect(Collectors.toList()).get(0);
			// 画像が1.pngであること
			assertEquals("1.jpg", tonkotu.getItem().getImagePath());
			// とんこつラーメンがMサイズであること
			assertEquals('M', tonkotu.getSize());
			// 価格が700であること
			assertEquals(700, tonkotu.getItem().getPriceM());
			// 数量が1であること
			assertEquals(1, tonkotu.getQuantity());
			// とんこつラーメンのオブジェクトに紐づいているトッピングのリストを作成
			List<String> tonkotuToppingList = tonkotu.getOrderToppingList().stream()
					.map(orderTopping -> orderTopping.getTopping().getName())
					.collect(Collectors.toList());
			// とんこつラーメンに正しいトッピングが入っていること
			assertTrue(tonkotuToppingList.contains("チャーシュー"));
			assertTrue(tonkotuToppingList.contains("煮たまご"));
		}

		@Test
		void 赤ラーメンが正しく取得できていること() {
			// 取得したorderItemの商品名のリストを作成
			List<String> orderItemNames = order.getOrderItemList().stream()
					.map(orderItem -> orderItem.getItem().getName()).collect(Collectors.toList());

			// 注文商品の中に赤ラーメンが入っていること
			assertTrue(orderItemNames.contains("赤ラーメン"));
			// 赤ラーメンのオブジェクトを取得
			OrderItem aka = order.getOrderItemList().stream()
					.filter(orderItem -> "赤ラーメン".equals(orderItem.getItem().getName()))
					.collect(Collectors.toList()).get(0);
			// 赤ラーメンのオブジェクトに紐づいているトッピングのリストを作成
			List<String> akaToppingList = aka.getOrderToppingList().stream()
					.map(orderTopping -> orderTopping.getTopping().getName())
					.collect(Collectors.toList());
			assertTrue(akaToppingList.contains("メンマ"));
			assertTrue(akaToppingList.contains("のり"));
		}

		@Test
		void からか麺が正しく取得できていること() {
			// 取得したorderItemの商品名のリストを作成
			List<String> orderItemNames = order.getOrderItemList().stream()
					.map(orderItem -> orderItem.getItem().getName()).collect(Collectors.toList());

			// 注文商品の中にからか麺が入っていること
			assertTrue(orderItemNames.contains("からか麺"));
			// からか麺のオブジェクトを取得
			OrderItem karaka = order.getOrderItemList().stream()
					.filter(orderItem -> "からか麺".equals(orderItem.getItem().getName()))
					.collect(Collectors.toList()).get(0);
			// からか麺のオブジェクトに紐づいているトッピングのリストを作成
			List<String> karakaToppingList = karaka.getOrderToppingList().stream()
					.map(orderTopping -> orderTopping.getTopping().getName())
					.collect(Collectors.toList());

			// トッピングが0件であること
			assertEquals(null, karakaToppingList.get(0));
		}

		@Test
		void 百福元味が正しく取得できていること() {
			// 取得したorderItemの商品名のリストを作成
			List<String> orderItemNames = order.getOrderItemList().stream()
					.map(orderItem -> orderItem.getItem().getName()).collect(Collectors.toList());

			// 注文商品の中に百福元味が入っていること
			assertTrue(orderItemNames.contains("百福元味"));
			// 百福元味のオブジェクトを取得
			OrderItem hyakuhuku = order.getOrderItemList().stream()
					.filter(orderItem -> "百福元味".equals(orderItem.getItem().getName()))
					.collect(Collectors.toList()).get(0);
			// 百福元味のオブジェクトに紐づいているトッピングのリストを作成
			List<String> hyakuhukuToppingList = hyakuhuku.getOrderToppingList().stream()
					.map(orderTopping -> orderTopping.getTopping().getName())
					.collect(Collectors.toList());

			// トッピングが0件であること
			assertEquals(null, hyakuhukuToppingList.get(0));
		}

		@Test
		void かさね味Specialが正しく取得できていること() {
			// 取得したorderItemの商品名のリストを作成
			List<String> orderItemNames = order.getOrderItemList().stream()
					.map(orderItem -> orderItem.getItem().getName()).collect(Collectors.toList());

			// 注文商品の中にかさね味Specialが入っていること
			assertTrue(orderItemNames.contains("かさね味Special"));
			// かさね味Specialのオブジェクトを取得
			OrderItem kasane = order.getOrderItemList().stream()
					.filter(orderItem -> "かさね味Special".equals(orderItem.getItem().getName()))
					.collect(Collectors.toList()).get(0);
			// かさね味Specialのオブジェクトに紐づいているトッピングのリストを作成
			List<String> kasaneToppingList = kasane.getOrderToppingList().stream()
					.map(orderTopping -> orderTopping.getTopping().getName())
					.collect(Collectors.toList());

			assertEquals(null, kasaneToppingList.get(0));
		}
	}

	@Nested
	class findShoppingCartByUserId_ログインユーザidがnullの場合 {
		@Test
		void Order型のインスタンスを返すこと() {
			assertTrue(order instanceof Order);
		}

		@Test
		void hogehoge() {
			order = orderConfirmRepository.findShoppingCartByUserId(null);
			// 商品リストが空で返ってくること
			assertTrue(order.getOrderItemList().isEmpty());
			// OrderConfirmControllerで商品リストが空である判定をする際にNullPointerExceptionが発生すること
			assertThrows(NullPointerException.class, () -> order.getOrderItemList().isEmpty());
		}
	}

	@Nested
	class findShoppingCartByUserId_ログインユーザidが3の場合 {
		@Test
		void Order型のインスタンスを返すこと() {
			assertTrue(order instanceof Order);
		}

		@Test
		void ショッピングカートの中身が空であること() {
			// ショッピングカート内の商品空となっているユーザのショッピングカートを取得
			order = orderConfirmRepository.findShoppingCartByUserId(3);
			assertTrue(order.getOrderItemList().isEmpty());
		}
	}
}
