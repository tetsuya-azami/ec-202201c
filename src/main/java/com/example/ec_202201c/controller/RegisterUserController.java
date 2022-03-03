package com.example.ec_202201c.controller;

import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterUserController {
	@Autowired
	private UserService userService;

	/**
	 * @author Tetsuya Azami
	 * @return ユーザ登録画面
	 */
	@RequestMapping("toInsert")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * @author Tetsuya Azami
	 * @return ユーザ登録処理
	 */
	@RequestMapping("/insert")
	public String insert() {
		User user =
				new User("hoge", "hoge@example.com", "aaaaaaaa", "111-1111", "住所", "000-0000-0000");
		userService.insert(user);
		return "redirect:/";
	}
}
