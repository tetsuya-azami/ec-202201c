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
		
		Order order = itemDetailService.ordersNullChecked(account.getUser().getId());
		System.out.println(order);
		Item item = itemDetailService.showDetail(itemId);
		
		/* 全件の取得 */
		List<Topping> toppingList = itemDetailService.findAll();
		if (order == null) {
			
			/* 注文がnullの場合orderのインスタンスを生成 */
			Order shoppingCart = new Order();
			shoppingCart.setUserId(account.getUser().getId());

			Item inItem = new Item();
			if(orderItemForm.getSize() == 'M') {
			inItem.setPriceM(item.getPriceM());
			}else if(orderItemForm.getSize() == 'L'){
			inItem.setPriceL(item.getPriceL());
			}
			
			//orderTopping
			List<Integer> checkedToppingIdList = orderItemForm.getToppingIdList();
			
			List<OrderTopping> orderToppingList = new ArrayList<>();

			for (Topping topping : toppingList) {
				
				if(checkedToppingIdList.contains(topping.getId())) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setTopping(topping);
					orderToppingList.add(orderTopping);
				}
			}
			 

            //orderItem
			OrderItem orderItem = new OrderItem();
		    orderItem.setItem(item);
			orderItem.setItemId(itemId);
			
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());
			orderItem.setOrderToppingList(orderToppingList);
			
			//注文に合計金額を取得
			shoppingCart.setTotalPrice(orderItem.getSubTotal());

			

			itemDetailService.OrderInsert(shoppingCart, orderItem, checkedToppingIdList);

			
		} else {
			
			Order shoppingCart = new Order();
			shoppingCart = itemDetailService.findShoppingCartByUserId(account.getUser().getId());
			
			System.out.println(shoppingCart.getId() + "aaa");
			
			Item inItem = new Item();
			if(orderItemForm.getSize() == 'M') {
			inItem.setPriceM(item.getPriceM());
			}else if(orderItemForm.getSize() == 'L'){
			inItem.setPriceL(item.getPriceL());
			}
			
			//orderTopping
			List<Integer> checkedToppingIdList = orderItemForm.getToppingIdList();
			
			List<OrderTopping> orderToppingList = new ArrayList<>();

			for (Topping topping : toppingList) {
				
				if(checkedToppingIdList.contains(topping.getId())) {
					OrderTopping orderTopping = new OrderTopping();
					orderTopping.setTopping(topping);
					orderToppingList.add(orderTopping);
				}
			}
			 

            //orderItem
			OrderItem orderItem = new OrderItem();
		    orderItem.setItem(item);
			orderItem.setItemId(itemId);
			orderItem.setOrderId(shoppingCart.getId());
			orderItem.setQuantity(orderItemForm.getQuantity());
			orderItem.setSize(orderItemForm.getSize());
			orderItem.setOrderToppingList(orderToppingList);
			
			List<OrderItem> orderItemList = shoppingCart.getOrderItemList();
			orderItemList.add(orderItem);
			
			shoppingCart.setOrderItemList(orderItemList);
			
			itemDetailService.ordersUpdate(shoppingCart, orderItem, checkedToppingIdList);
		}
		return "redirect:/cart/list";
	}
}