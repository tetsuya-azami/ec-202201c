package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("")
public class LoginController {
	/**
	 * @author Tetsuya Azami
	 * @return ログインページを表示
	 */
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
}
