package com.example.ec_202201c.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.repository.OrderHistoryRepository;

@Service
public class OrderHistoryService {
	@Autowired
	private OrderHistoryRepository orderHistoryRepository;

	/**
	 * ショッピングカートをユーザのidで1件取得
	 *
	 * @param userId ログインユーザのid
	 * @return ログインユーザの注文確定前のショッピングカート
	 * @author Tetsuya Azami
	 */
	public Order findHistoryByUserId(Integer userId) {
		Order order = orderHistoryRepository.findHistoryByUserId(userId);
		return order;
	}
}
