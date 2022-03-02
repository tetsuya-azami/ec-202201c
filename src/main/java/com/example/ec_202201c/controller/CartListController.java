package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cart")
public class CartListController {
	/**
	 * @author Tetsuya Azami
	 * @return ショッピングカート一覧画面を表示
	 */
	@RequestMapping("list")
	public String list() {
		return "cart_list";
	}
}
