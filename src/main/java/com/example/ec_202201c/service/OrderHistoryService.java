package com.example.ec_202201c.service;

import java.util.List;

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
	 * @author Keitaro Ono
	 */
	public List<Order> findHistoryByUserId(Integer userId) {
		return orderHistoryRepository.findHistoryByUserId(userId);
	}
}
