package com.example.ec_202201c.controller;

import java.util.Locale;
import com.example.ec_202201c.domain.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {
	@Autowired
	private MessageSource messageSource;

	/**
	 * @author Tetsuya Azami
	 * @return ログインページを表示
	 */
	@RequestMapping("/")
	public String login(@AuthenticationPrincipal Account account, String error, Model model) {
		if (account != null) {
			return "redirect:/item/list";
		}
		if (error != null) {
			model.addAttribute("loginErrorMessage", messageSource.getMessage("loginErrorMessage",
					new String[] {}, Locale.getDefault()));
		}
		return "login";
	}
}
