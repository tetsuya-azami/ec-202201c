package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order")
public class OrderConfirmController {
	/**
	 * @author Tetsuya Azami
	 * @return 注文確認画面を表示
	 */
	@RequestMapping("confirm")
	public String order() {
		return "order_confirm";
	}
}
