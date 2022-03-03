package com.example.ec_202201c.controller;

import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.service.OrderConfirmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderConfirmController {

	@Autowired
	private OrderConfirmService orderConfirmService;

	/**
	 * @author Tetsuya Azami
	 * @return 注文確認画面を表示
	 */
	@RequestMapping("confirm")
	public String order(Model model) {
		Order order = orderConfirmService.findShoppingCartByUserId(1);
		model.addAttribute("order", order);
		return "order_confirm";
	}
}
