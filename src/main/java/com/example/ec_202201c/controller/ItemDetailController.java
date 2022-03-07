package com.example.ec_202201c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.ec_202201c.domain.Account;
import com.example.ec_202201c.domain.Item;
import com.example.ec_202201c.domain.Order;
import com.example.ec_202201c.domain.OrderItem;
import com.example.ec_202201c.domain.OrderTopping;
import com.example.ec_202201c.domain.Topping;

import com.example.ec_202201c.form.InsertOrderItemForm;

import com.example.ec_202201c.service.ItemDetailService;

@Controller
@RequestMapping("/item")
public class ItemDetailController {
	
	private final Integer TOPPING_MSIZE_PRICE = 200;
	private final Integer TOPPING_LSIZE_PRICE = 300;

	@Autowired
	private ItemDetailService itemDetailService;

	@ModelAttribute
	public InsertOrderItemForm setUpOrderItemForm() {
		return new InsertOrderItemForm();
	}

	/**
	 * @author hashimoto
	 * @return 商品詳細ページを表示
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Item item = itemDetailService.showDetail(id);
		
		model.addAttribute("item", item);

		List<Topping> toppingList = itemDetailService.findAll();
		model.addAttribute("toppingList", toppingList);
		
		return "item_detail";
	}

	@RequestMapping("/save")
	public String addOrderItemToCart(InsertOrderItemForm orderItemForm,Integer itemId,@AuthenticationPrincipal Account account, Model model) {
		
		Order order = itemDetailService.findShoppingCartByUserId(100);
		Item item = itemDetailService.showDetail(itemId);
		
		if (order.getId() == null) {
			
			System.out.println(item);
			/* 注文がnullの場合orderのインスタンスを生成 */
			Order shoppingCart = new Order();
			shoppingCart.setUserId(1000);

			Item inItem = new Item();
			Topping topping = new Topping();
			
			/* サイズによって金額の変更 */
			if (orderItemForm.getSize() == 'M') {
				inItem.setPriceM(item.getPriceM());
				topping.setPriceM(TOPPING_MSIZE_PRICE);
			} else if (orderItemForm.getSize() == 'L') {
				inItem.setPriceL(item.getPriceL());
				topping.setPriceL(TOPPING_LSIZE_PRICE);
			}
			
			
			List<OrderTopping> orderToppingList = new ArrayList<>();

			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setTopping(topping);
			orderToppingList.add(orderTopping);
			orderTopping.setToppingId(orderItemForm.getToppingId());

            //orderItemにインサート
			OrderItem orderItem = new OrderItem();
		    orderItem.setItem(item);
			orderItem.setItemId(itemId);
			
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());
			orderItem.setOrderToppingList(orderToppingList);
			
			//注文に合計金額をインサート
			shoppingCart.setTotalPrice(orderItem.getSubTotal());

			
			System.out.println("コントローラーのインサートの直前");
			System.out.println(order.getUserId());
			itemDetailService.OrderInsert(shoppingCart, orderItem, orderTopping);

		} else {
			System.out.println("order;" + order);
			OrderItem orderItem = new OrderItem();
			OrderTopping orderTopping = new OrderTopping();

			order.setTotalPrice(order.getCalcTotalPrice());

			orderItem.setItemId(itemId);
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());

			orderTopping.setOrderItemId(orderItemForm.getOrderItemId());
			orderTopping.setToppingId(orderItemForm.getToppingId());
			itemDetailService.ordersUpdate(order, orderItem, orderTopping);

		}
		return "redirect:/cart/list";
	}
}