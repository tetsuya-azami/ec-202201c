package com.example.ec_202201c.controller;

import java.util.Locale;
import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.form.ItemSearchForm;
import com.example.ec_202201c.service.ItemListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String list(Model model, @PageableDefault(page = 0, size = 9) Pageable pageable,
			@ModelAttribute("name") String name) {
		Page<Item> itemList = itemListService.findAll(pageable);
		model.addAttribute("page", itemList);
		model.addAttribute("itemList", itemList.getContent());
		model.addAttribute("name", name);
		//System.out.println("\n\n\n\n\n\n\n\n\n\n" + itemList.getContent().get(0).getName());
		return "item_list_noodle";
	}

	@RequestMapping("/search")
	public String searchName(@Validated ItemSearchForm itemSearchForm, BindingResult result,
			Model model, @PageableDefault(page = 0, size = 9) Pageable pageable,
			RedirectAttributes redirectAttributes) {
		Page<Item> itemList = itemListService.findByLikeName(itemSearchForm.getName(), pageable);
		if ("".equals(itemSearchForm.getName())) {
			return "redirect:/item/list";
		}
		if (result.hasErrors()) {
			return "redirect:/item/list";
		}

		if (itemList.isEmpty()) {
			redirectAttributes.addFlashAttribute("name", "検索結果がありませんでした");
			return "redirect:/item/list";
		}

		model.addAttribute("page", itemList);
		model.addAttribute("itemList", itemList.getContent());
		// ValidationMessages.propertiesからエラーメッセージ「ResultByFuzzySearch」を取得してviewに渡す(引数にitemListのサイズを渡してます)
		model.addAttribute("ResultByFuzzySearch", messageSource.getMessage("ResultByFuzzySearch",
				new String[] {String.valueOf(itemList.getContent().size())}, Locale.getDefault()));
		return "item_list_noodle";
	}
}
