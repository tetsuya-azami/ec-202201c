package com.example.ec_202201c.controller;

import java.text.ParseException;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.User;
import com.example.ec_202201c.form.OrderForm;
import com.example.ec_202201c.service.OrderConfirmService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderConfirmController {

	@Autowired
	private OrderConfirmService orderConfirmService;

	@ModelAttribute
	private OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	/**
	 * @author Tetsuya Azami
	 * @return 注文確認画面を表示
	 */
	@RequestMapping("/confirm")
	public String confirm(Model model) {
		Order order = orderConfirmService.findShoppingCartByUserId(1);
		if (order.getOrderItemList().isEmpty()) {
			return "redirect:/cart/list";
		}
		model.addAttribute("order", order);
		return "order_confirm";
	}

	/**
	 * 注文確定
	 *
	 * @param orderForm 注文ユーザ情報(住所等)
	 * @param result バリデーションエラー情報
	 * @return 注文確定画面
	 */
	@RequestMapping("/finishing")
	public String finishing(@Validated OrderForm orderForm, BindingResult result, Model model) {
		if (result.hasErrors()) {
			return confirm(model);
		}
		Order order = orderConfirmService.findShoppingCartByUserId(1);

		// formからorderオブジェクトへの詰め替え
		BeanUtils.copyProperties(orderForm, order);
		order.setPaymentMethod(Integer.parseInt(orderForm.getPaymentMethod()));
		try {
			System.out.println(orderForm.getDeliveryDate());
			order.setDeliveryTime(orderForm.getOrderDeliveryTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return confirm(model);
		}
		// orderオブジェクトにuser情報詰める
		User user = new User();
		user.setId(1);
		order.setUser(user);

		orderConfirmService.finishingOrder(order);
		return "redirect:/order/finished";
	}
}
