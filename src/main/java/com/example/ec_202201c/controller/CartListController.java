package com.example.ec_202201c.controller;

import java.util.Locale;
import com.example.ec_202201c.domain.Account;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.service.CartListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/cart")
public class CartListController {
	@Autowired
	private CartListService cartListService;

	@Autowired
	private MessageSource messageSource;

	/**
	 * @author Tetsuya Azami
	 * @return ショッピングカート一覧画面を表示
	 */
	@RequestMapping("/list")
	public String list(@AuthenticationPrincipal Account account, Model model) {
		Order order = cartListService.findShoppingCartByUserId(account.getUser().getId());
		model.addAttribute("order", order);
		return "cart_list";
	}

	@RequestMapping("/delete")
	public String deleteById(@AuthenticationPrincipal Account account, Integer orderItemId,
			RedirectAttributes redirectAttributes) {
		int deletedCount = cartListService.deleteOrderItemsAndOrderToppingsByOrderItemId(
				orderItemId, account.getUser().getId());

		if (deletedCount <= 0) {
			redirectAttributes.addFlashAttribute("deleteFailure", messageSource
					.getMessage("deleteFailure", new String[] {}, Locale.getDefault()));
		} else {
			redirectAttributes.addFlashAttribute("deleteSuccess", messageSource
					.getMessage("deleteSuccess", new String[] {}, Locale.getDefault()));
		}
		return "redirect:/cart/list";
	}

}
