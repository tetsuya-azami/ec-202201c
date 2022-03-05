package com.example.ec_202201c.controller;

import java.util.List;
import java.util.Locale;
import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.form.ItemSearchForm;
import com.example.ec_202201c.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/item")
public class ItemLIstController {
	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	private ItemSearchForm setUpItemSearchForm() {
		return new ItemSearchForm();
	}

	@Autowired
	private ItemListService itemListService;

	/**
	 * @author Ono Keitaro
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/list")
	public String list(Model model) {
		List<Item> itemList = itemListService.findAll();
		model.addAttribute("itemList", itemList);
		return "item_list_noodle";
	}

	@RequestMapping("/search")
	public String searchName(@Validated ItemSearchForm itemSearchForm, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return list(model);
		}
		List<Item> itemList = itemListService.findByLikeName(itemSearchForm.getName());
		if (itemList.isEmpty()) {
			result.rejectValue("name", "noResultByFuzzySearch");
			return list(model);
		}
		model.addAttribute("itemList", itemList);

		// ValidationMessages.propertiesからエラーメッセージ「ResultByFuzzySearch」を取得してviewに渡す(引数にitemListのサイズを渡してます)
		model.addAttribute("ResultByFuzzySearch", messageSource.getMessage("ResultByFuzzySearch",
				new String[] {String.valueOf(itemList.size())}, Locale.getDefault()));
		return "item_list_noodle";
	}
}
