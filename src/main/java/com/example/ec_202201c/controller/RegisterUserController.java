package com.example.ec_202201c.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("user")
public class RegisterUserController {
	/**
	 * @author Tetsuya Azami
	 * @return ユーザ登録画面
	 */
	@RequestMapping("insert")
	public String insert() {
		return "register_user";
	}
}
