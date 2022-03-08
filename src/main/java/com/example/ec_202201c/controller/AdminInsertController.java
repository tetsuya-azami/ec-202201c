package com.example.ec_202201c.controller;

import java.util.Locale;
import com.example.ec_202201c.form.ItemInsertForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class AdminInsertController {
	@Autowired
	private MessageSource messageSource;

	@ModelAttribute
	public ItemInsertForm setUpItemInsertForm() {
		return new ItemInsertForm();
	}

	@RequestMapping("/item/toInsert")
	public String itemToInsert() {
		return "/admin/item_insert";
	}

	/**
	 * 商品登録
	 *
	 * @param itemInsertForm 商品登録情報
	 * @param result バリデーションエラー情報
	 * @param model リクエストスコープ
	 * @param redirectAttributes リダイレクト情報
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/item/insert")
	public String itemInsert(@Validated ItemInsertForm itemInsertForm, BindingResult result,
			Model model, RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return itemToInsert();
		}
		redirectAttributes.addFlashAttribute("insertSuccess",
				messageSource.getMessage("insertSuccess", new String[] {}, Locale.getDefault()));
		return "redirect:/item/list";
	}
}
