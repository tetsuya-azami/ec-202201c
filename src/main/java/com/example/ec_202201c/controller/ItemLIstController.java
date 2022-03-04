package com.example.ec_202201c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.service.ItemListService;

@Controller
@RequestMapping("/item")
public class ItemLIstController {
	/**
	 * @author Tetsuya Azami
	 * @return 商品一覧ページ
	 */
	@Autowired
	private ItemListService itemListService;

	@RequestMapping("/list")
	public String list(Model model) {
		List<Item> itemList = itemListService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list_noodle";
	}

	@RequestMapping("/search")
	public String searchName(String inputItemName, Model model) {
		if(inputItemName.equals("")) {
			return list(model);
		}
		List<Item> itemList = itemListService.findByLikeName(inputItemName);
		if(itemList.isEmpty()) {
			model.addAttribute("noName", "該当する商品がありません");
			return list(model);
		}else {
			model.addAttribute("itemList", itemList);
			return "item_list_noodle";
		}
	}
}
