package com.example.ec_202201c.controller;

import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.form.UserForm;
import com.example.ec_202201c.repository.UserRepository;
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
	@Autowired
	private UserRepository repository;
	/**
	 * @author Tetsuya Azami
	 * @return ユーザ登録画面
	 */
	
	
	/**
	 * 使用するフォームオブジェクトをリクエストスコープに格納する.
	 * @return フォーム
	 */
	@ModelAttribute
	public UserForm setUpUserForm() {
		return new UserForm();
	}
	
	@RequestMapping("toInsert")
	public String toInsert() {
		return "register_user";
	}

	/**
	 * @author Tetsuya Azami
	 * @return ユーザ登録処理
	 */
	@RequestMapping("/insert")
	public String insert(@Validated UserForm form, BindingResult result,Model model) {
			if(result.hasErrors()) {
				return "register_user";
			}
					
			User userMail = repository.findByEmail(form.getEmail());
			if(userMail != null) {
					model.addAttribute("mailErrorMessage", "メールアドレスが重複しています");
					return "register_user";
			}else {
				// フォームからドメインにプロパティ値をコピー
				User user =new User();
				user.setName(form.getName());
				user.setEmail(form.getEmail());
				user.setZipcode(form.getZipcode());
				user.setPassword(form.getPassword());
				user.setTelephone(form.getTelephone());
				user.setAddress(form.getAddress());
				userService.insert(user);
				return "redirect:/";
			}
		}
}
