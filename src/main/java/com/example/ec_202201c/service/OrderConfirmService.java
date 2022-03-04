package com.example.ec_202201c.service;

import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.repository.OrderConfirmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderConfirmService {

	@Autowired
	private OrderConfirmRepository orderConfirmRepository;

	/**
	 * ショッピングカートをユーザのidで1件取得
	 *
	 * @param userId ログインユーザのid
	 * @return ログインユーザの注文確定前のショッピングカート
	 * @author Tetsuya Azami
	 */
	public Order findShoppingCartByUserId(Integer userId) {
		Order order = orderConfirmRepository.findShoppingCartByUserId(userId);
		return order;
	}

	public void finishingOrder(Order order) {
		orderConfirmRepository.finishingOrder(order);
	}
}
