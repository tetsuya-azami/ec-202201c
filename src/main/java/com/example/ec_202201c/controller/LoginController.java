package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {
	/**
	 * @author Tetsuya Azami
	 * @return ログインページを表示
	 */
	@RequestMapping("/")
	public String login(String error, Model model) {
		System.out.println("条件分岐前");
		System.out.println(error);
		if (error != null) {
			System.out.println("errorがnullでない");
			System.out.println(error);
			model.addAttribute("errorMessage", "メールアドレスかパスワードが間違っています");
		}
		return "login";
	}
}