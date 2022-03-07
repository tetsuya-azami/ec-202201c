package com.example.ec_202201c.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@Autowired
	private ItemDetailService itemDetailService;

	@ModelAttribute
	public InsertOrderItemForm setUpOrderItemForm() {
		return new InsertOrderItemForm();
	}

	/**
	 * @author
	 * @return 商品詳細ページを表示
	 */
	@RequestMapping("/detail")
	public String detail(Integer id, Model model) {
		Item item = itemDetailService.showDetail(id);
		
		model.addAttribute("item", item);
		model.addAttribute("id", id);

		List<Topping> toppingList = itemDetailService.findAll();
		model.addAttribute("toppingList", toppingList);
		
		return "item_detail";
	}

	@RequestMapping("/save")
	public String addOrderItemToCart(InsertOrderItemForm orderItemForm, Model model) {
		Order order = itemDetailService.findShoppingCartByUserId(100);
		if (order.getId() == null) {
			System.out.println(orderItemForm.getSize());
			System.out.println(orderItemForm.getQuantity());
			System.out.println(orderItemForm.getItemId());
			System.out.println(orderItemForm.getOrderItemId());
			System.out.println(orderItemForm.getToppingId());

			Item item = new Item();
			Topping topping = new Topping();
			
			if (orderItemForm.getSize() == 'M') {
				item.setPriceM(680);
				topping.setPriceM(200);
			} else if (orderItemForm.getSize() == 'L') {
				item.setPriceL(800);
				topping.setPriceL(300);
			}
			
			
//		    Topping topping = new Topping();
//		    topping.setPriceM(200);

			List<OrderTopping> orderToppingList = new ArrayList<>();

			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setTopping(topping);

			orderToppingList.add(orderTopping);

			OrderItem orderItem = new OrderItem();
		    orderItem.setItem(item);
			orderItem.setItemId(orderItemForm.getItemId());
			orderItem.setOrderId(1);
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());

			orderItem.setOrderToppingList(orderToppingList);

			order.setUserId(1);
//			order.setStatus(0);
			order.setTotalPrice(orderItem.getSubTotal());

//			orderTopping.setOrderItemId(6);
			orderTopping.setToppingId(orderItemForm.getToppingId());

			itemDetailService.OrderInsert(order, orderItem, orderTopping);

		} else {
			System.out.println("order;" + order);
			OrderItem orderItem = new OrderItem();
			OrderTopping orderTopping = new OrderTopping();

			order.setTotalPrice(order.getCalcTotalPrice());

			orderItem.setItemId(orderItemForm.getItemId());
			orderItem.setOrderId(orderItemForm.getOrderId());
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());

			orderTopping.setOrderItemId(orderItemForm.getOrderItemId());
			orderTopping.setToppingId(orderItemForm.getToppingId());
			itemDetailService.ordersUpdate(order, orderItem, orderTopping);

		}
		return "redrect:/cart/list";
	}
}