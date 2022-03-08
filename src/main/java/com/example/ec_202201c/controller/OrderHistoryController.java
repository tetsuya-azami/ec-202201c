package com.example.ec_202201c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_202201c.domain.Account;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.service.OrderHistoryService;


@Controller
@RequestMapping("/order")
public class OrderHistoryController {
	@Autowired
	private OrderHistoryService orderHistoryService;

	/**
	 * @author Keitaro Ono
	 * @return 注文確認画面を表示
	 */
	@RequestMapping("/history")
	public String history(@AuthenticationPrincipal Account account, Model model) {
		
		List<Order> orderList = orderHistoryService.findHistoryByUserId(account.getUser().getId());
		model.addAttribute("orderList", orderList);
		System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n" + orderList);
		return "order_history";
	}
}
