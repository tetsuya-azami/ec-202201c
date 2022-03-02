package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemLIstController {
	/**
	 * @author Tetsuya Azami
	 * @return 商品一覧ページ
	 */
	@RequestMapping("list")
	public String list() {
		return "item_list_noodle";
	}
}
