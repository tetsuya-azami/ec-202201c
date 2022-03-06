package com.example.ec_202201c.controller;

import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.form.UserForm;
import com.example.ec_202201c.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class RegisterUserController {
	@Autowired
	private UserService userService;

	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 *
	 * @return フォーム
	 */
	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}

	/**
	 * ユーザ登録ページを表示
	 *
	 * @return ユーザ登録ページ
	 */
	@RequestMapping("/toInsert")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * @author Kazuki Akita
	 * @return ユーザ登録処理
	 */
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result, Model model) {
		User userMail = userService.findByEmail(form.getEmail());
		if (userMail != null) {
			result.rejectValue("email", "dupulicateEmail");
		}
		if (userMail != null || result.hasErrors()) {
			return "register_user";
		}

		// フォームからドメインにプロパティ値をコピー
		User user = new User();
		user.setName(form.getName());
		user.setEmail(form.getEmail());
		user.setZipcode(form.getZipcode());
		user.setPassword(form.getPassword());
		user.setTelephone(form.getTelephone());
		user.setAddress(form.getAddress());
		user.setRole(2);
		userService.insert(user);
		return "redirect:/";
	}

	@RequestMapping("/create")
	public String create() {
		// 管理者ユーザ/
		User adminRoleUser = new User("rakunoo", "rakunoo@example.com", "rakunoo", "111-1111",
				"rakunoo住所", "011-1111-1111", 1);
		// 一般ユーザ
		User userRoleUser = new User("テストユーザ", "test@test.co.jp", "morimori", "222-2222", "テスト住所",
				"022-2222-2222", 2);
		userService.insert(adminRoleUser);
		userService.insert(userRoleUser);
		return "redirect:/";
	}
}
