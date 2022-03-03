package com.example.ec_202201c.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Topping;
import com.example.ec_202201c.service.ItemDetailService;

@Controller
@RequestMapping("/item")
public class ItemDetailController {
	
	@Autowired
	private ItemDetailService itemDetailService;
	/**
	 * @author
	 * @return 商品詳細ページを表示
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Item item = itemDetailService.showDetail(id);
		model.addAttribute("item", item);
		
		List<Topping> toppingList = itemDetailService.findAll();
		model.addAttribute("toppingList", toppingList);
		
		return "item_detail";
	}
}
