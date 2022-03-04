package com.example.ec_202201c.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.service.CartListService;

@Controller
@RequestMapping("/cart")
public class CartListController {
	@Autowired
	CartListService cartListService;
	/**
	 * @author Tetsuya Azami
	 * @return ショッピングカート一覧画面を表示
	 */
	@RequestMapping("/list")
	public String list(Integer useerId,Model model) {
		Order order = cartListService.findShoppingCartByUserId(2);
		model.addAttribute("order", order);
		System.out.println(order);
		return "cart_list";
	}
}
