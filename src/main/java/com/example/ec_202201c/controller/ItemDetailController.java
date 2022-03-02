package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemDetailController {
	/**
	 * @author
	 * @return 商品詳細ページを表示
	 */
	@RequestMapping("/detail")
	public String detail() {
		return "item_detail";
	}
}
