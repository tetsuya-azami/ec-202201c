package com.example.ec_202201c.controller;

import java.text.ParseException;
import java.util.Date;
import com.example.ec_202201c.domain.Account;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.exception.UserNotFoundException;
import com.example.ec_202201c.form.OrderForm;
import com.example.ec_202201c.service.OrderConfirmService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public String confirm(@AuthenticationPrincipal Account account, Model model) {
		Order order;
		try {
			order = orderConfirmService.findShoppingCartByUserId(account.getUser().getId());
		} catch (UserNotFoundException e) {
			e.printStackTrace();
			return "redirect:/";
		}
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
	public String finishing(@Validated OrderForm orderForm, BindingResult result,
			@AuthenticationPrincipal Account account, Model model) {
		// 注文時から3時間以内に配達指定になっていたらエラーメッセージを返す
		try {
			if (checkIfTooEearlyDeliveryTime(orderForm.getOrderDeliveryTime())) {
				result.rejectValue("deliveryTime", "toEarlyDeliveryTimeError");
			}
		} catch (ParseException e1) {
			result.rejectValue("deliveryTime", "DateTimeFormat", new String[] {"日付"}, null);
			e1.printStackTrace();
		}

		if (result.hasErrors()) {
			return confirm(account, model);
		}

		Order order;
		try {
			order = orderConfirmService.findShoppingCartByUserId(account.getUser().getId());
		} catch (UserNotFoundException e1) {
			e1.printStackTrace();
			return "redirect:/";
		}

		// formからorderオブジェクトへの詰め替え
		BeanUtils.copyProperties(orderForm, order);
		order.setPaymentMethod(Integer.parseInt(orderForm.getPaymentMethod()));
		try {
			order.setDeliveryTime(orderForm.getOrderDeliveryTime());
		} catch (ParseException e) {
			e.printStackTrace();
			return confirm(account, model);
		}
		// orderオブジェクトにuser情報詰める
		order.setUser(account.getUser());
		orderConfirmService.finishingOrder(order);
		return "redirect:/order/finished";
	}

	/**
	 * 配達時間が3時間以内に指定されていればtrue, そうでなければfalseを返す
	 *
	 * @param deliveryTime 配達時間
	 * @return true or false
	 * @author Tetsuya Azami
	 */
	public Boolean checkIfTooEearlyDeliveryTime(Date deliveryTime) {
		Date now = new Date();
		long durationByMiliseconds = deliveryTime.getTime() - now.getTime();
		long durationByHours = durationByMiliseconds / 1000 / 60 / 60;
		if (durationByHours < 3) {
			return true;
		} else {
			return false;
		}
	}
}
